package com.relaxed.boot.biz.system.service.impl;

import com.relaxed.boot.biz.system.mapper.SysConfigMapper;
import com.relaxed.boot.biz.system.service.SysConfigService;
import com.relaxed.boot.common.system.model.entity.SysConfig;
import com.relaxed.boot.common.system.model.qo.SysConfigQO;
import com.relaxed.boot.common.system.model.vo.SysConfigPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 基础配置
 *
 * @author yakir 2022-11-15 10:24:40
 */
@Service
public class SysConfigServiceImpl extends ExtendServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult<SysConfigPageVO> 分页数据
	 */
	@Override
	public PageResult<SysConfigPageVO> queryPage(PageParam pageParam, SysConfigQO qo) {
		return baseMapper.queryPage(pageParam, qo);
	}

}
