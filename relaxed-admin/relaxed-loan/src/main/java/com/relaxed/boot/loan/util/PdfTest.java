package com.relaxed.boot.loan.util;

import com.relaxed.boot.loan.util.KeywordLocation;
import java.util.ArrayList;

import cn.hutool.core.util.IdUtil;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.StampingProperties;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.parser.PdfDocumentContentParser;
import com.itextpdf.kernel.pdf.canvas.parser.listener.IPdfTextLocation;
import com.itextpdf.kernel.pdf.canvas.parser.listener.RegexBasedLocationExtractionStrategy;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.signatures.BouncyCastleDigest;
import com.itextpdf.signatures.DigestAlgorithms;
import com.itextpdf.signatures.IExternalDigest;
import com.itextpdf.signatures.IExternalSignature;
import com.itextpdf.signatures.PdfSignatureAppearance;
import com.itextpdf.signatures.PdfSignatureAppearance.RenderingMode;
import com.itextpdf.signatures.PdfSigner;
import com.itextpdf.signatures.PrivateKeySignature;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author Yakir
 * @Topic PdfTest
 * @Description
 * @date 2022/12/12 13:59
 * @Version 1.0
 */
public class PdfTest {

	public static final String KEYSTORE = "D:\\other\\100000\\itext\\keystore\\test.keystore";// keystore文件路径

	public static final char[] PASSWORD = "123456".toCharArray();// keystore密码

	public static final String IMG = "D:\\other\\100000\\itext\\keystore\\seal.png";// 印章图片路径

	public static void main(String[] args) throws Exception {
		String sourceFile = "D:\\other\\100000\\itext\\test.pdf";
		String targetFile = "D:\\other\\100000\\itext\\" + IdUtil.getSnowflakeNextId() + ".pdf";
		// 通过指定pdf文件名，指定关键字，和指定的pdf文件的待处理页数做参数
		// getKeyWordsLocation(sourceFile, targetFile,"国民信托有限公司", 14);
		// generateSealImage("D:\\other\\100000\\seal.png");

		List<KeywordLocation> wordsLocation1 = getKeyWordsLocation1(sourceFile, "国民信托有限公司");
		PreviewSignInfo previewSignInfo = new PreviewSignInfo();
		previewSignInfo.setImgPath("D:\\other\\100000\\itext\\image\\test.png");
		previewSignInfo.setContentBefore(true);
		previewSignInfo.setKeywordLocationList(wordsLocation1);

		PdfUtil.addImage(sourceFile, targetFile, previewSignInfo);
		// BouncyCastleProvider provider = new BouncyCastleProvider();
		// Security.addProvider(provider);
		// //读取keystore ，获得私钥和证书链 jks
		// KeyStore ks = KeyStore.getInstance("JKS");
		// ks.load(new FileInputStream("D:\\mnt\\itext7\\keystore\\homejks.jks"),
		// PASSWORD);
		// String alias = ks.aliases().nextElement();
		// PrivateKey pk = (PrivateKey) ks.getKey(alias, PASSWORD);
		// //获取证书链
		// Certificate[] chain = ks.getCertificateChain(alias);
		// 读取keystore ，获得私钥和证书链 PKCS12
		// String KEYSTORE="D:\\mnt\\itext7\\keystore\\keystore.p12";
		// KeyStore ks = KeyStore.getInstance("PKCS12");
		// ks.load(new FileInputStream(KEYSTORE), PASSWORD);
		// String alias = ks.aliases().nextElement();
		// PrivateKey pk = (PrivateKey) ks.getKey(alias, PASSWORD);
		// //获取证书链
		// Certificate[] chain = ks.getCertificateChain(alias);
		// SignInfo signInfo=new SignInfo();
		// signInfo.setReason("测试");
		// signInfo.setLocation("北京市");
		// signInfo.setContact("xxx@qq.com");
		// signInfo.setImagePath("D:\\other\\100000\\testsign.png");
		// signInfo.setFieldName("Signature1");
		// signInfo.setExistSignArea(false);
		// signInfo.setPageNumber(1);
		// signInfo.setChain(chain);
		// signInfo.setRenderingMode(RenderingMode.GRAPHIC);
		// signInfo.setPk(pk);
		// signInfo.setX(100);
		// signInfo.setY(200);
		// signInfo.setWidth(100);
		// signInfo.setImageScale(-1);
		// signInfo.setHeight(100);
		// PdfUtil.multiSign(sourceFile,targetFile,wordsLocation1,signInfo);
		// System.out.println("生成后文件名"+targetFile);
		// SignInfo signInfo=new SignInfo();
		// signInfo.setReason("测试");
		// signInfo.setLocation("北京市");
		// signInfo.setContact("xxx@qq.com");
		// //章图片最好用无背景图层的 否则会遮盖内容
		// signInfo.setDescription("测试。还会*@rts$");
		// signInfo.setImagePath("D:\\mnt\\itext7\\images\\testsign.png");
		// signInfo.setFieldName("ts");
		// signInfo.setPageNumber(1);
		// signInfo.setChain(chain);
		// signInfo.setRenderingMode(RenderingMode.GRAPHIC_AND_DESCRIPTION);
		// signInfo.setPk(pk);
		// signInfo.setX(100);
		// signInfo.setY(700);
		// signInfo.setWidth(400);
		// signInfo.setHeight(100);
		//
		// PdfUtil.sign(sourceFile,targetFile,signInfo);
		// PdfUtil.sign(sourceFile,targetFile,signInfo);
		// SignInfo signInfo1=new SignInfo();
		// signInfo1.setReason("测试");
		// signInfo1.setLocation("北京市");
		// signInfo1.setContact("xxx@qq.com");
		// signInfo1.setImagePath("D:\\other\\100000\\test24.png");
		// signInfo1.setFieldName("ts1");
		// signInfo1.setPageNumber(2);
		// signInfo1.setChain(chain);
		// signInfo1.setDescription("relaxed Signed on " + new Date());
		// signInfo1.setRenderingMode(RenderingMode.GRAPHIC_AND_DESCRIPTION);
		// signInfo1.setPk(pk);
		// signInfo1.setX(100);
		// signInfo1.setY(200);
		// signInfo1.setWidth(400);
		// signInfo1.setHeight(100);
		// String targetFile1 = "D:\\other\\100000\\itext\\" + IdUtil.getSnowflakeNextId()
		// + ".pdf";
		// PdfUtil.sign(targetFile,targetFile1,signInfo1);
		// System.out.println("原始文件路径"+sourceFile+"\n中间文件路径"+targetFile+"\n最终文件路径"+targetFile1);
		// sign(sourceFile,targetFile);
		// ImageData image = ImageDataFactory.create("D:\\other\\100000\\test24.png");
		// 此方法指定签名 若想不指定 则需要设置 pageRect

	}

