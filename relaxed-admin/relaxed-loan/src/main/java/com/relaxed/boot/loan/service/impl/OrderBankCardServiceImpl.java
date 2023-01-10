package com.relaxed.boot.loan.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.loan.model.entity.OrderBankCard;
import com.relaxed.boot.loan.model.vo.OrderBankCardPageVO;
import com.relaxed.boot.loan.model.qo.OrderBankCardQO;
import com.relaxed.boot.loan.mapper.OrderBankCardMapper;
import com.relaxed.boot.loan.service.OrderBankCardService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Service
public class OrderBankCardServiceImpl extends ExtendServiceImpl<OrderBankCardMapper, OrderBankCard> implements OrderBankCardService {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult<OrderBankCardPageVO> 分页数据
    */
    @Override
    public PageResult<OrderBankCardPageVO> queryPage(PageParam pageParam, OrderBankCardQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

    @Override
    public List<OrderBankCard> listByOrderId(Long orderId) {
        return list(Wrappers.lambdaQuery(OrderBankCard.class )
                .eq(OrderBankCard::getOrderId,orderId));
    }
}
