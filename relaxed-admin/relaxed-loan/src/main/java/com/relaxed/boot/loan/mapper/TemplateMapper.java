package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.loan.converter.TemplateConverter;
import com.relaxed.boot.loan.model.entity.Template;
import com.relaxed.boot.loan.model.qo.TemplateQO;
import com.relaxed.boot.loan.model.vo.TemplatePageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 模板文件配置表
 *
 * @author yakir 2022-12-14 11:36:23
 */
public interface TemplateMapper extends ExtendMapper<Template> {

    /**
    * 分页查询
    * @param pageParam 分页参数
    * @param qo 查询参数
    * @return PageResult<TemplatePageVO> VO分页数据
    */
   default PageResult<TemplatePageVO> queryPage(PageParam pageParam, TemplateQO qo){
		IPage<Template> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<Template> wrapper = WrappersX.lambdaQueryX(Template.class);
		this.selectPage(page, wrapper);
		IPage<TemplatePageVO> voPage = page.convert(TemplateConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
   }
}