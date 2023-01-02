package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.loan.converter.PartnerConverter;
import com.relaxed.boot.loan.model.entity.Partner;
import com.relaxed.boot.loan.model.qo.PartnerQO;
import com.relaxed.boot.loan.model.vo.PartnerPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 合作方
 *
 * @author yakir 2022-12-14 11:36:23
 */
public interface PartnerMapper extends ExtendMapper<Partner> {

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param qo 查询参数
	 * @return PageResult<PartnerPageVO> VO分页数据
	 */
	default PageResult<PartnerPageVO> queryPage(PageParam pageParam, PartnerQO qo) {
		IPage<Partner> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<Partner> wrapper = WrappersX.lambdaQueryX(Partner.class);
		this.selectPage(page, wrapper);
		IPage<PartnerPageVO> voPage = page.convert(PartnerConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

}