package com.relaxed.boot.loan.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.common.system.utils.file.*;
import com.relaxed.boot.framework.config.RelaxedConfig;
import com.relaxed.boot.loan.constants.LoanUploadPath;
import com.relaxed.boot.loan.enums.CertificateEnum;
import com.relaxed.boot.loan.model.entity.Certificate;
import com.relaxed.boot.loan.model.entity.ProjectTemplate;
import com.relaxed.boot.loan.model.vo.CertificatePageVO;
import com.relaxed.boot.loan.model.qo.CertificateQO;
import com.relaxed.boot.loan.mapper.CertificateMapper;
import com.relaxed.boot.loan.service.CertificateService;
import com.relaxed.boot.loan.service.ProjectTemplateService;
import com.relaxed.boot.loan.util.keystore.KeystoreMeta;
import com.relaxed.boot.loan.util.keystore.KeystoreUtil;
import com.relaxed.common.core.exception.BusinessException;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.domain.SelectData;
import com.relaxed.common.model.result.SysResultCode;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 签章证书表
 *
 * @author yakir 2022-12-14 11:36:23
 */
@RequiredArgsConstructor
@Service
public class CertificateServiceImpl extends ExtendServiceImpl<CertificateMapper, Certificate>
		implements CertificateService {

	private final ProjectTemplateService projectTemplateService;

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult<CertificatePageVO> 分页数据
	 */
	@Override
	public PageResult<CertificatePageVO> queryPage(PageParam pageParam, CertificateQO qo) {
		PageResult<CertificatePageVO> certificatePageVOPageResult = baseMapper.queryPage(pageParam, qo);
		for (CertificatePageVO record : certificatePageVOPageResult.getRecords()) {
			record.setCertificateAddress(RelaxedConfig.getRequestFullUrl(record.getCertificateAddress()));
		}
		return certificatePageVOPageResult;
	}

	@Override
	public List<SelectData> queryCertificateList() {
		return list().stream().map(item -> {
			Map<String, Object> additionalInfo = new HashMap<>();
			additionalInfo.put("url", RelaxedConfig.getRequestFullUrl(item.getCertificateAddress()));
			additionalInfo.put("name", item.getCertificateFilename());
			SelectData<Map<String, Object>> selectItem = new SelectData<>();
			selectItem.setLabel(item.getCertificateSubject());
			selectItem.setValue(item.getCertificateId());
			selectItem.setExtendObj(additionalInfo);
			return selectItem;
		}).collect(Collectors.toList());
	}

	@Override
	public boolean saveCertificate(Certificate certificate) {
		Integer authorizeType = certificate.getAuthorizeType();
		Integer keystoreType = certificate.getKeystoreType();

		if (CertificateEnum.AUTHORIZE_TYPE.SYSTEM.getVal().equals(authorizeType)) {
			String certificateSubject = certificate.getCertificateSubject();
			String certificateAlias = certificate.getCertificateAlias();
			CertificateEnum.KEYSTORE_TYPE keystoreTypeEnum = CertificateEnum.KEYSTORE_TYPE.getByVal(keystoreType);
			// 系统生成
			KeystoreMeta keystoreMeta = new KeystoreMeta().password("123456").validity(certificate.getValidityTime())
					.certificateCRL("https://relaxed.cn").keystoreType(keystoreTypeEnum.getDesc())
					.alias(certificateAlias).subject().CN(certificateAlias).OU(certificateSubject).O(certificateSubject)
					.C("CN").end().issuer().CN("Yakir").OU("relaxed研发部").O("relaxed有限公司").L("上海").E("relaxed@qq.com")
					.ST("上海").C("CN").end();
			String filename = "sys_keystore" + RandomUtil.randomNumbers(6) + "." + keystoreTypeEnum.getSuffix();

			ByteArrayOutputStream byteArrayOutputStream = KeystoreUtil.generateKeyStoreV3(keystoreMeta);
			ByteArrayMultipartFile file = new ByteArrayMultipartFile(byteArrayOutputStream.toByteArray(), filename);
			FileMeta fileMeta = FileUtils.upload(RelaxedConfig.getProfile(), LoanUploadPath.KEYSTORE_RELATIVE_PATH,
					file, FileConfig.create().splitDate(false));
			String address = fileMeta.getRelativeFilePath();
			certificate.setCertificateFilename(filename);
			certificate.setCertificateAddress(address);
		}
		boolean result = save(certificate);
		return result;
	}

	@Override
	public boolean removeCertificateById(Integer certificateId) {
		Certificate certificate = getById(certificateId);
		Assert.notNull(certificate, () -> new BusinessException(SysResultCode.BAD_REQUEST.getCode(), "证书文件不存在"));
		long relatedCount = projectTemplateService.countByKeystoreId(certificateId);
		Assert.isTrue(relatedCount <= 0,
				() -> new BusinessException(SysResultCode.BAD_REQUEST.getCode(), "证书存在关联使用,不允许删除."));
		String certificateAddress = certificate.getCertificateAddress();
		Assert.isTrue(StrUtil.isNotEmpty(certificateAddress),
				() -> new BusinessException(SysResultCode.BAD_REQUEST.getCode(), "证书地址不存在"));
		boolean deleteSuccess = FileUtils.delete(RelaxedConfig.getProfile(), certificateAddress);
		Assert.isTrue(deleteSuccess, () -> new BusinessException(SysResultCode.BAD_REQUEST.getCode(), "证书文件删除失败"));
		return removeById(certificateId);
	}

}
