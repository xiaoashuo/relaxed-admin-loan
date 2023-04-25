package com.relaxed.boot.loan.util.pdf;

import com.relaxed.boot.loan.util.KeywordLocation;
import lombok.Data;

import java.util.List;

/**
 * @author Yakir
 * @Topic ImageSignMeta
 * @Description
 * @date 2023/1/31 9:51
 * @Version 1.0
 */
@Data
public class ImageSignMeta {

	/**
	 * 图片地址
	 */
	private String imgPath;

	/**
	 * 是否在内容上 添加内容 默认为false
	 */
	private boolean contentBefore = false;

	/**
	 * X轴偏移量
	 */
	private float offsetX;

	/**
	 * Y轴偏移量
	 */
	private float offsetY;

	private List<KeywordLocation> keywordLocationList;

}
