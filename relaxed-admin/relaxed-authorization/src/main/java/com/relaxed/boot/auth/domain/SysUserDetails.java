package com.relaxed.boot.auth.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

/**
 * @author Yakir
 * @Topic User
 * @Description
 * @date 2022/8/1 17:10
 * @Version 1.0
 */
@Accessors(chain = true)
@Data
public class SysUserDetails implements UserDetails {

	private static final long serialVersionUID = -5445208192069734241L;

	private  Integer userId;

	private  String username;

	private  String password;

	private  String nickname;

	private  String email;
	/**
	 * 电话
	 */
	@Schema(title = "电话", description = "电话")
	private String phone;
	private  Integer status;

	private  String avatar;



	private  LocalDateTime createdTime;
	private  Collection<? extends GrantedAuthority> authorities;

	private  Map<String, Object> attributes;

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.status != null && this.status == 1;
	}

}
