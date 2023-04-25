package com.relaxed.boot.risk.config.engine;

import com.relaxed.boot.risk.core.cache.CacheService;
import com.relaxed.boot.risk.core.cache.RedisCacheService;

import com.relaxed.boot.risk.core.handler.DefaultFieldValidateHandler;
import com.relaxed.boot.risk.core.handler.DefaultRiskReportHandler;
import com.relaxed.boot.risk.core.handler.FieldValidateHandler;
import com.relaxed.boot.risk.core.handler.RiskReportHandler;
import com.relaxed.boot.risk.core.risk.LocalRiskEvalAsyncDistributor;
import com.relaxed.boot.risk.core.risk.RiskEvalAsyncDistributor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yakir
 * @Topic RiskEngineConfiguration
 * @Description
 * @date 2021/8/29 10:05
 * @Version 1.0
 */
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
public class RiskEngineConfiguration {

	/**
	 * 缓存服务
	 * @author yakir
	 * @date 2021/8/29 10:06
	 * @return com.relaxed.common.risk.engine.cache.CacheService
	 */
	@Bean
	@ConditionalOnMissingBean
	public CacheService cacheService() {
		return new RedisCacheService();
	}

	/**
	 * 异步风控评估分发器
	 * @author yakir
	 * @date 2021/9/26 9:28
	 * @return com.relaxed.common.risk.biz.distributor.risk.RiskEvalAsyncDistributor
	 */
	@Bean
	@ConditionalOnMissingBean
	public RiskEvalAsyncDistributor riskEvalAsyncDistributor() {
		return new LocalRiskEvalAsyncDistributor();
	}

	/**
	 * 字段验证处理器
	 * @author yakir
	 * @date 2021/8/29 13:48
	 * @return com.relaxed.common.risk.engine.core.handler.FieldValidateHandler
	 */
	@Bean
	@ConditionalOnMissingBean
	public FieldValidateHandler fieldValidateHandler() {
		return new DefaultFieldValidateHandler();
	}

	/**
	 * 风控评估报告处理器
	 * @author yakir
	 * @date 2021/8/31 15:23
	 * @return com.relaxed.common.risk.engine.core.handler.RiskReportHandler
	 */
	@Bean
	@ConditionalOnMissingBean
	public RiskReportHandler riskReportHandler() {
		return new DefaultRiskReportHandler();
	}

}
