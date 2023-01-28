package com.relaxed.boot.web.system.manage;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.relaxed.boot.auth.component.PasswordHelper;
import com.relaxed.boot.auth.domain.SysUserDetails;
import com.relaxed.boot.biz.system.service.SysOrganizationService;
import com.relaxed.boot.biz.system.service.SysUserRoleService;
import com.relaxed.boot.biz.system.service.SysUserService;
import com.relaxed.boot.common.system.converter.SysUserConverter;
import com.relaxed.boot.common.system.model.dto.SysUserDTO;
import com.relaxed.boot.common.system.model.dto.SysUserScope;
import com.relaxed.boot.common.system.model.entity.SysOrganization;
import com.relaxed.boot.common.system.model.entity.SysRole;
import com.relaxed.boot.common.system.model.entity.SysUser;
import com.relaxed.boot.common.system.model.vo.SysUserProfileVO;
import com.relaxed.boot.common.system.utils.AvatarUtil;
import com.relaxed.boot.common.system.utils.file.Base64MultipartFile;
import com.relaxed.boot.common.system.utils.file.FileConfig;
import com.relaxed.boot.common.system.utils.file.FileMeta;
import com.relaxed.boot.common.system.utils.file.FileUtils;
import com.relaxed.boot.framework.config.RelaxedConfig;
import com.relaxed.common.core.exception.BusinessException;
import com.relaxed.common.model.result.BaseResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Yakir
 * @Topic SysUserManage
 * @Description
 * @date 2022/11/11 16:30
 * @Version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysUserManage {

	private final SysUserService sysUserService;

	private final SysUserRoleService sysUserRoleService;

	private final SysOrganizationService sysOrganizationService;

	private final PasswordHelper passwordHelper;

	/**
	 * 更新用户权限信息
	 * @param userId 用户Id
	 * @param sysUserScope 系统用户权限范围
	 * @return 更新成功：true
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateUserScope(Integer userId, SysUserScope sysUserScope) {
		// 更新用户角色关联关系
		return sysUserRoleService.updateUserRoles(userId, sysUserScope.getRoleCodes());
	}

	/**
	 * 新增系统用户
	 * @param sysUserDto 系统用户DTO
	 * @return 添加成功：true , 失败：false
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean addSysUser(SysUserDTO sysUserDto) {

		SysUser sysUser = SysUserConverter.INSTANCE.dtoToPo(sysUserDto);
		// 明文密码
		String encryptPassword = passwordHelper.encode(sysUser.getPassword());
		sysUser.setPassword(encryptPassword);
		// 设置默认头像
		String avatarBase64Img = AvatarUtil.generateImg(sysUser.getNickname());
		MultipartFile avatarMultipartFile = Base64MultipartFile.base64Convert(avatarBase64Img);
		// 上传头像
		FileMeta fileMeta = FileUtils.upload(RelaxedConfig.getProfile(), "profile/upload", avatarMultipartFile,
				FileConfig.create().splitDate(true));
		String fullFilePath = fileMeta.getRelativeFilePath();
		String url = RelaxedConfig.getUrl();
		sysUser.setAvatar(url + fullFilePath);
		// 保存用户
		boolean insertSuccess = sysUserService.save(sysUser);
		Assert.isTrue(insertSuccess, () -> {
			log.error("[addSysUser] 数据插入系统用户表失败，user：{}", sysUserDto);
			return new BusinessException(BaseResultCode.UPDATE_DATABASE_ERROR.getCode(), "数据插入系统用户表失败");
		});

		// 新增用户角色关联
		List<String> roleCodes = sysUserDto.getRoleCodes();
		if (CollectionUtil.isNotEmpty(roleCodes)) {
			boolean addUserRoleSuccess = sysUserRoleService.addUserRoles(sysUser.getUserId(), roleCodes);
			Assert.isTrue(addUserRoleSuccess, () -> {
				log.error("[addSysUser] 更新用户角色信息失败，user：{}， roleCodes: {}", sysUserDto, roleCodes);
				return new BusinessException(BaseResultCode.UPDATE_DATABASE_ERROR.getCode(), "更新用户角色信息失败");
			});
		}

		// 发布用户创建事件
		// publisher.publishEvent(new UserCreatedEvent(sysUser,
		// sysUserDto.getRoleCodes()));

		return true;
	}

	/**
	 * 更新用户信息
	 * @date 2022/11/25 15:04
	 * @param sysUserDTO
	 * @return boolean
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateUser(SysUserDTO sysUserDTO) {
		SysUser sysUser = SysUserConverter.INSTANCE.dtoToPo(sysUserDTO);
		List<String> roleCodes = sysUserDTO.getRoleCodes();
		Integer userId = sysUserDTO.getUserId();
		SysUserScope sysUserScope = new SysUserScope();
		sysUserScope.setRoleCodes(roleCodes);
		updateUserScope(userId, sysUserScope);

		boolean success = sysUserService.updateById(sysUser);
		Assert.isTrue(success, () -> {
			log.error("[updateUserInfo] 更新用户信息失败，userId：{}", userId);
			return new BusinessException(BaseResultCode.UPDATE_DATABASE_ERROR.getCode(), "更新用户信息失败");
		});
		return success;
	}

	public boolean updateUserPassword(Integer userId, String oldPassword, String newPassword) {
		SysUser sqlSysUser = sysUserService.getById(userId);
		Assert.notNull(sqlSysUser, () -> {
			throw new BusinessException(BaseResultCode.UPDATE_DATABASE_ERROR.getCode(), "更新用户密码失败,原始用户不存在");
		});
		String oldEncryptedPassword = sqlSysUser.getPassword();
		if (!passwordHelper.matches(oldPassword, oldEncryptedPassword)) {
			throw new BusinessException(BaseResultCode.UPDATE_DATABASE_ERROR.getCode(), "更新用户密码失败,原始密码不正确");
		}
		String encryptedPassword = passwordHelper.encode(newPassword);
		boolean success = sysUserService.update(new LambdaUpdateWrapper<SysUser>().eq(SysUser::getUserId, userId)
				.set(SysUser::getPassword, encryptedPassword));
		Assert.isTrue(success, () -> {
			log.error("[updateUserPassword] 修改用户密码失败，userId：{}", userId);
			return new BusinessException(BaseResultCode.UPDATE_DATABASE_ERROR.getCode(), "修改用户密码失败");
		});
		return success;
	}

	public String uploadAvatar(SysUserDetails sysUserDetails, MultipartFile file) {
		FileMeta fileMeta = FileUtils.upload(RelaxedConfig.getProfile(), "profile/upload", file,
				FileConfig.create().splitDate(true));
		String relativeFilePath = fileMeta.getRelativeFilePath();
		String url =  relativeFilePath;
		SysUser sysUser = new SysUser();
		Integer userId = sysUserDetails.getUserId();
		sysUser.setUserId(userId);
		sysUser.setAvatar(url);
		Assert.isTrue(sysUserService.updateById(sysUser), () -> {
			log.error("[uploadAvatar] 上传头像失败，userId：{}", userId);
			return new BusinessException(BaseResultCode.UPDATE_DATABASE_ERROR.getCode(), "上传头像失败");
		});
		return url;

	}

	/**
	 * 获取个人相关信息
	 * @date 2022/11/28 14:19
	 * @param sysUserDetails
	 * @return com.relaxed.boot.common.system.model.vo.SysUserProfileVO
	 */
	public SysUserProfileVO profile(SysUserDetails sysUserDetails) {
		Integer userId = sysUserDetails.getUserId();
		SysUser sysUser = sysUserService.getById(userId);
		SysUserProfileVO sysUserProfileVO = SysUserConverter.INSTANCE.poToProfileVO(sysUser);
		Integer organizationId = sysUser.getOrganizationId();
		SysOrganization sysOrganization = sysOrganizationService.getById(organizationId);
		List<SysRole> sysRoles = sysUserRoleService.listRoles(userId);
		sysUserProfileVO.setOrganization(sysOrganization);
		sysUserProfileVO.setRoleList(sysRoles);
		return sysUserProfileVO;
	}

}
