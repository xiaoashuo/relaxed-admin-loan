package com.relaxed.boot.common.system.model.vo;

import com.relaxed.boot.common.system.model.entity.SysOrganization;
import com.relaxed.boot.common.system.model.entity.SysRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Yakir
 * @Topic SysUserProfileVO
 * @Description
 * @date 2022/11/28 14:13
 * @Version 1.0
 */
@Data
public class SysUserProfileVO {
    /**
     * 主键id
     */
    @Schema(title = "主键id", description = "主键id")
    private Integer userId;

    /**
     * 登录账号
     */
    @Schema(title = "登录账号", description = "登录账号")
    private String username;

    /**
     * 显示名称
     */
    @Schema(title = "显示名称", description = "显示名称")
    private String nickname;

    /**
     * md5密码盐
     */
    @Schema(title = "md5密码盐", description = "md5密码盐")
    private String salt;

    /**
     * 头像
     */
    @Schema(title = "头像", description = "头像")
    private String avatar;

    /**
     * 性别(0-默认未知,1-男,2-女)
     */
    @Schema(title = "性别(0-默认未知,1-男,2-女)", description = "性别(0-默认未知,1-男,2-女)")
    private Integer sex;

    /**
     * 电子邮件
     */
    @Schema(title = "电子邮件", description = "电子邮件")
    private String email;

    /**
     * 电话
     */
    @Schema(title = "电话", description = "电话")
    private String phone;

    /**
     * 状态(1-正常,0-冻结)
     */
    @Schema(title = "状态(1-正常,0-冻结)", description = "状态(1-正常,0-冻结)")
    private Integer status;

    /**
     * 账户类型
     */
    @Schema(title = "账户类型", description = "账户类型")
    private Integer type;
    /**
     * 组织vo
     */
    @Schema(title = "组织", description = "组织")
    private SysOrganization organization;

    @Schema(title = "身份列表", description = "身份列表")
    private List<SysRole> roleList;
    /**
     * 创建时间
     */
    @Schema(title = "创建时间", description = "创建时间")
    private LocalDateTime createdTime;
}
