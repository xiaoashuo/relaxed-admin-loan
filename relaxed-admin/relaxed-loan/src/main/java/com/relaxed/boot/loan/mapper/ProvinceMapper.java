package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.loan.converter.ProvinceConverter;
import com.relaxed.boot.loan.model.entity.Province;
import com.relaxed.boot.loan.model.qo.ProvinceQO;
import com.relaxed.boot.loan.model.vo.ProvincePageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 省市区码表
 *
 * @author Yakir 2023-03-08 10:44:09
 */
public interface ProvinceMapper extends ExtendMapper<Province> {

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param qo 查询参数
	 * @return PageResult<ProvincePageVO> VO分页数据
	 */
	default PageResult<ProvincePageVO> queryPage(PageParam pageParam, ProvinceQO qo) {
		IPage<Province> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<Province> wrapper = WrappersX.lambdaQueryX(Province.class);
		this.selectPage(page, wrapper);
		IPage<ProvincePageVO> voPage = page.convert(ProvinceConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

}