package com.relaxed.boot.web.config;

import com.relaxed.common.core.constants.GlobalConstants;
import com.relaxed.common.core.exception.BusinessException;
import com.relaxed.common.model.result.R;
import com.relaxed.common.model.result.SysResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.xml.bind.ValidationException;

/**
 * @author yakir
 * @Topic 全局异常处理
 * @Description 全局异常处理
 * @date 2021/12/22
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class CustomGlobalExceptionHandlerResolver {

	@Value("${spring.profiles.active:prod}")
	private String profile;

	public static final String PROD_ERR_MSG = "系统异常，请联系管理员";

	public static final String NLP_MSG = "空指针异常!";

	/**
	 * 全局异常捕获
	 * @param e the e
	 * @return R
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public R<String> handleGlobalException(Exception e) {
		log.error("全局异常信息 ex={}", e.getMessage(), e);

		// 当为生产环境, 不适合把具体的异常信息展示给用户, 比如数据库异常信息.
		String errorMsg = GlobalConstants.ENV_PROD.equals(profile) ? PROD_ERR_MSG
				: (e instanceof NullPointerException ? NLP_MSG : e.getLocalizedMessage());
		return R.failed(SysResultCode.SERVER_ERROR, errorMsg);
	}

	/**
	 * 进入拒绝异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public R<String> handleGlobalException(AccessDeniedException e) {
		log.error("全局异常信息 ex={}", e.getMessage(), e);

		return R.failed(SysResultCode.FORBIDDEN, e.getMessage());
	}

	/**
	 * MethodArgumentTypeMismatchException 参数类型转换异常
	 * @param e the e
	 * @return R
	 */
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public R<String> handleMethodArgumentTypeMismatchException(Exception e) {
		log.error("请求入参异常 ex={}", e.getMessage());

		return R.failed(SysResultCode.BAD_REQUEST,
				GlobalConstants.ENV_PROD.equals(profile) ? PROD_ERR_MSG : e.getMessage());
	}

	/**
	 * 请求方式有问题 - MediaType 异常 - Method 异常
	 * @return R
	 */
	@ExceptionHandler({ HttpMediaTypeNotSupportedException.class, HttpRequestMethodNotSupportedException.class })
	public R<String> requestNotSupportedException(Exception e) {
		log.error("请求方式异常 ex={}", e.getMessage());

		return R.failed(SysResultCode.BAD_REQUEST, e.getLocalizedMessage());
	}

	/**
	 * IllegalArgumentException 异常捕获，主要用于Assert
	 * @param e the e
	 * @return R
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public R<String> handleIllegalArgumentException(IllegalArgumentException e) {
		log.error("非法数据输入 ex={}", e.getMessage());
		return R.failed(SysResultCode.BAD_REQUEST, e.getMessage());
	}

	/**
	 * validation Exception
	 * @param exception e
	 * @return R
	 */
	@ExceptionHandler({ MethodArgumentNotValidException.class, BindException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public R<String> handleBodyValidException(Exception exception) {
		BindingResult bindingResult;

		if (exception instanceof BindException) {
			bindingResult = ((BindException) exception).getBindingResult();
		}
		else {
			bindingResult = ((MethodArgumentNotValidException) exception).getBindingResult();
		}

		String errorMsg = bindingResult.getErrorCount() > 0 ? bindingResult.getAllErrors().get(0).getDefaultMessage()
				: "未获取到错误信息!";

		log.error("参数绑定异常,ex = {}", errorMsg);
		return R.failed(SysResultCode.BAD_REQUEST, errorMsg);
	}

	/**
	 * 单体参数校验异常 validation Exception
	 * @param e the e
	 * @return R
	 */
	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public R<String> handleValidationException(Exception e) {
		log.error("参数绑定异常 ex={}", e.getMessage());
		return R.failed(SysResultCode.BAD_REQUEST, e.getLocalizedMessage());
	}

	/**
	 * 自定义业务异常捕获 业务异常响应码推荐使用200 用 result 结构中的code做为业务错误码标识
	 * @param e the e
	 * @return R
	 */
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.OK)
	public R<String> handleBallCatException(BusinessException e) {
		log.error("业务异常信息 ex={}", e.getMessage());
		return R.failed(e.getCode(), e.getMessage());
	}

}
