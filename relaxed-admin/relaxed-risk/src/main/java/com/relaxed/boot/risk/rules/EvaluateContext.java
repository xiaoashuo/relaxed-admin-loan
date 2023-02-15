package com.relaxed.boot.risk.rules;

import com.relaxed.boot.risk.model.vo.RiskModelVO;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Yakir
 * @Topic EvaluateContext
 * @Description 评估上下文
 * @date 2021/8/29 8:38
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class EvaluateContext implements Serializable {

	/**
	 * 请求id
	 */
	private String reqId;

	/**
	 * 模型vo
	 */
	private RiskModelVO modelVo;

	/**
	 * 事件json
	 */
	private Map eventJson;

	/**
	 * 预处理项参数字段存储
	 */
	private Map<String, Object> preItemMap;

}
