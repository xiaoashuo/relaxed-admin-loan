package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.loan.converter.AuditDetailConverter;
import com.relaxed.boot.loan.model.entity.AuditDetail;
import com.relaxed.boot.loan.model.qo.AuditDetailQO;
import com.relaxed.boot.loan.model.vo.AuditDetailPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 审核拒绝原因表
 *
 * @author yakir 2023-01-10 10:32:31
 */
public interface AuditDetailMapper extends ExtendMapper<AuditDetail> {

    /**
    * 分页查询
    * @param pageParam 分页参数
    * @param qo 查询参数
    * @return PageResult<AuditDetailPageVO> VO分页数据
    */
   default PageResult<AuditDetailPageVO> queryPage(PageParam pageParam, AuditDetailQO qo){
		IPage<AuditDetail> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<AuditDetail> wrapper = WrappersX.lambdaQueryX(AuditDetail.class);
		this.selectPage(page, wrapper);
		IPage<AuditDetailPageVO> voPage = page.convert(AuditDetailConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
   }
}