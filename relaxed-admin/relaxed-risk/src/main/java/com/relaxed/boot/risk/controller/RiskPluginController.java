package com.relaxed.boot.risk.controller;

import com.relaxed.boot.risk.manage.RiskPluginManage;
import com.relaxed.boot.risk.model.entity.RiskPlugin;
import com.relaxed.boot.risk.model.qo.RiskPluginQO;
import com.relaxed.boot.risk.model.vo.RiskPluginVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.domain.SelectData;
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

import java.util.List;

/**
 * <p>
 * 控制器
 * </p>
 *
 * @author Yakir
 * @since 2021-09-28T13:43:11.834
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/risk/plugin")
@Tag(name = "插件管理")
public class RiskPluginController {

	private final RiskPluginManage riskPluginManage;

	/**
	 * 分页查询
	 * @param pageParam {@link PageParam} 分页参数
	 * @param pluginQO {@link RiskPluginQO} 查询条件
	 * @return @{code R<PageResult<PluginVO>>} 通用返回体
	 */
	@GetMapping("/page")
	@Operation(summary = "分页查询", description = "分页查询")
	public R<PageResult<RiskPluginVO>> page(PageParam pageParam, RiskPluginQO pluginQO) {
		return R.ok(riskPluginManage.selectByPage(pageParam, pluginQO));
	}

	/**
	 * 获取所有的启用插件
	 * @author yakir
	 * @date 2021/9/28 13:53
	 * @return com.relaxed.common.model.result.R<java.util.List<com.relaxed.common.risk.model.vo.PluginVO>>
	 */
	@GetMapping("/list/enabled")
	@Operation(summary = "获取所有启用插件", description = "获取所有启用插件")
	public R<List<SelectData>> listEnabledPlugins() {
		return R.ok(riskPluginManage.listEnabledPlugins());
	}

	/**
	 * 新增数据
	 * @param plugin {@link RiskPlugin} 数据参数
	 * @return {@code R<?>} 通用返回体
	 */
	@PostMapping
	@Operation(summary = "新增数据", description = "新增数据")
	public R<?> save(@RequestBody RiskPlugin plugin) {
		return riskPluginManage.add(plugin) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增数据失败");
	}

	/**
	 * 更新数据
	 * @param plugin {@link RiskPlugin} 更新数据
	 * @return {@code R<?>}通用返回体
	 */
	@PutMapping
	@Operation(summary = "更新数据", description = "更新数据")
	public R<?> updateById(@RequestBody RiskPlugin plugin) {
		return riskPluginManage.edit(plugin) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "更新数据失败");
	}

	/**
	 * 根据id删除数据
	 * @param id {@code id} id
	 * @return {@code R<?>} 通用返回体
	 */
	@DeleteMapping("/{id}")
	@Operation(summary = "根据id删除数据", description = "根据id删除数据")
	public R<?> removeById(@PathVariable Long id) {
		return riskPluginManage.del(id) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "根据id删除数据失败");
	}

}
