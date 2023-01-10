package com.relaxed.boot.loan.service;

import com.relaxed.boot.loan.model.entity.Order;
import com.relaxed.boot.loan.model.vo.OrderDetailVO;
import com.relaxed.boot.loan.model.vo.OrderPageVO;
import com.relaxed.boot.loan.model.qo.OrderQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

/**
 * 
 *
 * @author yakir 2023-01-10 10:32:31
 */
public interface OrderService extends ExtendService<Order> {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult&lt;OrderPageVO&gt; 分页数据
    */
    PageResult<OrderPageVO> queryPage(PageParam pageParam, OrderQO qo);

    Order getByPartnerBizNo(String partnerBizNo);

    OrderDetailVO getOrderDetail(Long orderId);
}
