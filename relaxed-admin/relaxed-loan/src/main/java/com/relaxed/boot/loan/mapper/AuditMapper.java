package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.loan.converter.AuditConverter;
import com.relaxed.boot.loan.model.entity.Audit;
import com.relaxed.boot.loan.model.qo.AuditQO;
import com.relaxed.boot.loan.model.vo.AuditPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 审核表
 *
 * @author yakir 2023-01-10 10:32:31
 */
public interface AuditMapper extends ExtendMapper<Audit> {

    /**
    * 分页查询
    * @param pageParam 分页参数
    * @param qo 查询参数
    * @return PageResult<AuditPageVO> VO分页数据
    */
   default PageResult<AuditPageVO> queryPage(PageParam pageParam, AuditQO qo){
		IPage<Audit> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<Audit> wrapper = WrappersX.lambdaQueryX(Audit.class);
		this.selectPage(page, wrapper);
		IPage<AuditPageVO> voPage = page.convert(AuditConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
   }
}