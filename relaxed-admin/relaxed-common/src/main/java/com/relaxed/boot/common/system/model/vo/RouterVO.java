package com.relaxed.boot.common.system.model.vo;

import com.relaxed.common.core.util.tree.TreeNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author Yakir
 * @Topic Router
 * @Description
 * @date 2022/11/11 14:02
 * @Version 1.0
 */
@Schema(title = "路由信息")
@Data
public class RouterVO implements TreeNode<Integer> {

	/**
	 * 主键
	 */
	@Schema(title = "主键", description = "主键")
	private Integer id;

	/**
	 * url地址
	 */
	@Schema(title = "url地址", description = "url地址")
	private String path;

	/**
	 * 菜单名称
	 */
	@Schema(title = "菜单名称", description = "菜单名称")
	private String title;

	/**
	 * 菜单图标
	 */
	@Schema(title = "菜单图标", description = "菜单图标")
	private String icon;

	/**
	 * 类型 1 目录 2菜单 3按钮
	 */
	@Schema(title = "类型 1 目录 2菜单 3按钮", description = "类型 1 目录 2菜单 3按钮")
	private Integer type;

	/**
	 * 打开方式 (1组件 2内链 3外链)
	 */
	@Schema(title = "打开方式 (1组件 2内链 3外链)", description = "打开方式 (1组件 2内链 3外链)")
	private Integer targetType;

	/**
	 * 定位标识 (打开方式为组件时其值为组件相对路径，其他为URL地址)
	 */
	@Schema(title = "定位标识 (打开方式为组件时其值为组件相对路径，其他为URL地址)", description = "定位标识 (打开方式为组件时其值为组件相对路径，其他为URL地址)")
	private String uri;

	/**
	 * 父级id
	 */
	@Schema(title = "父级id", description = "父级id")
	private Integer parentId;

	/**
	 * 权限标识
	 */
	@Schema(title = "权限标识", description = "权限标识")
	private String permission;

	/**
	 * 高亮菜单
	 */
	@Schema(title = "高亮菜单", description = "高亮菜单")
	private String activeMenu;

	/**
	 * 组件缓存：0-开启，1-关闭
	 */
	@Schema(title = "组件缓存：0-开启，1-关闭", description = "组件缓存：0-开启，1-关闭")
	private Integer keepAlive;

	/**
	 * 隐藏菜单: 0-否，1-是
	 */
	@Schema(title = "隐藏菜单:  0-否，1-是", description = "隐藏菜单:  0-否，1-是")
	private Integer hidden;

	/**
	 * 子节点
	 */
	@Schema(title = "子节点", description = "子节点")
	private List<RouterVO> children;

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
		this.children = (List<RouterVO>) list;
	}

}
