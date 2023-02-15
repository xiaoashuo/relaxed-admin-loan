package com.relaxed.boot.risk.controller;

import com.relaxed.boot.risk.enums.FieldType;
import com.relaxed.boot.risk.enums.ValidTypeEnum;
import com.relaxed.boot.risk.manage.RiskFieldManage;
import com.relaxed.boot.risk.model.entity.RiskField;
import com.relaxed.boot.risk.model.entity.RiskPreItem;
import com.relaxed.common.model.result.BaseResultCode;
import com.relaxed.common.model.result.R;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yakir
 * @Topic FieldManageController
 * @Description
 * @date 2021/9/26 13:36
 * @Version 1.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/risk/field")
@Tag(name = "字段管理")
public class RiskFieldController {

	private final RiskFieldManage fieldManageService;

	/**
	 * 验证字段列表
	 * @author yakir
	 * @date 2021/9/28 14:50
	 * @return com.relaxed.common.model.result.R
	 */
	@GetMapping("/types/validate")
	@Operation(summary = "验证字段列表", description = "验证字段列表")
	public R validateType() {
		List<Map<String, Object>> fieldTypeList = new ArrayList<>();
		for (ValidTypeEnum value : ValidTypeEnum.values()) {
			Map<String, Object> map = new HashMap<>(16);
			map.put("name", value.name());
			map.put("desc", value.getDesc());
			fieldTypeList.add(map);
		}
		return R.ok(fieldTypeList);
	}

	/**
	 * 字段类型列表
	 * @author yakir
	 * @date 2021/9/26 13:37
	 * @return com.relaxed.common.model.result.R
	 */
	@GetMapping("/types")
	@Operation(summary = "字段类型列表", description = "字段类型列表")
	public R listFieldTypes() {
		List<Map<String, Object>> fieldTypeList = new ArrayList<>();

		for (FieldType value : FieldType.values()) {
			Map<String, Object> map = new HashMap<>(16);
			map.put("name", value.name());
			map.put("desc", value.getDesc());
			fieldTypeList.add(map);
		}
		return R.ok(fieldTypeList);
	}

	/**
	 * 根据模型id查询字段列表
	 * @author yakir
	 * @date 2021/9/28 16:23
	 * @param modelId
	 * @return com.relaxed.common.model.result.R<?>
	 */
	@GetMapping("/list/{modelId}")
	public R<?> fieldListByModelId(@PathVariable Long modelId) {
		return R.ok(fieldManageService.fieldListByModelId(modelId));
	}

	/**
	 * 新增数据
	 * @param field {@link RiskField} 数据参数
	 * @return {@code R<?>} 通用返回体
	 */
	@PostMapping
	@Operation(summary = "原始字段新增数据", description = "原始字段新增数据")
	public R<?> fieldAdd(@RequestBody RiskField field) {
		return fieldManageService.fieldAdd(field) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增数据失败");
	}

	/**
	 * 更新数据
	 * @param field {@link RiskField} 更新数据
	 * @return {@code R<?>}通用返回体
	 */
	@PutMapping
	@Operation(summary = "原始字段更新数据", description = "原始字段更新数据")
	public R<?> fieldEdit(@RequestBody RiskField field) {
		return fieldManageService.fieldEdit(field) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "更新数据失败");
	}

	/**
	 * 根据id删除数据
	 * @param id {@code id} id
	 * @return {@code R<?>} 通用返回体
	 */
	@DeleteMapping("/{id}")
	@Operation(summary = "原始字段根据id删除数据", description = "原始字段根据id删除数据")
	public R<?> fieldDel(@PathVariable Long id) {
		return fieldManageService.fieldDel(id) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "根据id删除数据失败");
	}

	/**
	 * 根据模型id查询预字段列表
	 * @author yakir
	 * @date 2021/9/28 16:23
	 * @param modelId
	 * @return com.relaxed.common.model.result.R<?>
	 */
	@GetMapping("/pre/list/{modelId}")
	public R<?> preItemListByModelId(@PathVariable Long modelId) {
		return R.ok(fieldManageService.preItemListByModelId(modelId));
	}

	/**
	 * 新增数据
	 * @param preItem {@link RiskPreItem} 数据参数
	 * @return {@code R<?>} 通用返回体
	 */
	@PostMapping("/pre")
	@Operation(summary = "预处理字段新增数据", description = "预处理字段新增数据")
	public R<?> preItemFieldAdd(@RequestBody RiskPreItem preItem) {
		return fieldManageService.preItemFieldAdd(preItem) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增数据失败");
	}

	/**
	 * 更新数据
	 * @param preItem {@link RiskPreItem} 更新数据
	 * @return {@code R<?>}通用返回体
	 */
	@PutMapping("/pre")
	@Operation(summary = "预处理字段更新数据", description = "预处理字段更新数据")
	public R<?> preItemFieldEdit(@RequestBody RiskPreItem preItem) {
		return fieldManageService.preItemFieldEdit(preItem) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "更新数据失败");
	}

	/**
	 * 根据id删除数据
	 * @param modelId {@code modelId} modelId
	 * @param id {@code id} id
	 * @return {@code R<?>} 通用返回体
	 */
	@DeleteMapping("/pre/{modelId}/{id}")
	@Operation(summary = "预处理字段根据id删除数据", description = "预处理字段根据id删除数据")
	public R<?> preItemFieldDel(@PathVariable Long modelId, @PathVariable Long id) {
		return fieldManageService.preItemFieldDel(modelId, id) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "根据id删除数据失败");
	}

}
