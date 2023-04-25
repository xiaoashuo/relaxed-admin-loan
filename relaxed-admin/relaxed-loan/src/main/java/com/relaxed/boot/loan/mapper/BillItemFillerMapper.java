package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.loan.converter.BillItemFillerConverter;
import com.relaxed.boot.loan.model.entity.BillItemFiller;
import com.relaxed.boot.loan.model.qo.BillItemFillerQO;
import com.relaxed.boot.loan.model.vo.BillItemFillerPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * @author yakir 2023-01-10 10:32:19
 */
public interface BillItemFillerMapper extends ExtendMapper<BillItemFiller> {

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param qo 查询参数
	 * @return PageResult<BillItemFillerPageVO> VO分页数据
	 */
	default PageResult<BillItemFillerPageVO> queryPage(PageParam pageParam, BillItemFillerQO qo) {
		IPage<BillItemFiller> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<BillItemFiller> wrapper = WrappersX.lambdaQueryX(BillItemFiller.class);
		this.selectPage(page, wrapper);
		IPage<BillItemFillerPageVO> voPage = page.convert(BillItemFillerConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

}