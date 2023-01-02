package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.loan.converter.TemplateAreaConverter;
import com.relaxed.boot.loan.model.entity.TemplateArea;
import com.relaxed.boot.loan.model.qo.TemplateAreaQO;
import com.relaxed.boot.loan.model.vo.TemplateAreaPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 模板文件文本域表
 *
 * @author Yakir 2022-12-16 11:18:37
 */
public interface TemplateAreaMapper extends ExtendMapper<TemplateArea> {

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param qo 查询参数
	 * @return PageResult<TemplateAreaPageVO> VO分页数据
	 */
	default PageResult<TemplateAreaPageVO> queryPage(PageParam pageParam, TemplateAreaQO qo) {
		IPage<TemplateArea> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<TemplateArea> wrapper = WrappersX.lambdaQueryX(TemplateArea.class);
		wrapper.eq(TemplateArea::getTemplateId, qo.getTemplateId());
		this.selectPage(page, wrapper);
		IPage<TemplateAreaPageVO> voPage = page.convert(TemplateAreaConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

}
