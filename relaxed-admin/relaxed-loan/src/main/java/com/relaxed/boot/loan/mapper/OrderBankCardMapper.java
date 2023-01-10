package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.loan.converter.OrderBankCardConverter;
import com.relaxed.boot.loan.model.entity.OrderBankCard;
import com.relaxed.boot.loan.model.qo.OrderBankCardQO;
import com.relaxed.boot.loan.model.vo.OrderBankCardPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 
 *
 * @author yakir 2023-01-10 10:32:19
 */
public interface OrderBankCardMapper extends ExtendMapper<OrderBankCard> {

    /**
    * 分页查询
    * @param pageParam 分页参数
    * @param qo 查询参数
    * @return PageResult<OrderBankCardPageVO> VO分页数据
    */
   default PageResult<OrderBankCardPageVO> queryPage(PageParam pageParam, OrderBankCardQO qo){
		IPage<OrderBankCard> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<OrderBankCard> wrapper = WrappersX.lambdaQueryX(OrderBankCard.class);
		this.selectPage(page, wrapper);
		IPage<OrderBankCardPageVO> voPage = page.convert(OrderBankCardConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
   }
}