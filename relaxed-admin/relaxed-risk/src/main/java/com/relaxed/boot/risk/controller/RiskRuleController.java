package com.relaxed.boot.risk.controller;

import com.relaxed.boot.risk.manage.RiskRuleManage;
import com.relaxed.boot.risk.model.dto.DataColumn;
import com.relaxed.boot.risk.model.entity.RiskRule;
import com.relaxed.boot.risk.model.qo.RiskRuleQO;
import com.relaxed.boot.risk.model.vo.RiskFieldVO;
import com.relaxed.boot.risk.model.vo.RiskRuleVO;
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
 * @since 2021-09-11T11:23:02.509
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/risk/rule")
@Tag(name = "规则控制器")
public class RiskRuleController {

	private final RiskRuleManage riskRuleManage;

	/**
	 * 分页查询
	 * @param pageParam {@link PageParam} 分页参数
	 * @param ruleQO {@link RiskRuleQO} 查询条件
	 * @return @{code R<PageResult<RuleVO>>} 通用返回体
	 */
	@GetMapping("/page")
	@Operation(summary = "分页查询", description = "分页查询")
	public R<PageResult<RiskRuleVO>> page(PageParam pageParam, RiskRuleQO ruleQO) {
		return R.ok(riskRuleManage.selectByPage(pageParam, ruleQO));
	}

	/**
	 * 根据决策id查询所有规则
	 * @author yakir
	 * @date 2021/9/29 10:53
	 * @param activationId
	 * @return com.relaxed.common.model.result.R<java.util.List<com.relaxed.common.risk.model.vo.RuleVO>>
	 */
	@GetMapping("/list/{activationId}")
	@Operation(summary = "根据决策id查询所有规则", description = "根据决策id查询所有规则")
	public R<List<RiskRuleVO>> listByActivationId(@PathVariable Long activationId) {
		return R.ok(riskRuleManage.listByActivationId(activationId));
	}
	@GetMapping("/fields/{modelId}")
	public R<List<SelectData<?>>> fieldList(@PathVariable Long modelId) {
		return R.ok(riskRuleManage.getAllFieldList(modelId));
	}
	/**
	 * 特征提取列
	 * @param modelId {@link PageParam} 分页参数
	 */
	@GetMapping("/columns/{modelId}")
	@Operation(summary = "特征提取列", description = "特征提取列")
	public R<List<DataColumn>> dataColumns(@PathVariable Long modelId) {
		return R.ok(riskRuleManage.selectColumns(modelId));
	}

	/**
	 * 新增数据
	 * @param rule {@link RiskRule} 数据参数
	 * @return {@code R<?>} 通用返回体
	 */
	@PostMapping
	@Operation(summary = "新增数据", description = "新增数据")
	public R<?> save(@RequestBody RiskRule rule) {
		return riskRuleManage.add(rule) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增数据失败");
	}

	/**
	 * 更新数据
	 * @param rule {@link RiskRule} 更新数据
	 * @return {@code R<?>}通用返回体
	 */
	@PutMapping
	@Operation(summary = "更新数据", description = "更新数据")
	public R<?> updateById(@RequestBody RiskRule rule) {
		return riskRuleManage.edit(rule) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "更新数据失败");
	}

	/**
	 * 根据id删除数据
	 * @param id {@code id} id
	 * @return {@code R<?>} 通用返回体
	 */
	@DeleteMapping("/{id}")
	@Operation(summary = "根据id删除数据", description = "根据id删除数据")
	public R<?> removeById(@PathVariable Long id) {
		return riskRuleManage.del(id) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "根据id删除数据失败");
	}

}
