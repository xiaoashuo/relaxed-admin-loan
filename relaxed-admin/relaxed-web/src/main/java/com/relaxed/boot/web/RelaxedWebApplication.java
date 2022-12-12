package com.relaxed.boot.web;

import com.relaxed.autoconfigure.log.annotation.EnableAccessLog;
import com.relaxed.autoconfigure.log.annotation.EnableOperationLog;
import com.relaxed.oauth2.auth.annotation.EnableOauth2AuthorizationServer;
import com.relaxed.oauth2.resource.annotation.EnableOauth2ResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Yakir
 * @Topic RelaxedWebApplication
 * @Description
 * @date 2022/11/11 10:17
 * @Version 1.0
 */
@EnableOauth2AuthorizationServer
@EnableOauth2ResourceServer
@EnableAccessLog
@EnableOperationLog
@MapperScan(basePackages = "com.relaxed.**.mapper")
@SpringBootApplication(scanBasePackages = { "com.relaxed" })
public class RelaxedWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(RelaxedWebApplication.class, args);
	}

}
