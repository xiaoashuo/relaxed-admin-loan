package com.relaxed.boot.common.system.utils;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ModifierUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Field;

/**
 * @author Yakir
 * @Topic BeanUtil
 * @Description
 * @date 2022/11/11 16:52
 * @Version 1.0
 */
public class BeanUtil {

	/**
	 * 判断Bean是否为空对象，空对象表示本身为{@code null}或者所有属性都为{@code null}<br>
	 * 此方法不判断static属性
	 * @param bean Bean对象
	 * @param ignoreFiledNames 忽略检查的字段名
	 * @return 是否为空，{@code true} - 空 / {@code false} - 非空
	 * @since 4.1.10
	 */
	public static boolean isEmpty(Object bean, String... ignoreFiledNames) {
		if (null != bean) {
			for (Field field : ReflectUtil.getFields(bean.getClass())) {
				if (ModifierUtil.isStatic(field)) {
					continue;
				}
				Object fieldValue = ReflectUtil.getFieldValue(bean, field);
				Class<?> fieldType = field.getType();
				if (String.class.isAssignableFrom(fieldType)) {
					if (false == ArrayUtil.contains(ignoreFiledNames, field.getName())
							&& StrUtil.isNotEmpty((CharSequence) fieldValue)) {
						return false;
					}
				}
				else if ((false == ArrayUtil.contains(ignoreFiledNames, field.getName())) && null != fieldValue) {
					return false;
				}
			}
		}
		return true;
	}

}
