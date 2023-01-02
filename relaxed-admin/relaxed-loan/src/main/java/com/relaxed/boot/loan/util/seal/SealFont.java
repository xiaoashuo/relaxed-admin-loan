package com.relaxed.boot.loan.util.seal;

import lombok.Builder;
import lombok.Data;

/**
 * @author Yakir
 * @Topic SealFont
 * @Description
 * @date 2022/12/13 9:55
 * @Version 1.0
 */

@Builder
@Data
public class SealFont {

	private String text;

	private String family;

	private Integer size;

	private Boolean bold;

	private Double space;

	private Integer margin;

	public String getFamily() {
		return family == null ? "宋体" : family;
	}

	public boolean getBold() {
		return bold == null ? true : bold;
	}

	public SealFont append(String text) {
		this.text += text;
		return this;
	}

}
