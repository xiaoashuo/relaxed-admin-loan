package com.relaxed.boot.biz.system.service;

import com.relaxed.boot.common.system.model.entity.SysConfig;
import com.relaxed.boot.common.system.model.qo.SysConfigQO;
import com.relaxed.boot.common.system.model.vo.SysConfigPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

/**
 * 基础配置
 *
 * @author yakir 2022-11-15 10:24:40
 */
public interface SysConfigService extends ExtendService<SysConfig> {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult&lt;SysConfigPageVO&gt; 分页数据
	 */
	PageResult<SysConfigPageVO> queryPage(PageParam pageParam, SysConfigQO qo);

}
