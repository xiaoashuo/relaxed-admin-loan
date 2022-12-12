package com.relaxed.boot.web.system.controller;

import com.relaxed.boot.common.system.model.dto.SysDictItemDTO;
import com.relaxed.boot.common.system.model.entity.SysDict;
import com.relaxed.boot.common.system.model.qo.SysDictQO;
import com.relaxed.boot.common.system.model.vo.DictDataVO;
import com.relaxed.boot.common.system.model.vo.SysDictItemPageVO;
import com.relaxed.boot.common.system.model.vo.SysDictPageVO;
import com.relaxed.boot.web.system.manage.SysDictManage;
import com.relaxed.common.core.validation.group.GroupType;
import com.relaxed.common.log.operation.annotation.CreateLog;
import com.relaxed.common.log.operation.annotation.DeleteLog;
import com.relaxed.common.log.operation.annotation.UpdateLog;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.result.BaseResultCode;
import com.relaxed.common.model.result.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.util.List;
import java.util.Map;

/**
 * 字典表
 *
 * @author yakir 2022-11-11 10:27:36
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/dict")
@Tag(name = "字典表管理")
public class SysDictController {

	private final SysDictManage sysDictManage;

	/**
	 * 通过字典标识查找对应字典项
	 * @param dictCodes 字典标识列表
	 * @return 同类型字典
	 */
	@GetMapping("/data")
	public R<List<DictDataVO>> getDictData(@RequestParam("dictCodes") String[] dictCodes) {
		return R.ok(sysDictManage.queryDictDataAndHashVO(dictCodes));
	}

	/**
	 * 通过字典标识查找对应字典项
	 * @param dictHashCode 字典标识
	 * @return 同类型字典
	 */
	@PostMapping("/invalid-hash")
	public R<List<String>> invalidDictHash(@RequestBody Map<String, String> dictHashCode) {
		return R.ok(sysDictManage.invalidDictHash(dictHashCode));
	}

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param sysDictQO 字典查询参数
	 * @return R<PageResult<SysDictVO>>
	 */
	@GetMapping("/page")
	@Operation(summary = "分页查询", description = "分页查询")
	public R<PageResult<SysDictPageVO>> getSysDictPage(@Validated PageParam pageParam, SysDictQO sysDictQO) {
		return R.ok(sysDictManage.dictPage(pageParam, sysDictQO));
	}

	/**
	 * 新增字典表
	 * @param sysDict 字典表
	 * @return R
	 */
	@CreateLog(msg = "新增字典表")
	@PostMapping
	@Operation(summary = "新增字典表", description = "新增字典表")
	public R<Void> save(@RequestBody SysDict sysDict) {
		return sysDictManage.dictSave(sysDict) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增字典表失败");
	}

	/**
	 * 修改字典表
	 * @param sysDict 字典表
	 * @return R
	 */
	@UpdateLog(msg = "修改字典表")
	@PutMapping
	@Operation(summary = "修改字典表", description = "修改字典表")
	public R<Void> updateById(@RequestBody SysDict sysDict) {
		return sysDictManage.updateDictById(sysDict) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改字典表失败");
	}

	/**
	 * 通过id删除字典表
	 * @param id id
	 * @return R
	 */
	@DeleteLog(msg = "通过id删除字典表")
	@DeleteMapping("/{id}")
	@Operation(summary = "通过id删除字典表", description = "通过id删除字典表")
	public R<Void> removeById(@PathVariable("id") Integer id) {
		sysDictManage.removeDictById(id);
		return R.ok();
	}

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param dictCode 字典标识
	 * @return R
	 */
	@GetMapping("/item/page")
	@Operation(summary = "分页查询", description = "分页查询")
	public R<PageResult<SysDictItemPageVO>> getSysDictItemPage(PageParam pageParam,
			@RequestParam("dictCode") String dictCode) {
		return R.ok(sysDictManage.dictItemPage(pageParam, dictCode));
	}

	/**
	 * 新增字典项
	 * @param sysDictItemDTO 字典项
	 * @return R
	 */
	@CreateLog(msg = "新增字典项")
	@PostMapping("item")
	@Operation(summary = "新增字典项", description = "新增字典项")
	public R<Void> saveItem(
			@Validated({ Default.class, GroupType.CreateGroup.class }) @RequestBody SysDictItemDTO sysDictItemDTO) {
		return sysDictManage.saveDictItem(sysDictItemDTO) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增字典项失败");
	}

	/**
	 * 修改字典项
	 * @param sysDictItemDTO 字典项
	 * @return R
	 */
	@UpdateLog(msg = "修改字典项")
	@PutMapping("item")
	@Operation(summary = "修改字典项", description = "修改字典项")
	public R<Void> updateItemById(
			@Validated({ Default.class, GroupType.UpdateGroup.class }) @RequestBody SysDictItemDTO sysDictItemDTO) {
		return sysDictManage.updateDictItemById(sysDictItemDTO) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改字典项失败");
	}

	/**
	 * 通过id删除字典项
	 * @param id id
	 * @return R
	 */
	@DeleteLog(msg = "通过id删除字典项")
	@DeleteMapping("/item/{id}")
	@Operation(summary = "通过id删除字典项", description = "通过id删除字典项")
	public R<Void> removeItemById(@PathVariable("id") Integer id) {
		return sysDictManage.removeDictItemById(id) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除字典项失败");
	}

	/**
	 * 通过id修改字典项状态
	 * @param id id
	 * @return R
	 */
	@UpdateLog(msg = "通过id修改字典项状态")
	@PatchMapping("/item/{id}")
	@Operation(summary = "通过id修改字典项状态", description = "通过id修改字典项状态")
	public R<Void> updateDictItemStatusById(@PathVariable("id") Integer id, @RequestParam("status") Integer status) {
		sysDictManage.updateDictItemStatusById(id, status);
		return R.ok();
	}

}
