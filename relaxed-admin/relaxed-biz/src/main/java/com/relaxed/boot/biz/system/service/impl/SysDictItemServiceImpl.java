package com.relaxed.boot.biz.system.service.impl;

import com.relaxed.boot.common.system.model.entity.SysDictItem;
import com.relaxed.boot.common.system.model.vo.SysDictItemPageVO;
import com.relaxed.boot.common.system.model.qo.SysDictItemQO;
import com.relaxed.boot.biz.system.mapper.SysDictItemMapper;
import com.relaxed.boot.biz.system.service.SysDictItemService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典项
 *
 * @author yakir 2022-11-11 10:27:53
 */
@Service
public class SysDictItemServiceImpl extends ExtendServiceImpl<SysDictItemMapper, SysDictItem>
		implements SysDictItemService {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult<SysDictItemPageVO> 分页数据
	 */
	@Override
	public PageResult<SysDictItemPageVO> queryPage(PageParam pageParam, SysDictItemQO qo) {
		return baseMapper.queryPage(pageParam, qo);
	}

	/**
	 * 根据Code查询对应字典项数据
	 * @param dictCode 字典标识
	 * @return 字典项集合
	 */
	@Override
	public List<SysDictItem> listByDictCode(String dictCode) {
		return baseMapper.listByDictCode(dictCode);
	}

	/**
	 * 判断字典项是否存在
	 * @param dictCode 字典标识
	 * @return 存在返回 true
	 */
	@Override
	public boolean exist(String dictCode) {
		return baseMapper.existsDictItem(dictCode);
	}

}
