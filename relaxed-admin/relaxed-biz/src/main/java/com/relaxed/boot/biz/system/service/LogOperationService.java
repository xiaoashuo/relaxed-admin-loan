package com.relaxed.boot.biz.system.service;

import com.relaxed.boot.common.system.model.entity.LogOperation;
import com.relaxed.boot.common.system.model.vo.LogOperationPageVO;
import com.relaxed.boot.common.system.model.qo.LogOperationQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

/**
 * 操作日志
 *
 * @author yakir 2022-11-11 10:27:36
 */
public interface LogOperationService extends ExtendService<LogOperation> {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult&lt;LogOperationPageVO&gt; 分页数据
	 */
	PageResult<LogOperationPageVO> queryPage(PageParam pageParam, LogOperationQO qo);

}
