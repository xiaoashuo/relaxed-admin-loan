package com.relaxed.boot.biz.system.service.impl;

import cn.hutool.core.util.IdUtil;
import com.relaxed.boot.common.system.model.entity.SysDict;
import com.relaxed.boot.common.system.model.vo.SysDictPageVO;
import com.relaxed.boot.common.system.model.qo.SysDictQO;
import com.relaxed.boot.biz.system.mapper.SysDictMapper;
import com.relaxed.boot.biz.system.service.SysDictService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典表
 *
 * @author yakir 2022-11-11 10:27:36
 */
@Service
public class SysDictServiceImpl extends ExtendServiceImpl<SysDictMapper, SysDict> implements SysDictService {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult<SysDictPageVO> 分页数据
	 */
	@Override
	public PageResult<SysDictPageVO> queryPage(PageParam pageParam, SysDictQO qo) {
		return baseMapper.queryPage(pageParam, qo);
	}

	/**
	 * 根据字典标识数组查询对应字典集合
	 * @param dictCodes 字典标识数组
	 * @return List<SysDict> 字典集合
	 */
	@Override
	public List<SysDict> listByCodes(String[] dictCodes) {
		if (dictCodes == null || dictCodes.length == 0) {
			return new ArrayList<>();
		}
		return baseMapper.listByCodes(dictCodes);
	}

	/**
	 * 更新字典HashCode
	 * @param dictCode 字典标识
	 * @return 更新状态: 成功(true) 失败(false)
	 */
	@Override
	public boolean updateHashCode(String dictCode) {
		return baseMapper.updateHashCode(dictCode, IdUtil.fastSimpleUUID());
	}

}
