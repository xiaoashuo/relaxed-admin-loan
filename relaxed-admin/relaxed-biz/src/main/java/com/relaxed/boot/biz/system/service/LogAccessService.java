package com.relaxed.boot.biz.system.service;

import com.relaxed.boot.common.system.model.entity.LogAccess;
import com.relaxed.boot.common.system.model.vo.LogAccessPageVO;
import com.relaxed.boot.common.system.model.qo.LogAccessQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

/**
 * 访问日志
 *
 * @author yakir 2022-11-11 10:27:36
 */
public interface LogAccessService extends ExtendService<LogAccess> {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult&lt;LogAccessPageVO&gt; 分页数据
	 */
	PageResult<LogAccessPageVO> queryPage(PageParam pageParam, LogAccessQO qo);

}
