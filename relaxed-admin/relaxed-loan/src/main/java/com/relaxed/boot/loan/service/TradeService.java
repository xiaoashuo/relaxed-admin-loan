package com.relaxed.boot.loan.service;

import com.relaxed.boot.loan.model.entity.Trade;
import com.relaxed.boot.loan.model.vo.TradePageVO;
import com.relaxed.boot.loan.model.qo.TradeQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

/**
 * 
 *
 * @author yakir 2023-01-10 10:32:31
 */
public interface TradeService extends ExtendService<Trade> {



    PageResult<TradePageVO> queryRepaymentPage(PageParam pageParam, TradeQO tradeQO);
    PageResult<TradePageVO> queryLoanPage(PageParam pageParam, TradeQO tradeQO);
}
