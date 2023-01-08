package com.relaxed.boot.loan.service.impl;

import com.relaxed.boot.loan.model.entity.TemplateArea;
import com.relaxed.boot.loan.model.vo.TemplateAreaPageVO;
import com.relaxed.boot.loan.model.qo.TemplateAreaQO;
import com.relaxed.boot.loan.mapper.TemplateAreaMapper;
import com.relaxed.boot.loan.service.TemplateAreaService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;
import org.springframework.stereotype.Service;

/**
 * 模板文件文本域表
 *
 * @author Yakir 2022-12-16 11:18:37
 */
@Service
public class TemplateAreaServiceImpl extends ExtendServiceImpl<TemplateAreaMapper, TemplateArea>
		implements TemplateAreaService {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult<TemplateAreaPageVO> 分页数据
	 */
	@Override
	public PageResult<TemplateAreaPageVO> queryPage(PageParam pageParam, TemplateAreaQO qo) {
		return baseMapper.queryPage(pageParam, qo);
	}

	@Override
	public boolean removeByTemplateId(Integer templateId) {
		return remove(WrappersX.lambdaQueryX(TemplateArea.class)
				.eq(TemplateArea::getTemplateId, templateId));
	}
}
