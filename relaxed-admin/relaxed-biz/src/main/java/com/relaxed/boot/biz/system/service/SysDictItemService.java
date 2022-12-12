package com.relaxed.boot.biz.system.service;

import com.relaxed.boot.common.system.model.entity.SysDictItem;
import com.relaxed.boot.common.system.model.vo.SysDictItemPageVO;
import com.relaxed.boot.common.system.model.qo.SysDictItemQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

import java.util.List;

/**
 * 字典项
 *
 * @author yakir 2022-11-11 10:27:53
 */
public interface SysDictItemService extends ExtendService<SysDictItem> {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult&lt;SysDictItemPageVO&gt; 分页数据
	 */
	PageResult<SysDictItemPageVO> queryPage(PageParam pageParam, SysDictItemQO qo);

	/**
	 * 根据Code查询对应字典项数据
	 * @param dictCode 字典标识
	 * @return 字典项集合
	 */
	List<SysDictItem> listByDictCode(String dictCode);

	/**
	 * 判断字典项是否存在
	 * @param dictCode 字典标识
	 * @return 存在返回 true
	 */
	boolean exist(String dictCode);

}
