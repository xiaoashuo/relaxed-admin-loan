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
 * @author yakir 2023-01-10 10:32:31
 */
@Service
public class TradeServiceImpl extends ExtendServiceImpl<TradeMapper, Trade> implements TradeService {

	@Override
	public PageResult<TradePageVO> queryRepaymentPage(PageParam pageParam, TradeQO qo) {
		return baseMapper.queryRepaymentPage(pageParam, qo);
	}

	@Override
	public PageResult<TradePageVO> queryLoanPage(PageParam pageParam, TradeQO qo) {
		return baseMapper.queryLoanPage(pageParam, qo);
	}

}
