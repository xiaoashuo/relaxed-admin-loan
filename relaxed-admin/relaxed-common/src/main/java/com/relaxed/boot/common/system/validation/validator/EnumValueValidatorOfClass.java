package com.relaxed.boot.common.system.validation.validator;

import com.relaxed.boot.common.system.validation.constraints.OneOfClasses;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author hccake
 * @version 1.0 枚举值 Validator
 */
public class EnumValueValidatorOfClass implements ConstraintValidator<OneOfClasses, Class<?>> {

	private Class<?>[] classList;

	private boolean allowNull;

	@Override
	public void initialize(OneOfClasses constraintAnnotation) {
		classList = constraintAnnotation.value();
		allowNull = constraintAnnotation.allowNull();
	}

	@Override
	public boolean isValid(Class value, ConstraintValidatorContext context) {
		if (value == null) {
			return allowNull;
		}
		for (Class<?> clazz : classList) {
			if (clazz.isAssignableFrom(value)) {
				return true;
			}
		}
		return false;
	}

}
