package com.relaxed.boot.biz.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.common.system.converter.OauthClientDetailsConverter;
import com.relaxed.boot.common.system.model.entity.OauthClientDetails;
import com.relaxed.boot.common.system.model.qo.OauthClientDetailsQO;
import com.relaxed.boot.common.system.model.vo.OauthClientDetailsPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 接入客户端信息
 *
 * @author yakir 2022-11-11 10:27:36
 */
public interface OauthClientDetailsMapper extends ExtendMapper<OauthClientDetails> {

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param qo 查询参数
	 * @return PageResult<OauthClientDetailsPageVO> VO分页数据
	 */
	default PageResult<OauthClientDetailsPageVO> queryPage(PageParam pageParam, OauthClientDetailsQO qo) {
		IPage<OauthClientDetails> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<OauthClientDetails> wrapper = WrappersX.lambdaQueryX(OauthClientDetails.class);
		this.selectPage(page, wrapper);
		IPage<OauthClientDetailsPageVO> voPage = page.convert(OauthClientDetailsConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

}
