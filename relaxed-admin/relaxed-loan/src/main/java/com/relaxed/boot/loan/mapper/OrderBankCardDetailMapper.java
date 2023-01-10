package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.loan.converter.OrderBankCardDetailConverter;
import com.relaxed.boot.loan.model.entity.OrderBankCardDetail;
import com.relaxed.boot.loan.model.qo.OrderBankCardDetailQO;
import com.relaxed.boot.loan.model.vo.OrderBankCardDetailPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 订单银行卡放款详情表
 *
 * @author yakir 2023-01-10 10:32:19
 */
public interface OrderBankCardDetailMapper extends ExtendMapper<OrderBankCardDetail> {

    /**
    * 分页查询
    * @param pageParam 分页参数
    * @param qo 查询参数
    * @return PageResult<OrderBankCardDetailPageVO> VO分页数据
    */
   default PageResult<OrderBankCardDetailPageVO> queryPage(PageParam pageParam, OrderBankCardDetailQO qo){
		IPage<OrderBankCardDetail> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<OrderBankCardDetail> wrapper = WrappersX.lambdaQueryX(OrderBankCardDetail.class);
		this.selectPage(page, wrapper);
		IPage<OrderBankCardDetailPageVO> voPage = page.convert(OrderBankCardDetailConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
   }
}