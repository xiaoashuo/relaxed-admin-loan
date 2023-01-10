package com.relaxed.boot.loan.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.loan.model.entity.OrderCustomer;
import com.relaxed.boot.loan.model.vo.OrderCustomerPageVO;
import com.relaxed.boot.loan.model.qo.OrderCustomerQO;
import com.relaxed.boot.loan.mapper.OrderCustomerMapper;
import com.relaxed.boot.loan.service.OrderCustomerService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Service
public class OrderCustomerServiceImpl extends ExtendServiceImpl<OrderCustomerMapper, OrderCustomer> implements OrderCustomerService {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult<OrderCustomerPageVO> 分页数据
    */
    @Override
    public PageResult<OrderCustomerPageVO> queryPage(PageParam pageParam, OrderCustomerQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

    @Override
    public OrderCustomer getByOrderId(Long orderId) {
        return getOne(Wrappers.<OrderCustomer>lambdaQuery().eq(OrderCustomer::getOrderId, orderId));
    }
}
