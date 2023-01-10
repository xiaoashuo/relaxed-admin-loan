package com.relaxed.boot.loan.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.loan.model.entity.OrderGuarantor;
import com.relaxed.boot.loan.model.vo.OrderGuarantorPageVO;
import com.relaxed.boot.loan.model.qo.OrderGuarantorQO;
import com.relaxed.boot.loan.mapper.OrderGuarantorMapper;
import com.relaxed.boot.loan.service.OrderGuarantorService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 担保机构
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Service
public class OrderGuarantorServiceImpl extends ExtendServiceImpl<OrderGuarantorMapper, OrderGuarantor> implements OrderGuarantorService {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult<OrderGuarantorPageVO> 分页数据
    */
    @Override
    public PageResult<OrderGuarantorPageVO> queryPage(PageParam pageParam, OrderGuarantorQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

    @Override
    public OrderGuarantor getByOrderId(Long orderId) {
        return getOne(Wrappers.lambdaQuery(OrderGuarantor.class )
                .eq(OrderGuarantor::getOrderId,orderId));
    }
}
