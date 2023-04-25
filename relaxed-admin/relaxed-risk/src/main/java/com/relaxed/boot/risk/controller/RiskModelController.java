package com.relaxed.boot.risk.controller;

import com.relaxed.boot.risk.manage.RiskModelManage;
import com.relaxed.boot.risk.model.entity.RiskModel;
import com.relaxed.boot.risk.model.qo.RiskModelQO;
import com.relaxed.boot.risk.model.vo.RiskModelVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 控制器
 * </p>
 *
 * @author Yakir
 * @since 2021-09-11T11:23:00.550
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/risk/model")
@Tag(name = "模型配置")
public class RiskModelController {

	private final RiskModelManage riskModelManage;

	/**
	 * 分页查询
	 * @param pageParam {@link PageParam} 分页参数
	 * @param modelQO {@link RiskModelQO} 查询条件
	 * @return @{code R<PageResult<ModelVO>>} 通用返回体
	 */
	@GetMapping("/page")
	@Operation(summary = "分页查询", description = "分页查询")
	public R<PageResult<RiskModelVO>> page(PageParam pageParam, RiskModelQO modelQO) {
		return R.ok(riskModelManage.selectByPage(pageParam, modelQO));
	}

	/**
	 * 查询执行模型
	 * @param modelId {@link PageParam} 模型id
	 * @return @{code R<PageResult<ModelVO>>} 通用返回体
	 */
	@GetMapping("/{modelId}")
	@Operation(summary = "查询模型", description = "查询模型")
	public R<RiskModelVO> page(@PathVariable Long modelId) {
		return R.ok(riskModelManage.getByModelId(modelId));
	}

	/**
	 * 新增数据
	 * @param model {@link RiskModel} 数据参数
	 * @return {@code R<?>} 通用返回体
	 */
	@PostMapping
	@Operation(summary = "新增数据", description = "新增数据")
	public R<?> save(@RequestBody RiskModel model) {
		return riskModelManage.add(model) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增数据失败");
	}

	/**
	 * 更新数据
	 * @param model {@link RiskModel} 更新数据
	 * @return {@code R<?>}通用返回体
	 */
	@PutMapping
	@Operation(summary = "更新数据", description = "更新数据")
	public R<?> updateById(@RequestBody RiskModel model) {
		return riskModelManage.edit(model) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "更新数据失败");
	}

	@PutMapping("/switch/status")
	@Operation(summary = "切换模型启用状态", description = "切换模型启用状态")
	public R<?> switchStatus(@RequestParam Long modelId, @RequestParam Integer status) {
		return R.ok(riskModelManage.switchStatus(modelId, status));
	}

	/**
	 * 根据id删除数据
	 * @param id {@code id} id
	 * @return {@code R<?>} 通用返回体
	 */
	@DeleteMapping("/{id}")
	@Operation(summary = "根据id删除数据", description = "根据id删除数据")
	public R<?> removeById(@PathVariable Long id) {
		return riskModelManage.del(id) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "根据id删除数据失败");
	}

}
