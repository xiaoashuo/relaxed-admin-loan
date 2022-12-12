package com.relaxed.boot.auth.config.mobile;

import cn.hutool.core.util.StrUtil;

import com.relaxed.oauth2.auth.extension.PreValidator;
import com.relaxed.oauth2.auth.extension.mobile.SmsCodeTokenGranter;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Yakir
 * @Topic CustomSmsCodeHandler
 * @Description
 * @date 2022/7/23 10:16
 * @Version 1.0
 */
@Component
public class CustomSmsCodeValidator implements PreValidator {

	@Override
	public String supportType() {
		return SmsCodeTokenGranter.GRANT_TYPE;
	}

	@Override
	public void validate(Map<String, String> parameterMap) {
		String mobile = parameterMap.get("mobile");
		String code = parameterMap.get("code");
		if (!code.equals("666666")) { // 666666 是后门，因为短信收费，正式环境删除这个if分支
			// 短信验证码key前缀
			String codeKey = "SMS_CODE:" + mobile;
			// 根据验证码key获取验证码
			String correctCode = "";
			// 验证码比对
			if (StrUtil.isBlank(correctCode) || !code.equals(correctCode)) {
				throw new InvalidGrantException("验证码不正确");
			}
			// 比对成功删除缓存的验证码
			// ...del
		}
	}

}
