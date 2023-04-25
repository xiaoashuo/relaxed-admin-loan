package com.relaxed.boot.loan.model.bo;

import cn.hutool.json.JSONObject;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Yakir
 * @Topic RiskResBO
 * @Description
 * @date 2023/3/14 13:59
 * @Version 1.0
 */
@Data
public class RiskResBO {

	private Integer code;

	private String message;

	private JSONObject data;

	@Data
	public class Risk {

		private String risk;

		private Double score;

	}

	@Data
	public class RiskRule {

		private String key;

		private BigDecimal value;

		private String desc;

	}

	@Data
	public class Content {

		private LocalDateTime startTime;

		private LocalDateTime endTime;

		private Map<String, Object> phaseTime;

		private Map<String, Object> report;

	}

}
