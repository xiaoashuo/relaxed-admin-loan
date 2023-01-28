package com.relaxed.boot.loan.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.relaxed.boot.common.system.utils.file.ByteArrayMultipartFile;
import com.relaxed.boot.common.system.utils.file.FileConfig;
import com.relaxed.boot.common.system.utils.file.FileMeta;
import com.relaxed.boot.common.system.utils.file.FileUtils;
import com.relaxed.boot.framework.config.RelaxedConfig;
import com.relaxed.boot.loan.converter.SealConverter;
import com.relaxed.boot.loan.enums.SealEnum;
import com.relaxed.boot.loan.model.entity.Seal;
import com.relaxed.boot.loan.model.vo.SealPageVO;
import com.relaxed.boot.loan.model.qo.SealQO;
import com.relaxed.boot.loan.mapper.SealMapper;
import com.relaxed.boot.loan.service.SealService;
import com.relaxed.boot.loan.util.seal.SealCircle;
import com.relaxed.boot.loan.util.seal.SealFont;
import com.relaxed.boot.loan.util.seal.SealGenerate;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.domain.SelectData;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 签章图片表
 *
 * @author yakir 2022-12-15 10:25:25
 */
@Service
public class SealServiceImpl extends ExtendServiceImpl<SealMapper, Seal> implements SealService {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult<SealPageVO> 分页数据
	 */
	@Override
	public PageResult<SealPageVO> queryPage(PageParam pageParam, SealQO qo) {
		PageResult<SealPageVO> sealPageVOPageResult = baseMapper.queryPage(pageParam, qo);
		sealPageVOPageResult.getRecords().forEach(seal -> seal.setSealAddress(RelaxedConfig.getRequestFullUrl(seal.getSealAddress())));
		return sealPageVOPageResult;
	}

	@Override
	public List<SealPageVO> queryList() {
		List<Seal> list = list();
		list.forEach(seal -> seal.setSealAddress(RelaxedConfig.getRequestFullUrl(seal.getSealAddress())));
		return SealConverter.INSTANCE.poToPageVos(list);
	}

	@Override
	public List<SelectData> querySealList() {
		return list().stream().map(item -> {
			Map<String, Object> additionalInfo = new HashMap<>();
			additionalInfo.put("url", RelaxedConfig.getRequestFullUrl(item.getSealAddress()));
			additionalInfo.put("name", item.getSealFilename());
			SelectData<Map<String, Object>> selectItem = new SelectData<>();
			selectItem.setLabel(item.getSealSubject());
			selectItem.setValue(item.getSealId());
			selectItem.setExtendObj(additionalInfo);
			return selectItem;
		}).collect(Collectors.toList());
	}

	@SneakyThrows
	@Override
	public boolean saveSeal(Seal seal) {
		String sealCode = IdUtil.getSnowflakeNextId() + "";
		seal.setSealCode(sealCode);
		Integer sealSource = seal.getSealSource();
		if (SealEnum.SEAL_SOURCE.SYSTEM.getVal().equals(sealSource)) {
			// 系统生成
			// 图片类型
			String sealSubject = seal.getSealSubject();
			Integer sealPictureType = seal.getSealPictureType();
			String filename = "sys-" + IdUtil.simpleUUID() + ".png";
			if (SealEnum.SEAL_TYPE.OFFICIAL.getVal().equals(sealPictureType)) {
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				SealGenerate.builder().size(200).borderCircle(SealCircle.builder().line(4).width(95).height(95).build())
						.mainFont(SealFont.builder().text(sealSubject).size(22).space(22.0).margin(4).build())
						.centerFont(SealFont.builder().text("★").size(60).build())
						.titleFont(SealFont.builder().text("Relaxed发布").size(16).space(8.0).margin(54).build()).build()
						.drawToOutStream(outputStream);
				ByteArrayMultipartFile file = new ByteArrayMultipartFile(outputStream.toByteArray(), filename);
				FileMeta fileMeta = FileUtils.upload(RelaxedConfig.getProfile(), "profile/seal", file,
						FileConfig.create().splitDate(false));
				String address =  fileMeta.getRelativeFilePath();
				seal.setSealFilename(filename);
				seal.setSealAddress(address);

			}
			else {

			}

		}


		boolean result = save(seal);
		return result;
	}

}
