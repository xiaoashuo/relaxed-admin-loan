package com.relaxed.boot.common.system.event;

import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

/**
 * @author Yakir
 * @Topic DictChangeEvent
 * @Description
 * @date 2022/11/11 15:43
 * @Version 1.0
 */
@Getter
@ToString
public class DictChangeEvent extends ApplicationEvent {

	private final String dictCode;

	public DictChangeEvent(String dictCode) {
		super(dictCode);
		this.dictCode = dictCode;
	}

}
