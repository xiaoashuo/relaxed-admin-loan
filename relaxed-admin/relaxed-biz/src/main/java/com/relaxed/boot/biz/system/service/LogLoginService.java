package com.relaxed.boot.biz.system.service;

import com.relaxed.boot.common.system.model.entity.LogLogin;
import com.relaxed.boot.common.system.model.vo.LogLoginPageVO;
import com.relaxed.boot.common.system.model.qo.LogLoginQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

/**
 * 登陆日志
 *
 * @author Yakir 2022-11-22 14:02:00
 */
public interface LogLoginService extends ExtendService<LogLogin> {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult&lt;LogLoginPageVO&gt; 分页数据
	 */
	PageResult<LogLoginPageVO> queryPage(PageParam pageParam, LogLoginQO qo);

}
