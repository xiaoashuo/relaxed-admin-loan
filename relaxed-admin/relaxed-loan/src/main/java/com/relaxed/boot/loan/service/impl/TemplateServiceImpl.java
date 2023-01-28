package com.relaxed.boot.loan.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.common.system.utils.file.FileConfig;
import com.relaxed.boot.common.system.utils.file.FileMeta;
import com.relaxed.boot.common.system.utils.file.FileUtils;
import com.relaxed.boot.framework.config.RelaxedConfig;
import com.relaxed.boot.loan.model.entity.Template;
import com.relaxed.boot.loan.model.entity.TemplateArea;
import com.relaxed.boot.loan.model.vo.TemplatePageVO;
import com.relaxed.boot.loan.model.qo.TemplateQO;
import com.relaxed.boot.loan.mapper.TemplateMapper;
import com.relaxed.boot.loan.service.TemplateAreaService;
import com.relaxed.boot.loan.service.TemplateService;
import com.relaxed.boot.loan.util.word.StoreWordTemplate;
import com.relaxed.boot.loan.util.word.domain.ElementMeta;
import com.relaxed.common.core.exception.BusinessException;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.domain.SelectData;
import com.relaxed.common.model.result.SysResultCode;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 模板文件配置表
 *
 * @author yakir 2022-12-14 11:36:23
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class TemplateServiceImpl extends ExtendServiceImpl<TemplateMapper, Template> implements TemplateService {

	private final TemplateAreaService templateAreaService;
	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult<TemplatePageVO> 分页数据
	 */
	@Override
	public PageResult<TemplatePageVO> queryPage(PageParam pageParam, TemplateQO qo) {
		return baseMapper.queryPage(pageParam, qo);
	}

	@Override
	public String getByTemplateCode(String templateCode) {
		return getOne(Wrappers.lambdaQuery(Template.class).eq(Template::getTemplateCode, templateCode))
				.getTemplateUrl();
	}

	@Override
	public List<SelectData> querySelectData() {
		List<Template> templateList = list();
		List<SelectData> selectDataList = templateList.stream().map(e -> {
			SelectData<Template> selectData = new SelectData<>();
			selectData.setLabel(e.getTemplateName());
			selectData.setValue(e.getTemplateId());
			selectData.setExtendObj(e);
			return selectData;
		}).collect(Collectors.toList());
		return selectDataList;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean removeTemplate(Integer templateId) {
		Assert.isTrue(templateAreaService.removeByTemplateId(templateId),()-> new BusinessException(SysResultCode.SERVER_ERROR.getCode(),"删除模板参数异常"));
		boolean success = removeById(templateId);
		return success;
	}
}
