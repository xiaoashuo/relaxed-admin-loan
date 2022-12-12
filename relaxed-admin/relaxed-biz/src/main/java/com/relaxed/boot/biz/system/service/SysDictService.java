package com.relaxed.boot.biz.system.service;

import com.relaxed.boot.common.system.model.entity.SysDict;
import com.relaxed.boot.common.system.model.vo.SysDictPageVO;
import com.relaxed.boot.common.system.model.qo.SysDictQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

import java.util.List;

/**
 * 字典表
 *
 * @author yakir 2022-11-11 10:27:36
 */
public interface SysDictService extends ExtendService<SysDict> {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult&lt;SysDictPageVO&gt; 分页数据
	 */
	PageResult<SysDictPageVO> queryPage(PageParam pageParam, SysDictQO qo);

	/**
	 * 根据字典标识数组查询对应字典集合
	 * @param dictCodes 字典标识数组
	 * @return List<SysDict> 字典集合
	 */
	List<SysDict> listByCodes(String[] dictCodes);

	/**
	 * 更新字典HashCode
	 * @param dictCode 字典标识
	 * @return 更新状态 成功（true） or 失败 (false)
	 */
	boolean updateHashCode(String dictCode);

}
