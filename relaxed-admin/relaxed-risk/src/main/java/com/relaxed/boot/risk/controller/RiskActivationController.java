package com.relaxed.boot.risk.controller;

import com.relaxed.boot.risk.manage.RiskActivationManage;
import com.relaxed.boot.risk.model.dto.RiskActivationDTO;
import com.relaxed.boot.risk.model.entity.RiskActivation;
import com.relaxed.boot.risk.model.qo.RiskActivationQO;
import com.relaxed.boot.risk.model.vo.RiskActivationVO;
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
 * @since 2021-09-22T16:03:38.120
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/risk/activation")
@Tag(name = "决策控制器")
public class RiskActivationController {

	private final RiskActivationManage riskActivationManage;

	/**
	 * 分页查询
	 * @param pageParam {@link PageParam} 分页参数
	 * @param activationQO {@link RiskActivationQO} 查询条件
	 * @return @{code R<PageResult<ActivationVO>>} 通用返回体
	 */
	@GetMapping("/page")
	@Operation(summary = "分页查询", description = "分页查询")
	public R<PageResult<RiskActivationVO>> page(PageParam pageParam, RiskActivationQO activationQO) {
		return R.ok(riskActivationManage.selectByPage(pageParam, activationQO));
	}

	/**
	 * 新增数据
	 * @param activation {@link RiskActivation} 数据参数
	 * @return {@code R<?>} 通用返回体
	 */
	@PostMapping
	@Operation(summary = "新增数据", description = "新增数据")
	public R<RiskActivationDTO> save(@RequestBody RiskActivation activation) {
		return  R.ok(riskActivationManage.add(activation));
	}

	/**
	 * 更新数据
	 * @param activation {@link RiskActivation} 更新数据
	 * @return {@code R<?>}通用返回体
	 */
	@PutMapping
	@Operation(summary = "更新数据", description = "更新数据")
	public R<?> updateById(@RequestBody RiskActivation activation) {
		return riskActivationManage.edit(activation) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "更新数据失败");
	}

	/**
	 * 切换策略状态开关
	 * @param activationId 策略id
	 * @param status 状态
	 * @return {@code R<?>}通用返回体
	 */
	@PutMapping("/switch/status")
	@Operation(summary = "切换状态", description = "更新数据")
	public R<?> switchStatus(@RequestParam Long activationId,@RequestParam Integer status) {
		return R.ok(riskActivationManage.switchActivationStatus(activationId,status)) ;
	}
	/**
	 * 根据id删除数据
	 * @param id {@code id} id
	 * @return {@code R<?>} 通用返回体
	 */
	@DeleteMapping("/{id}")
	@Operation(summary = "根据id删除数据", description = "根据id删除数据")
	public R<?> removeById(@PathVariable Long id) {
		return riskActivationManage.del(id) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "根据id删除数据失败");
	}

}
