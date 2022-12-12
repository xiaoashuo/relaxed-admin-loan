package com.relaxed.boot.biz.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.common.system.converter.SysDictItemConverter;
import com.relaxed.boot.common.system.model.entity.SysDictItem;
import com.relaxed.boot.common.system.model.qo.SysDictItemQO;
import com.relaxed.boot.common.system.model.vo.SysDictItemPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

import java.util.List;

/**
 * 字典项
 *
 * @author yakir 2022-11-11 10:27:53
 */
public interface SysDictItemMapper extends ExtendMapper<SysDictItem> {

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param qo 查询参数
	 * @return PageResult<SysDictItemPageVO> VO分页数据
	 */
	default PageResult<SysDictItemPageVO> queryPage(PageParam pageParam, SysDictItemQO qo) {
		IPage<SysDictItem> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<SysDictItem> wrapper = WrappersX.lambdaQueryX(SysDictItem.class);
		wrapper.eqIfPresent(SysDictItem::getDictCode, qo.getDictCode());
		this.selectPage(page, wrapper);
		IPage<SysDictItemPageVO> voPage = page.convert(SysDictItemConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

	/**
	 * 根据字典标识查询对应字典项集合
	 * @param dictCode 字典标识
	 * @return List<SysDictItem> 字典项集合
	 */
	default List<SysDictItem> listByDictCode(String dictCode) {
		return this.selectList(Wrappers.<SysDictItem>lambdaQuery().eq(SysDictItem::getDictCode, dictCode));
	}

	/**
	 * 判断是否存在指定字典标识的字典项
	 * @param dictCode 字典标识
	 * @return boolean 存在：true
	 */
	default boolean existsDictItem(String dictCode) {
		return this.exists(Wrappers.lambdaQuery(SysDictItem.class).eq(SysDictItem::getDictCode, dictCode));
	}

}
