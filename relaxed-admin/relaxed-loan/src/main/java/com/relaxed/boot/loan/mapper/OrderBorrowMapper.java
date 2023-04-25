package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.loan.converter.OrderBorrowConverter;
import com.relaxed.boot.loan.model.entity.OrderBorrow;
import com.relaxed.boot.loan.model.qo.OrderBorrowQO;
import com.relaxed.boot.loan.model.vo.OrderBorrowPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 共同借款人
 *
 * @author yakir 2023-01-10 10:32:19
 */
public interface OrderBorrowMapper extends ExtendMapper<OrderBorrow> {

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param qo 查询参数
	 * @return PageResult<OrderBorrowPageVO> VO分页数据
	 */
	default PageResult<OrderBorrowPageVO> queryPage(PageParam pageParam, OrderBorrowQO qo) {
		IPage<OrderBorrow> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<OrderBorrow> wrapper = WrappersX.lambdaQueryX(OrderBorrow.class);
		wrapper.eq(OrderBorrow::getOrderId, qo.getOrderId());
		wrapper.eqIfPresent(OrderBorrow::getRelatedName, qo.getRelatedName());
		this.selectPage(page, wrapper);
		IPage<OrderBorrowPageVO> voPage = page.convert(OrderBorrowConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

}
