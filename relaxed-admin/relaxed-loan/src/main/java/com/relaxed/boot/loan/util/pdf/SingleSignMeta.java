package com.relaxed.boot.loan.util.pdf;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.signatures.DigestAlgorithms;
import com.itextpdf.signatures.PdfSignatureAppearance;
import com.itextpdf.signatures.PdfSigner;
import lombok.Data;

import java.security.PrivateKey;
import java.security.cert.Certificate;

/**
 * @author Yakir
 * @Topic SingleSignMeta
 * @Description
 * @date 2023/1/31 10:44
 * @Version 1.0
 */
@Data
public class SingleSignMeta {

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
     * 摘要算法名称，例如SHA-1
     */
    private String digestAlgorithm = DigestAlgorithms.SHA256;

    /**
     * 文件是否存在签名域
     */
    private boolean existSignArea = false;

    /**
     * layer2 层 签名描述文本 渲染模式包含 RenderingMode.DESCRIPTION 显示
     */
    private String description;

    /**
     * pdf 字体 PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN)
     * //PdfFontFactory.createFont("STSong-Light",
     * "UniGB-UCS2-H",PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED) 中文字体
     */
    private PdfFont pdfFont;

    /**
     * 描述文本颜色 默认为红色
     */
    private Color color = ColorConstants.RED;

    /**
     * 图章路径 渲染模式包含 RenderingMode.GRAPHIC 显示 章图片最好用无背景图层的 否则会遮盖内容
     */
    private String imagePath;

    /**
     * 图片缩放比列 默认为0
     */
    private Integer imageScale = 0;

    /**
     * 表单域名称 不能重复
     */
    private String fieldName;

    /**
     * 签名页码
     */
    private Integer pageNumber;

    /**
     * 证书链
     */
    private Certificate[] chain;

    /**
     * 签名私钥
     */
    private PrivateKey pk;

    /**
     * 批准签章
     */
    private int certificationLevel = PdfSigner.NOT_CERTIFIED;

    /**
     * 表现形式：仅描述，仅图片，图片和描述，签章者和描述
     */
    private PdfSignatureAppearance.RenderingMode renderingMode = PdfSignatureAppearance.RenderingMode.DESCRIPTION;

    /**
     * 签名区域 位置属性 x 左下角x y 左下角y width 宽 height 高
     */
    private float x;


    private float y;
    private float width;

    private float height;
}
