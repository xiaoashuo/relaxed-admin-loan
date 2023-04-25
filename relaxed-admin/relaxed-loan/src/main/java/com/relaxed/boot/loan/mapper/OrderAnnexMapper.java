package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.loan.converter.OrderAnnexConverter;
import com.relaxed.boot.loan.model.entity.OrderAnnex;
import com.relaxed.boot.loan.model.qo.OrderAnnexQO;
import com.relaxed.boot.loan.model.vo.OrderAnnexPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * @author yakir 2023-01-10 10:32:19
 */
public interface OrderAnnexMapper extends ExtendMapper<OrderAnnex> {

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param qo 查询参数
	 * @return PageResult<OrderAnnexPageVO> VO分页数据
	 */
	default PageResult<OrderAnnexPageVO> queryPage(PageParam pageParam, OrderAnnexQO qo) {
		IPage<OrderAnnex> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<OrderAnnex> wrapper = WrappersX.lambdaQueryX(OrderAnnex.class);
		wrapper.eq(OrderAnnex::getOrderId, qo.getOrderId());
		wrapper.likeIfPresent(OrderAnnex::getFileName, qo.getFileName());
		this.selectPage(page, wrapper);
		IPage<OrderAnnexPageVO> voPage = page.convert(OrderAnnexConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

}
