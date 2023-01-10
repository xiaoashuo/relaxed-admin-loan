package com.relaxed.boot.loan.service.impl;

import com.relaxed.boot.loan.model.entity.OrderCollateral;
import com.relaxed.boot.loan.model.vo.OrderCollateralPageVO;
import com.relaxed.boot.loan.model.qo.OrderCollateralQO;
import com.relaxed.boot.loan.mapper.OrderCollateralMapper;
import com.relaxed.boot.loan.service.OrderCollateralService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 
 *
 * @author yakir 2023-01-10 10:32:31
 */
@Service
public class OrderCollateralServiceImpl extends ExtendServiceImpl<OrderCollateralMapper, OrderCollateral> implements OrderCollateralService {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult<OrderCollateralPageVO> 分页数据
    */
    @Override
    public PageResult<OrderCollateralPageVO> queryPage(PageParam pageParam, OrderCollateralQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

}
