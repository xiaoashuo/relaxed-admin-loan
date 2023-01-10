package com.relaxed.boot.loan.service.impl;

import com.relaxed.boot.loan.model.entity.Trade;
import com.relaxed.boot.loan.model.vo.TradePageVO;
import com.relaxed.boot.loan.model.qo.TradeQO;
import com.relaxed.boot.loan.mapper.TradeMapper;
import com.relaxed.boot.loan.service.TradeService;
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
public class TradeServiceImpl extends ExtendServiceImpl<TradeMapper, Trade> implements TradeService {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult<TradePageVO> 分页数据
    */
    @Override
    public PageResult<TradePageVO> queryPage(PageParam pageParam, TradeQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

}
