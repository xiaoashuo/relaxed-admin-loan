package com.relaxed.boot.biz.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.common.system.converter.LogAccessConverter;
import com.relaxed.boot.common.system.model.entity.LogAccess;
import com.relaxed.boot.common.system.model.entity.LogLogin;
import com.relaxed.boot.common.system.model.qo.LogAccessQO;
import com.relaxed.boot.common.system.model.vo.LogAccessPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 访问日志
 *
 * @author yakir 2022-11-11 10:27:36
 */
public interface LogAccessMapper extends ExtendMapper<LogAccess> {

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param qo 查询参数
	 * @return PageResult<LogAccessPageVO> VO分页数据
	 */
	default PageResult<LogAccessPageVO> queryPage(PageParam pageParam, LogAccessQO qo) {
		IPage<LogAccess> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<LogAccess> wrapper = WrappersX.lambdaQueryX(LogAccess.class);
		wrapper.eqIfPresent(LogAccess::getTraceId, qo.getTraceId());
		wrapper.eqIfPresent(LogAccess::getIp, qo.getIp());
		this.selectPage(page, wrapper);
		IPage<LogAccessPageVO> voPage = page.convert(LogAccessConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

}
