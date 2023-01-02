package com.relaxed.boot.common.system.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Yakir
 * @Topic SysMenuType
 * @Description
 * @date 2022/11/11 14:19
 * @Version 1.0
 */
@RequiredArgsConstructor
@Getter
public enum SysDictKey {

	FILE_TYPE_KEY("file_type"),
	;

	private final String key;

}
