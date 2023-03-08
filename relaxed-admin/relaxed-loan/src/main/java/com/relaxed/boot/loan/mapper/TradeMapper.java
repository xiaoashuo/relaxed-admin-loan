package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;

import com.relaxed.boot.loan.enums.LoanEnum;
import com.relaxed.boot.loan.model.entity.Trade;
import com.relaxed.boot.loan.model.qo.TradeQO;
import com.relaxed.boot.loan.model.vo.TradePageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaAliasQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;
import org.apache.ibatis.annotations.Param;

/**
 * 
 *
 * @author yakir 2023-01-10 10:32:31
 */
public interface TradeMapper extends ExtendMapper<Trade> {

    /**
    * 分页查询
    * @param pageParam 分页参数
    * @param qo 查询参数
    * @return PageResult<TradePageVO> VO分页数据
    */
   default PageResult<TradePageVO> queryRepaymentPage(PageParam pageParam, TradeQO qo){
	   IPage<TradePageVO> page = this.prodPage(pageParam);
	   LambdaAliasQueryWrapperX<Trade> wrapper = WrappersX.lambdaAliasQueryX(Trade.class);
	   wrapper.eq(Trade::getLoanPurpose, LoanEnum.LoanPurpose.REPAYMENT.getVal());
	   wrapper.eqIfPresent(Trade::getOrderId,qo.getOrderId());
	   wrapper.eqIfPresent(Trade::getPartnerBizNo,qo.getPartnerBizNo());
	   wrapper.eqIfPresent(Trade::getTradeNo,qo.getTradeNo());
	   this.selectByPage(page, wrapper);
	   return new PageResult<>(page.getRecords(), page.getTotal());
   }
	default PageResult<TradePageVO> queryLoanPage(PageParam pageParam, TradeQO qo){
		IPage<TradePageVO> page = this.prodPage(pageParam);
		LambdaAliasQueryWrapperX<Trade> wrapper = WrappersX.lambdaAliasQueryX(Trade.class);
		wrapper.eq(Trade::getLoanPurpose, LoanEnum.LoanPurpose.LOAN.getVal());
		wrapper.eqIfPresent(Trade::getOrderId,qo.getOrderId());
		wrapper.eqIfPresent(Trade::getPartnerBizNo,qo.getPartnerBizNo());
		wrapper.eqIfPresent(Trade::getTradeNo,qo.getTradeNo());
		this.selectByPage(page, wrapper);
		return new PageResult<>(page.getRecords(), page.getTotal());
	}

	IPage<TradePageVO> selectByPage(IPage<TradePageVO> page,
									@Param(Constants.WRAPPER) Wrapper<Trade> queryWrapper);


}
