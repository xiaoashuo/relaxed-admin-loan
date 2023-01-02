package com.relaxed.boot.loan.util.word;

import com.deepoove.poi.config.Configure;
import com.relaxed.boot.loan.util.word.provider.WordTemplateProvider;

public class WordTemplateFactory {

	public static DefaultWordTemplate create() {
		return new DefaultWordTemplate();
	}

	public static StoreWordTemplate create(Configure configure, WordTemplateProvider wordTemplateProvider) {
		StoreWordTemplate wordTemplate = new StoreWordTemplate(configure, wordTemplateProvider);
		return wordTemplate;
	}

}
