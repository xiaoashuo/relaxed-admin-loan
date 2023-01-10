package com.relaxed.boot.loan.service.impl;

import com.relaxed.boot.loan.model.entity.OrderBankCardDetail;
import com.relaxed.boot.loan.model.vo.OrderBankCardDetailPageVO;
import com.relaxed.boot.loan.model.qo.OrderBankCardDetailQO;
import com.relaxed.boot.loan.mapper.OrderBankCardDetailMapper;
import com.relaxed.boot.loan.service.OrderBankCardDetailService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 订单银行卡放款详情表
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Service
public class OrderBankCardDetailServiceImpl extends ExtendServiceImpl<OrderBankCardDetailMapper, OrderBankCardDetail> implements OrderBankCardDetailService {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult<OrderBankCardDetailPageVO> 分页数据
    */
    @Override
    public PageResult<OrderBankCardDetailPageVO> queryPage(PageParam pageParam, OrderBankCardDetailQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

}