	/**
	 * 电子签章 * @param src 需要签章的pdf文件路径 * @param dest 签完章的pdf文件路径
	 */
	public static void sign(String src, String dest) {
		final String KEYSTORE = "D:\\other\\100000\\itext\\keystore\\test.keystore";// keystore文件路径
		final char[] PASSWORD = "123456".toCharArray();// keystore密码
		final String STAMPER_SRC = "D:\\other\\100000\\itext\\keystore\\seal.png";// 印章图片路径
		try (PdfReader reader = new PdfReader(src); FileOutputStream os = new FileOutputStream(dest);) {
			// 读取keystore ，获得私钥和证书链 jks
			KeyStore ks = KeyStore.getInstance("JKS");
			ks.load(new FileInputStream(KEYSTORE), PASSWORD);
			String alias = ks.aliases().nextElement();
			PrivateKey pk = (PrivateKey) ks.getKey(alias, PASSWORD);
			// 获取证书链
			Certificate[] chain = ks.getCertificateChain(alias);
			// 创建签章工具PdfSigner、设定数字签章的属性
			// 加盖图章图片
			// 使用png格式透明图片

			ImageData img = ImageDataFactory.create(STAMPER_SRC);
			Image image = new Image(img);
			Rectangle rect = new Rectangle(50, 50, image.getImageWidth(), image.getImageHeight());
			// Pass the temporary file's path to the PdfSigner constructor
			// 使用临时文件签署文档以避免OutOfMemoryExceptions使用大型 PDF：
			// PdfSigner signer = new PdfSigner(reader, new FileOutputStream(dest), temp,
			// new StampingProperties());
			// PdfSigner signer = new PdfSigner(reader, new FileOutputStream(dest), new
			// StampingProperties().useAppendMode());

			PdfSigner stamper = new PdfSigner(reader, os, new StampingProperties());
			// // This name corresponds to the name of the field that already exists in
			// the document.
			// stamper.setFieldName("sig");
			PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
			appearance.setReason("签名原因：系统自动签名盖章");
			appearance.setLocation("签名地点：xxx系统");
			appearance.setContact("联系方式：relaxed@qq.com");
			// 使用png格式透明图片
			// appearance.setImageScale(0);
			// appearance.setSignatureGraphic(img);
			// appearance.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC);
			//// Specify if the appearance before field is signed will be used
			//// as a background for the signed field. The "false" value is the default
			// value.
			// appearance .setReuseAppearance(false);
			appearance.setPageNumber(1);
			appearance.setPageRect(rect);
			appearance.setSignatureGraphic(img);
			appearance.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC);
			// No such provider: BC : 问题解决，加BC库支持 算法提供者
			Security.addProvider(new BouncyCastleProvider());
			// 摘要算法
			IExternalDigest digest = new BouncyCastleDigest();
			// 签名算法
			IExternalSignature signature = new PrivateKeySignature(pk, DigestAlgorithms.SHA256,
					BouncyCastleProvider.PROVIDER_NAME);
			// 调用itext签名方法完成pdf签章
			stamper.setCertificationLevel(1);
			// Sign the document using the detached mode, CMS or CAdES equivalent.
			stamper.signDetached(digest, signature, chain, null, null, null, 0, PdfSigner.CryptoStandard.CMS);
			System.out.println("操作完成！");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("操作异常...");
		}
	}

	/**
	 * 生成章图片
	 * @param targetImagePath
	 */
	public static void generateSealImage(String targetImagePath) {
		int canvasWidth = 400;
		int canvasHeight = 400;
		double lineArc = 80 * (Math.PI / 180);// 角度转弧度

		SimpleDateFormat format = new SimpleDateFormat("yyyy'年'MM'月'dd'日'");
		String head = "Relaxed-Admin";
		String foot = "受理专用章";
		String center = format.format(new Date());
		BufferedImage image = GraphicsUtil.getCircleSeal(head, center, foot, canvasWidth, canvasHeight, lineArc);

		try {
			ImageIO.write(image, "PNG", new File(targetImagePath));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		// GraphicsUtil.getRectangleSignTextImg("华佗", "在线医院", "2018.01.01", "sign.jpg");

	}

	/**
	 * 得到关键字位置
	 * @param input 源文件
	 * @param KEY_WORD 关键字
	 * @param pageNum 文档页数
	 */
	public static void getKeyWordsLocation(String input, String target, String KEY_WORD, int pageNum) {
		RegexBasedLocationExtractionStrategy strategy = new RegexBasedLocationExtractionStrategy(KEY_WORD);
		try {
			// 得到需要插入的图片
			ImageData imageData = ImageDataFactory.create("D:\\other\\100000\\testsign.png");
			// 核心思路为对PdfDocument对象采用某种Strategy，这里使用RegexBasedLocationExtractionStrategy
			PdfReader pr = new PdfReader(input);
			// 生成新的PDF文件
			PdfDocument pd = new PdfDocument(pr, new PdfWriter(target));
			// int pageNum = pd.getNumberOfPages();获取新pdf的总页数
			Document document = new Document(pd);
			PdfDocumentContentParser pdcp = new PdfDocumentContentParser(pd);
			// 文本内容具体解析借助使用PdfDocumentContentParser类(实质使用PdfCanvasProcessor进行处理)，
			// 对待处理页面装配合适策略
			RegexBasedLocationExtractionStrategy regexStrategy = pdcp.processContent(pageNum, strategy);
			// 获取处理结果
			Collection<IPdfTextLocation> resultantLocations = strategy.getResultantLocations();
			// 自定义结果处理
			if (!resultantLocations.isEmpty()) {
				for (IPdfTextLocation item : resultantLocations) {
					Rectangle boundRectangle = item.getRectangle();
					System.out.println(item.getText());
					System.out.println("关键字“" + KEY_WORD + "” 的坐标为 x: " + boundRectangle.getX() + "  ,y: "
							+ boundRectangle.getY());
					Image image = new Image(imageData).scaleAbsolute(70, 40).setFixedPosition(pageNum,
							boundRectangle.getRight() + 5f, boundRectangle.getBottom());
					document.add(image);
				}
				document.close();
			}
			else {
				System.out.println("结果为空");
			}

			pd.close();
		}
		catch (Exception e) {
			System.err.println("读取文件失败!");
			e.printStackTrace();
		}
	}

	public static List<KeywordLocation> getKeyWordsLocation1(String sourceFile, String keyword) {
		List<KeywordLocation> keywordLocations = PdfUtil.extractKeywordLocation(sourceFile, keyword);
		for (KeywordLocation keywordLocation : keywordLocations) {
			Integer pageNo = keywordLocation.getPageNum();
			List<IPdfTextLocation> items = keywordLocation.getIpdfTextLocations();
			for (IPdfTextLocation item : items) {
				Rectangle boundRectangle = item.getRectangle();
				System.out.println("当前页码" + pageNo + "关键字" + keyword + "” 的坐标为 x: " + boundRectangle.getX() + "  ,y: "
						+ boundRectangle.getY());

			}
		}
		return keywordLocations;
	}

}
