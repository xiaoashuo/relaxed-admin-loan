package com.relaxed.boot.risk.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Yakir
 * @Topic HitObjectDTO
 * @Description
 * @date 2021/8/31 11:24
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class Hit {

	private String key;

	private Double value;

	private String desc;

}
