package com.relaxed.boot.loan.util.pdf;

import com.itextpdf.signatures.DigestAlgorithms;
import com.itextpdf.signatures.PdfSignatureAppearance;
import com.itextpdf.signatures.PdfSigner;
import com.relaxed.boot.loan.util.KeywordLocation;
import lombok.Data;

import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.util.List;

/**
 * @author Yakir
 * @Topic MultiSignMeta
 * @Description
 * @date 2023/1/31 11:37
 * @Version 1.0
 */
@Data
public class MultiSignMeta {

	/**
	 * 证书链
	 */
	private Certificate[] chain;

	/**
	 * 签名私钥
	 */
	private PrivateKey pk;

	private String fieldName;

	/**
	 * 签名的原因，显示在pdf签名属性中
	 */
	private String reason;

	/**
	 * 签名的地点，显示在pdf签名属性中
	 */
	private String location;

	/**
	 * The contact name of the signer.
	 */
	private String contact = "";

	/**
	 * 文件是否存在签名域
	 */
	private boolean existSignArea = false;

	/**
	 * layer2 层 签名描述文本 渲染模式包含 RenderingMode.DESCRIPTION 显示
	 */
	private String description;

	/**
	 * 图章路径 渲染模式包含 RenderingMode.GRAPHIC 显示 章图片最好用无背景图层的 否则会遮盖内容
	 */
	private String imagePath;

	/**
	 * 图片缩放比列 默认为0
	 */
	private Integer imageScale = 0;

	/**
	 * x偏移量 正 像右便宜 负 像左
	 */
	private float offsetX;

	/**
	 * y 偏移量 正 向上 负向下
	 */
	private float offsetY;

	private float width;

	private float height;

	/**
	 * 批准签章
	 */
	private int certificationLevel = PdfSigner.NOT_CERTIFIED;

	/**
	 * 表现形式：仅描述，仅图片，图片和描述，签章者和描述
	 */
	private PdfSignatureAppearance.RenderingMode renderingMode = PdfSignatureAppearance.RenderingMode.DESCRIPTION;

	/**
	 * 摘要算法名称，例如SHA-1
	 */
	private String digestAlgorithm = DigestAlgorithms.SHA256;

	List<KeywordLocation> keywordLocationList;

}
