package com.relaxed.boot.risk.core.handler;

/**
 * @author Yakir
 * @Topic RiskReportHandler
 * @Description 主要用于风控报告的后续处理 可以发送到别的服务器
 * @date 2021/8/31 15:12
 * @Version 1.0
 */
public interface RiskReportHandler {

	/**
	 * 对风控报告后续处理 转发等
	 * @author yakir
	 * @date 2021/8/31 15:17
	 * @param modelGuid
	 * @param reqId
	 * @param evaluateReport
	 */
	void handle(String modelGuid, String reqId, String evaluateReport);

}
