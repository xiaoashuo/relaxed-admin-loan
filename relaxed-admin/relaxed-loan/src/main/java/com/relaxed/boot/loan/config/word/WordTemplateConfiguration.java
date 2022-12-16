package com.relaxed.boot.loan.config.word;

import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;

import com.relaxed.boot.loan.service.TemplateService;
import com.relaxed.boot.loan.util.word.StoreWordTemplate;
import com.relaxed.boot.loan.util.word.WordTemplateFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
public class WordTemplateConfiguration {

	private final TemplateService templateService;

	@Bean
	public RemoteWordTemplateProvider remoteWordTemplateProvider() {
		return new RemoteWordTemplateProvider(templateService);
	}

	/**
	 * 注册模板工具类
	 * @author yakir
	 * @date 2022/4/2 16:12
	 * @return com.relaxed.third.util.word.WordTemplate
	 */
	@Bean
	public StoreWordTemplate wordTemplate(RemoteWordTemplateProvider remoteWordTemplateProvider) {
		ConfigureBuilder builder = Configure.builder();
		builder.buildGramer("${", "}");
		Configure configure = builder.build();
		return WordTemplateFactory.create(configure, remoteWordTemplateProvider);

	}

}
