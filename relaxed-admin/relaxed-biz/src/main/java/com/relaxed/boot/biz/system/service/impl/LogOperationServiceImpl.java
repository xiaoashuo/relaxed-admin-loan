package com.relaxed.boot.biz.system.service.impl;

import com.relaxed.boot.common.system.model.entity.LogOperation;
import com.relaxed.boot.common.system.model.vo.LogOperationPageVO;
import com.relaxed.boot.common.system.model.qo.LogOperationQO;
import com.relaxed.boot.biz.system.mapper.LogOperationMapper;
import com.relaxed.boot.biz.system.service.LogOperationService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 操作日志
 *
 * @author yakir 2022-11-11 10:27:36
 */
@Service
public class LogOperationServiceImpl extends ExtendServiceImpl<LogOperationMapper, LogOperation>
		implements LogOperationService {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult<LogOperationPageVO> 分页数据
	 */
	@Override
	public PageResult<LogOperationPageVO> queryPage(PageParam pageParam, LogOperationQO qo) {
		return baseMapper.queryPage(pageParam, qo);
	}

}
