package com.relaxed.boot.loan.service;

import com.relaxed.boot.loan.model.entity.OrderCustomer;
import com.relaxed.boot.loan.model.vo.OrderCustomerPageVO;
import com.relaxed.boot.loan.model.qo.OrderCustomerQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

/**
 * @author yakir 2023-01-10 10:32:19
 */
public interface OrderCustomerService extends ExtendService<OrderCustomer> {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult&lt;OrderCustomerPageVO&gt; 分页数据
	 */
	PageResult<OrderCustomerPageVO> queryPage(PageParam pageParam, OrderCustomerQO qo);

	OrderCustomer getByOrderId(Long orderId);

	OrderCustomerPageVO getPageVoByOrderId(Long orderId);

}
