package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.loan.converter.TradeConverter;
import com.relaxed.boot.loan.model.entity.Trade;
import com.relaxed.boot.loan.model.qo.TradeQO;
import com.relaxed.boot.loan.model.vo.TradePageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

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
   default PageResult<TradePageVO> queryPage(PageParam pageParam, TradeQO qo){
		IPage<Trade> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<Trade> wrapper = WrappersX.lambdaQueryX(Trade.class);
		this.selectPage(page, wrapper);
		IPage<TradePageVO> voPage = page.convert(TradeConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
   }
}