package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.loan.converter.SealConverter;
import com.relaxed.boot.loan.model.entity.Seal;
import com.relaxed.boot.loan.model.qo.SealQO;
import com.relaxed.boot.loan.model.vo.SealPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 签章图片表
 *
 * @author yakir 2022-12-15 10:25:25
 */
public interface SealMapper extends ExtendMapper<Seal> {

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param qo 查询参数
	 * @return PageResult<SealPageVO> VO分页数据
	 */
	default PageResult<SealPageVO> queryPage(PageParam pageParam, SealQO qo) {
		IPage<Seal> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<Seal> wrapper = WrappersX.lambdaQueryX(Seal.class);
		this.selectPage(page, wrapper);
		IPage<SealPageVO> voPage = page.convert(SealConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

}