package com.relaxed.boot.biz.system.service;

import com.relaxed.boot.common.system.model.entity.OauthClientDetails;
import com.relaxed.boot.common.system.model.vo.OauthClientDetailsPageVO;
import com.relaxed.boot.common.system.model.qo.OauthClientDetailsQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

/**
 * 接入客户端信息
 *
 * @author yakir 2022-11-11 10:27:36
 */
public interface OauthClientDetailsService extends ExtendService<OauthClientDetails> {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult&lt;OauthClientDetailsPageVO&gt; 分页数据
	 */
	PageResult<OauthClientDetailsPageVO> queryPage(PageParam pageParam, OauthClientDetailsQO qo);

}
