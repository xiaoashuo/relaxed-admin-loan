package com.relaxed.boot.auth.component;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Yakir
 * @Topic PasswordHelper
 * @Description
 * @date 2022/8/2 17:29
 * @Version 1.0
 */
@RequiredArgsConstructor
@Component
public class PasswordHelper {

	private final PasswordEncoder passwordEncoder;

	/**
	 * 密码加密，单向加密，不可逆
	 * @param rawPassword 明文密码
	 * @return 加密后的密文
	 */
	public String encode(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}

	/**
	 * 是否匹配
	 * @param rawPassword
	 * @param encodedPassword
	 * @return
	 */
	public boolean matches(String rawPassword,String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}

}
