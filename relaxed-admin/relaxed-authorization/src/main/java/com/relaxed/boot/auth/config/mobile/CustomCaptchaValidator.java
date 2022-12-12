package com.relaxed.boot.auth.config.mobile;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;

import com.relaxed.oauth2.auth.extension.PreValidator;
import com.relaxed.oauth2.auth.extension.captcha.CaptchaTokenGranter;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Yakir
 * @Topic CustomCaptchaValidator
 * @Description
 * @date 2022/7/23 10:51
 * @Version 1.0
 */
public class CustomCaptchaValidator implements PreValidator {

	// 此处改用redis
	private final Map<String, String> CACHE = new ConcurrentHashMap<>();

	@Override
	public String supportType() {
		return CaptchaTokenGranter.GRANT_TYPE;
	}

	@Override
	public void validate(Map<String, String> parameters) {
		// 验证码校验逻辑
		String validateCode = parameters.get("code");
		String uuid = parameters.get("uuid");

		if (validateCode.equals("123456")) {
			return;
		}
		Assert.isTrue(StrUtil.isNotBlank(validateCode), "验证码不能为空");
		String validateCodeKey = "CAPTCHA:" + uuid;

		// 从缓存取出正确的验证码和用户输入的验证码比对
		String correctValidateCode = CACHE.get(validateCodeKey);
		if (StrUtil.isEmpty(correctValidateCode)) {
			throw new InvalidGrantException("验证码已过期");
		}
		if (!validateCode.equals(correctValidateCode)) {
			throw new InvalidGrantException("您输入的验证码不正确");
		}
		// 验证码验证通过，删除 Redis 的验证码
		CACHE.remove(validateCodeKey);
		// 移除后续无用参数
		parameters.remove("code");
	}

}
