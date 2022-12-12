package com.relaxed.boot.biz.system.service.impl;

import com.relaxed.boot.common.system.model.entity.LogAccess;
import com.relaxed.boot.common.system.model.vo.LogAccessPageVO;
import com.relaxed.boot.common.system.model.qo.LogAccessQO;
import com.relaxed.boot.biz.system.mapper.LogAccessMapper;
import com.relaxed.boot.biz.system.service.LogAccessService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 访问日志
 *
 * @author yakir 2022-11-11 10:27:36
 */
@Service
public class LogAccessServiceImpl extends ExtendServiceImpl<LogAccessMapper, LogAccess> implements LogAccessService {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult<LogAccessPageVO> 分页数据
	 */
	@Override
	public PageResult<LogAccessPageVO> queryPage(PageParam pageParam, LogAccessQO qo) {
		return baseMapper.queryPage(pageParam, qo);
	}

}
