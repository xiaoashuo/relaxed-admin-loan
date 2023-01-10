package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.loan.converter.OrderCollateralConverter;
import com.relaxed.boot.loan.model.entity.OrderCollateral;
import com.relaxed.boot.loan.model.qo.OrderCollateralQO;
import com.relaxed.boot.loan.model.vo.OrderCollateralPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 
 *
 * @author yakir 2023-01-10 10:32:31
 */
public interface OrderCollateralMapper extends ExtendMapper<OrderCollateral> {

    /**
    * 分页查询
    * @param pageParam 分页参数
    * @param qo 查询参数
    * @return PageResult<OrderCollateralPageVO> VO分页数据
    */
   default PageResult<OrderCollateralPageVO> queryPage(PageParam pageParam, OrderCollateralQO qo){
		IPage<OrderCollateral> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<OrderCollateral> wrapper = WrappersX.lambdaQueryX(OrderCollateral.class);
		this.selectPage(page, wrapper);
		IPage<OrderCollateralPageVO> voPage = page.convert(OrderCollateralConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
   }
}