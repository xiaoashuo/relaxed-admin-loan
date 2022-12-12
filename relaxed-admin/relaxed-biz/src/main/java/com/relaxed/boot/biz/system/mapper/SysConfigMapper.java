package com.relaxed.boot.biz.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.relaxed.boot.common.system.converter.SysConfigConverter;
import com.relaxed.boot.common.system.model.entity.SysConfig;
import com.relaxed.boot.common.system.model.qo.SysConfigQO;
import com.relaxed.boot.common.system.model.vo.SysConfigPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 基础配置
 *
 * @author yakir 2022-11-15 10:24:40
 */
public interface SysConfigMapper extends ExtendMapper<SysConfig> {

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param qo 查询参数
	 * @return PageResult<SysConfigPageVO> VO分页数据
	 */
	default PageResult<SysConfigPageVO> queryPage(PageParam pageParam, SysConfigQO qo) {
		IPage<SysConfig> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<SysConfig> wrapper = WrappersX.lambdaQueryX(SysConfig.class);
		wrapper.eqIfPresent(SysConfig::getConfKey, qo.getConfKey());
		wrapper.eqIfPresent(SysConfig::getName, qo.getName());
		this.selectPage(page, wrapper);
		IPage<SysConfigPageVO> voPage = page.convert(SysConfigConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

}
