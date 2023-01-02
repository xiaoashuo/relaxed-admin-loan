package com.relaxed.boot.biz.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.common.system.converter.LogOperationConverter;
import com.relaxed.boot.common.system.model.entity.LogOperation;
import com.relaxed.boot.common.system.model.qo.LogAccessQO;
import com.relaxed.boot.common.system.model.qo.LogOperationQO;
import com.relaxed.boot.common.system.model.vo.LogOperationPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 操作日志
 *
 * @author yakir 2022-11-11 10:27:36
 */
public interface LogOperationMapper extends ExtendMapper<LogOperation> {

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param qo 查询参数
	 * @return PageResult<LogOperationPageVO> VO分页数据
	 */
	default PageResult<LogOperationPageVO> queryPage(PageParam pageParam, LogOperationQO qo) {
		IPage<LogOperation> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<LogOperation> wrapper = WrappersX.lambdaQueryX(LogOperation.class);
		wrapper.eqIfPresent(LogOperation::getTraceId, qo.getTraceId());
		wrapper.eqIfPresent(LogOperation::getIp, qo.getIp());
		this.selectPage(page, wrapper);
		IPage<LogOperationPageVO> voPage = page.convert(LogOperationConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

}
