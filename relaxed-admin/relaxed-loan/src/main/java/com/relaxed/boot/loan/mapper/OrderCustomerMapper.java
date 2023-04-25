package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.loan.converter.OrderCustomerConverter;
import com.relaxed.boot.loan.model.entity.OrderCustomer;
import com.relaxed.boot.loan.model.qo.OrderCustomerQO;
import com.relaxed.boot.loan.model.vo.OrderCustomerPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * @author yakir 2023-01-10 10:32:19
 */
public interface OrderCustomerMapper extends ExtendMapper<OrderCustomer> {

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param qo 查询参数
	 * @return PageResult<OrderCustomerPageVO> VO分页数据
	 */
	default PageResult<OrderCustomerPageVO> queryPage(PageParam pageParam, OrderCustomerQO qo) {
		IPage<OrderCustomer> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<OrderCustomer> wrapper = WrappersX.lambdaQueryX(OrderCustomer.class);
		this.selectPage(page, wrapper);
		IPage<OrderCustomerPageVO> voPage = page.convert(OrderCustomerConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

}