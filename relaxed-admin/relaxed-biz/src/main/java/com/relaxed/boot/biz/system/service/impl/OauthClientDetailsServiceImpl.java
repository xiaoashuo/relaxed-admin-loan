package com.relaxed.boot.biz.system.service.impl;

import com.relaxed.boot.common.system.model.entity.OauthClientDetails;
import com.relaxed.boot.common.system.model.vo.OauthClientDetailsPageVO;
import com.relaxed.boot.common.system.model.qo.OauthClientDetailsQO;
import com.relaxed.boot.biz.system.mapper.OauthClientDetailsMapper;
import com.relaxed.boot.biz.system.service.OauthClientDetailsService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 接入客户端信息
 *
 * @author yakir 2022-11-11 10:27:36
 */
@Service
public class OauthClientDetailsServiceImpl extends ExtendServiceImpl<OauthClientDetailsMapper, OauthClientDetails>
		implements OauthClientDetailsService {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult<OauthClientDetailsPageVO> 分页数据
	 */
	@Override
	public PageResult<OauthClientDetailsPageVO> queryPage(PageParam pageParam, OauthClientDetailsQO qo) {
		return baseMapper.queryPage(pageParam, qo);
	}

}
