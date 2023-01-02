package com.relaxed.boot.biz.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.common.system.converter.LogLoginConverter;
import com.relaxed.boot.common.system.model.entity.LogLogin;
import com.relaxed.boot.common.system.model.qo.LogLoginQO;
import com.relaxed.boot.common.system.model.vo.LogLoginPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 登陆日志
 *
 * @author Yakir 2022-11-22 14:02:00
 */
public interface LogLoginMapper extends ExtendMapper<LogLogin> {

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param qo 查询参数
	 * @return PageResult<LogLoginPageVO> VO分页数据
	 */
	default PageResult<LogLoginPageVO> queryPage(PageParam pageParam, LogLoginQO qo) {
		IPage<LogLogin> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<LogLogin> wrapper = WrappersX.lambdaQueryX(LogLogin.class);
		wrapper.eqIfPresent(LogLogin::getTraceId, qo.getTraceId());
		wrapper.eqIfPresent(LogLogin::getIp, qo.getIp());
		this.selectPage(page, wrapper);
		IPage<LogLoginPageVO> voPage = page.convert(LogLoginConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

}
