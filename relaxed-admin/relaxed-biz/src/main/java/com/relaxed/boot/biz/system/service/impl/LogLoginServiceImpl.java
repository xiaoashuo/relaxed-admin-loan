package com.relaxed.boot.biz.system.service.impl;

import com.relaxed.boot.biz.system.mapper.LogLoginMapper;
import com.relaxed.boot.biz.system.service.LogLoginService;
import com.relaxed.boot.common.system.model.entity.LogLogin;
import com.relaxed.boot.common.system.model.vo.LogLoginPageVO;
import com.relaxed.boot.common.system.model.qo.LogLoginQO;

import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 登陆日志
 *
 * @author Yakir 2022-11-22 14:02:00
 */
@Service
public class LogLoginServiceImpl extends ExtendServiceImpl<LogLoginMapper, LogLogin> implements LogLoginService {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult<LogLoginPageVO> 分页数据
	 */
	@Override
	public PageResult<LogLoginPageVO> queryPage(PageParam pageParam, LogLoginQO qo) {
		return baseMapper.queryPage(pageParam, qo);
	}

}
