package com.relaxed.boot.common.system.model.vo;

import com.relaxed.common.core.util.tree.TreeNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 菜单权限 编号规则目录-菜单-按钮 eg: 10-01-01=100101分页视图对象
 *
 * @author yakir 2022-11-11 10:27:53
 */
@Data
@Schema(title = "菜单权限 编号规则目录-菜单-按钮 eg: 10-01-01=100101分页视图对象")
public class SysMenuPageVO implements TreeNode<Integer> {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单ID
	 */
	@Schema(title = "菜单ID", description = "菜单ID")
	private Integer id;

	/**
	 * 父级ID
	 */
	@Schema(title = "父级ID", description = "父级ID")
	private Integer parentId;

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
	 * 授权标识
	 */
	@Schema(title = "授权标识", description = "授权标识")
	private String permission;

	/**
	 * 高亮菜单
	 */
	@Schema(title = "高亮菜单", description = "高亮菜单")
	private String activeMenu;

	/**
	 * 路由地址
	 */
	@Schema(title = "路由地址", description = "路由地址")
	private String path;

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
	 * 显示排序
	 */
	@Schema(title = "显示排序", description = "显示排序")
	private Integer sort;

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
	 * 菜单类型 （0目录，1菜单，2按钮）
	 */
	@Schema(title = "菜单类型 （0目录，1菜单，2按钮）", description = "菜单类型 （0目录，1菜单，2按钮）")
	private Integer type;

	/**
	 * 目录下菜单数小于1,是否合并 1合并 0不合并
	 */
	@Schema(title = "目录下菜单数小于1,是否合并 1合并 0不合并", description = "目录下菜单数小于1,是否合并 1合并 0不合并")
	private Integer alwaysShow;

	/**
	 * 备注信息
	 */
	@Schema(title = "备注信息", description = "备注信息")
	private String remarks;

	/**
	 * 逻辑删除标识，未删除为 0，已删除为删除时间
	 */
	@Schema(title = "逻辑删除标识，未删除为 0，已删除为删除时间", description = "逻辑删除标识，未删除为 0，已删除为删除时间")
	private Long deleted;

	/**
	 * 创建人
	 */
	@Schema(title = "创建人", description = "创建人")
	private Integer createBy;

	/**
	 * 修改人
	 */
	@Schema(title = "修改人", description = "修改人")
	private Integer updateBy;

	/**
	 * 创建时间
	 */
	@Schema(title = "创建时间", description = "创建时间")
	private LocalDateTime createdTime;

	/**
	 * 修改时间
	 */
	@Schema(title = "修改时间", description = "修改时间")
	private LocalDateTime updatedTime;

	/**
	 * 子节点
	 */
	@Schema(title = "子节点", description = "子节点")
	private List<SysMenuPageVO> children;

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
		this.children = (List<SysMenuPageVO>) list;
	}

}
