package com.relaxed.boot.common.system.model.qo;

import com.relaxed.boot.common.system.model.vo.RouterVO;
import com.relaxed.common.core.util.tree.TreeNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author Yakir
 * @Topic SysMenuGrantVO
 * @Description
 * @date 2022/11/11 14:58
 * @Version 1.0
 */
@Schema(title = "菜单权限授权对象")
@Data
public class SysMenuGrantVO implements TreeNode<Integer> {

	@Schema(title = "菜单ID", description = "菜单ID")
	private Integer id;

	@Schema(title = "父级ID", description = "父级ID")
	private Integer parentId;

	@Schema(title = "菜单名称", description = "菜单名称")
	private String title;

	@Schema(title = "菜单类型 （0目录，1菜单，2按钮）", description = "菜单类型 （0目录，1菜单，2按钮）")
	private Integer type;

	/**
	 * 子节点
	 */
	@Schema(title = "子节点", description = "子节点")
	private List<SysMenuGrantVO> children;

	@Override
	public Integer getKey() {
		return id;
	}

	@Override
	public Integer getParentKey() {
		return parentId;
	}

	@Override
	public <T extends TreeNode<Integer>> void setChildren(List<T> list) {
		this.children = (List<SysMenuGrantVO>) list;
	}

}
