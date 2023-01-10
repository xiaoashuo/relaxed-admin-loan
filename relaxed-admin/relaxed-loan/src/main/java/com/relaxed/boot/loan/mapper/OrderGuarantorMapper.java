package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.loan.converter.OrderGuarantorConverter;
import com.relaxed.boot.loan.model.entity.OrderGuarantor;
import com.relaxed.boot.loan.model.qo.OrderGuarantorQO;
import com.relaxed.boot.loan.model.vo.OrderGuarantorPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 担保机构
 *
 * @author yakir 2023-01-10 10:32:19
 */
public interface OrderGuarantorMapper extends ExtendMapper<OrderGuarantor> {

    /**
    * 分页查询
    * @param pageParam 分页参数
    * @param qo 查询参数
    * @return PageResult<OrderGuarantorPageVO> VO分页数据
    */
   default PageResult<OrderGuarantorPageVO> queryPage(PageParam pageParam, OrderGuarantorQO qo){
		IPage<OrderGuarantor> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<OrderGuarantor> wrapper = WrappersX.lambdaQueryX(OrderGuarantor.class);
		this.selectPage(page, wrapper);
		IPage<OrderGuarantorPageVO> voPage = page.convert(OrderGuarantorConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
   }
}