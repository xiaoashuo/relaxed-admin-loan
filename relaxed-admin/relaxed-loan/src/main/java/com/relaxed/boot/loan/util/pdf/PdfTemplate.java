package com.relaxed.boot.loan.util.pdf;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfStream;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.StampingProperties;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.parser.PdfCanvasProcessor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.IPdfTextLocation;
import com.itextpdf.kernel.pdf.canvas.parser.listener.RegexBasedLocationExtractionStrategy;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.signatures.BouncyCastleDigest;
import com.itextpdf.signatures.IExternalDigest;
import com.itextpdf.signatures.IExternalSignature;
import com.itextpdf.signatures.PdfSignatureAppearance;
import com.itextpdf.signatures.PdfSigner;
import com.itextpdf.signatures.PrivateKeySignature;
import com.relaxed.boot.loan.util.KeywordLocation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Yakir
 * @Topic PdfTemplate
 * @Description
 * @date 2023/1/31 9:49
 * @Version 1.0
 */
@Slf4j
public class PdfTemplate {

    public static void main(String[] args) throws Exception {
        String imgPath = "D:\\other\\100000\\local\\profile\\seal\\1d4c77209-9271-4a25-b2b6-9863ccc9703a.png";
        String sourcePath="D:\\other\\100000\\local\\profile\\contract\\20230130\\fbbcb08b-3793-4791-b8f5-3e8aaa72288f.pdf";
        String targetPath="D:\\other\\100000\\local\\profile\\contract\\20230130\\"+ IdUtil.getSnowflakeNextIdStr()+".pdf";


        //testAddImage(imgPath,sourcePath,destPath);
        String keyStorePath="D:\\other/100000/local/profile/keystore/aa265c5a-9f9f-4e92-a2d4-9b67c7f19090.jks";
        char[] password="123456".toCharArray();
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(new FileInputStream(keyStorePath), password);
        String alias = ks.aliases().nextElement();
        PrivateKey pk = (PrivateKey) ks.getKey(alias, password);
        // 获取证书链
        Certificate[] chain = ks.getCertificateChain(alias);
        File sourceFile = new File(sourcePath);
        File destFile = new File(targetPath);
        SingleSignMeta signMeta = new SingleSignMeta();
        signMeta.setReason("测试签字");
        signMeta.setLocation("上海市");
        signMeta.setContact("Yakir");
        signMeta.setExistSignArea(false);
        signMeta.setDescription("今天是个好日子");
        signMeta.setImagePath(imgPath);
        signMeta.setFieldName("sign");
        signMeta.setPageNumber(1);
        signMeta.setChain(chain);
        signMeta.setPk(pk);

        signMeta.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC_AND_DESCRIPTION);
        signMeta.setX(30);
        signMeta.setY(200);
        signMeta.setWidth(100);
        signMeta.setHeight(100);

        sign(sourceFile,destFile,signMeta);
        log.info("目标文件地址:{}",targetPath);
    }

    private static void testAddImage(String imgPath,String sourcePath,String targetPath) {
        File sourceFile = new File(sourcePath);
        File destFile = new File(targetPath);
        ImageSignMeta signMeta=new ImageSignMeta();
        signMeta.setImgPath(imgPath);
        signMeta.setContentBefore(false);
        signMeta.setOffsetX(0);
        signMeta.setOffsetY(30);
        List<KeywordLocation> keywordLocationList = extractKeywordLocation(sourceFile, "贷款合同");
        signMeta.setKeywordLocationList(keywordLocationList);
        addImage(sourceFile,destFile,signMeta);
        log.info("目标文件地址:{}",targetPath);
    }

    @SneakyThrows
    public static void multiSign(File source,File target,MultiSignMeta signMeta){
        multiSign(new FileInputStream(source),new FileOutputStream(target),signMeta);
    }

    public static void multiSign(InputStream source,OutputStream target,MultiSignMeta signMeta){
        // No such provider: BC : 问题解决，加BC库支持 算法提供者
        BouncyCastleProvider provider = new BouncyCastleProvider();
        Security.addProvider(provider);
        //私钥
        PrivateKey pk = signMeta.getPk();
        //证书链
        Certificate[] chain = signMeta.getChain();
        List<KeywordLocation> keywordLocationList = signMeta.getKeywordLocationList();
        //中间文件
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        InputStream inputStream = null;
        try {
            for (int i = 0, size = keywordLocationList.size(); i < size; i++) {
                KeywordLocation keywordLocation = keywordLocationList.get(i);
                Integer pageNum = keywordLocation.getPageNum();
                String fieldName = signMeta.getFieldName();
                List<IPdfTextLocation> ipdfTextLocations = keywordLocation.getIpdfTextLocations();
                //循环当前页关键字
                for (int j = 0, locationSize = ipdfTextLocations.size(); j < locationSize; j++) {
                    IPdfTextLocation iPdfTextLocation = ipdfTextLocations.get(j);
                    String tmpFiledName = fieldName + i + "_" + j;
                    Rectangle rectangle = iPdfTextLocation.getRectangle();
                    ByteArrayOutputStream tmpOutStream = new ByteArrayOutputStream();
                    if (i == 0 && j == 0) {
                        inputStream = source;
                    }
                    else {
                        inputStream = new ByteArrayInputStream(result.toByteArray());
                    }
                    PdfReader reader = new PdfReader(inputStream);
                    PdfSigner signer = new PdfSigner(reader, tmpOutStream, new StampingProperties().useAppendMode());
                    signer.setFieldName(tmpFiledName);
                    signer.setCertificationLevel(signMeta.getCertificationLevel());
                    PdfSignatureAppearance signatureAppearance = signer.getSignatureAppearance();
                    signatureAppearance.setReason(signMeta.getReason());
                    signatureAppearance.setLocation(signMeta.getLocation());
                    signatureAppearance.setContact(signMeta.getContact());
                    if (!signMeta.isExistSignArea()) {
                        // TODO 原始文字宽度高度太小，此处使用用户填充的
                        float offsetX = signMeta.getOffsetX();
                        float offsetY = signMeta.getOffsetY();
                        rectangle.setX(rectangle.getX()+offsetX);
                        rectangle.setY(rectangle.getY()+offsetY);
                        rectangle.setWidth(signMeta.getWidth());
                        rectangle.setHeight(signMeta.getHeight());
                        signatureAppearance.setPageRect(rectangle);
                    }
                    signatureAppearance.setPageNumber(pageNum);
                    signatureAppearance.setImageScale(signMeta.getImageScale());
                    signatureAppearance.setLayer2Text(signMeta.getDescription());
                    ImageData img = ImageDataFactory.create(signMeta.getImagePath());
                    signatureAppearance.setSignatureGraphic(img);
                    signatureAppearance.setRenderingMode(signMeta.getRenderingMode());
                    IExternalSignature pks = new PrivateKeySignature(pk, signMeta.getDigestAlgorithm(),
                            provider.getName());
                    // 摘要算法
                    IExternalDigest digest = new BouncyCastleDigest();
                    // Sign the document using the detached mode, CMS or CAdES
                    // equivalent.数字签名格式，CMS,CADE
                    signer.signDetached(digest, pks, chain, null, null, null, 0, PdfSigner.CryptoStandard.CMS);
                    result = tmpOutStream;
                }

           }

            target.write(result.toByteArray());
            target.flush();
        }catch (Exception e){
           throw ExceptionUtil.wrapRuntime(e);
        }finally {
            IoUtil.close(result);
            IoUtil.close(inputStream);
            IoUtil.close(source);
            IoUtil.close(target);
        }
    }
    @SneakyThrows
    public static void  sign(File source,File dest,SingleSignMeta signMeta){
        sign(new FileInputStream(source),new FileOutputStream(dest),signMeta);
    }



    /**
     * 指定页面 指定位置签名
     * @param source 源文件
     * @param dest 目标文件
     * @param signMeta 签名信息
     */
    public static void sign(InputStream source,OutputStream dest,SingleSignMeta signMeta){
        // No such provider: BC : 问题解决，加BC库支持 算法提供者
        BouncyCastleProvider provider = new BouncyCastleProvider();
        Security.addProvider(provider);
        // 私钥
        PrivateKey pk = signMeta.getPk();
        // 获取证书链
        Certificate[] chain = signMeta.getChain();
        try (PdfReader reader = new PdfReader(source);) {
            // 创建签章工具PdfSigner ，StampingProperties useAppendMode 参数是否允许被追加签名
            // false的话，pdf文件只允许被签名一次，多次签名，最后一次有效
            // true的话，pdf可以被追加签名，验签工具可以识别出每次签名之后文档是否被修改
            PdfSigner signer = new PdfSigner(reader, dest, new StampingProperties().useAppendMode());
            // 使用临时文件签署文档以避免OutOfMemoryExceptions使用大型 PDF：
            // PdfSigner signer = new PdfSigner(reader, new FileOutputStream(dest), temp,
            // new StampingProperties());
            // 签名字段名称
            signer.setFieldName(signMeta.getFieldName());
            signer.setCertificationLevel(signMeta.getCertificationLevel());
            // 获取数字签章属性对象
            PdfSignatureAppearance signatureAppearance = signer.getSignatureAppearance();
            signatureAppearance.setReason(signMeta.getReason());
            signatureAppearance.setLocation(signMeta.getLocation());
            signatureAppearance.setContact(signMeta.getContact());
            // 模板签名域优先级最高 指定了签名域此属性设置 也不生效
            if (!signMeta.isExistSignArea()) {
                // 设置签名的位置，页码，签名域名称，多次追加签名的时候，签名预名称不能一样 图片大小受表单域大小影响（过小导致压缩）
                // 签名的位置， 是图章相对于pdf页面的位置坐标，原点为pdf页面左下角
                // 四个参数的分别是，图章左下角x，图章左下角y，图章右上角x，图章右上角y
                Rectangle pageRect = new Rectangle(signMeta.getX(), signMeta.getY(), signMeta.getWidth(),
                        signMeta.getHeight());
                // 若在pdf文件中 设置了签名域 则 此处不需要设置
                signatureAppearance.setPageRect(pageRect);
            }
            signatureAppearance.setPageNumber(signMeta.getPageNumber());
            // 设置要应用于背景图像的缩放比例。 默认为0
            signatureAppearance.setImageScale(signMeta.getImageScale());
            // 文字描述
            signatureAppearance.setLayer2Text(signMeta.getDescription());
            PdfFont pdfFont = signMeta.getPdfFont();
            if (pdfFont == null) {
                // 该方法为通过添加font的路径的方式让FontProvider对象自行加载字体列表
                // 该方法的优点是便捷，缺点是对于字体的同族支持效果较差容易出现无效果
                // fontProvider.addDirectory(prePath + "config\\fonts");
                // 该方法类似于上面，可以根据设置的路径加载单个字体
                // fp里面的其他方法基本与addFont相同所添加的字体属性基本一致
                // FontProvider fp = new FontProvider();
                // fp.addFont(fontPath);
                // 该方法与第二种不同的在于可以设置alias
                // FontProvider fp = new FontProvider();
                // fp.getFontSet().addFont(prePath + "config\\fonts\\Times New Roman.ttf",
                // null, "timesnewromanpsmt");
                // 参考 https://blog.csdn.net/b45bobo/article/details/124335157
                pdfFont = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H",
                        PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED);
            }
//            PdfFormXObject layer0 = signatureAppearance.getLayer0();
//            Paragraph p = new Paragraph("This document was signed by Bruno Specimen.");
//            new Canvas(layer0, signer.getDocument()).add(p);
            signatureAppearance.setLayer2Font(pdfFont);
            signatureAppearance.setLayer2FontColor(signMeta.getColor());
            // 读取图章图片
            ImageData img = ImageDataFactory.create(signMeta.getImagePath());
            signatureAppearance.setSignatureGraphic(img);

            // 设置图章的显示方式，如下选择的是只显示图章（还有其他的模式，可以图章和签名描述一同显示）
            signatureAppearance.setRenderingMode(signMeta.getRenderingMode());
            IExternalSignature pks = new PrivateKeySignature(pk, signMeta.getDigestAlgorithm(), provider.getName());
            // 摘要算法
            IExternalDigest digest = new BouncyCastleDigest();
            // Sign the document using the detached mode, CMS or CAdES
            // equivalent.数字签名格式，CMS,CADE
            signer.signDetached(digest, pks, chain, null, null, null, 0, PdfSigner.CryptoStandard.CMS);
        }catch (Exception e){
            throw ExceptionUtil.wrapRuntime(e);
        }

    }

    @SneakyThrows
    public static void addImage(File source, File dest,ImageSignMeta signMeta){
        addImage(new FileInputStream(source),new FileOutputStream(dest),signMeta);
    }
    public static void addImage(InputStream source, OutputStream dest,
                               ImageSignMeta signMeta ){
        try(PdfReader pdfReader=new PdfReader(source);
            PdfDocument pdfDoc = new PdfDocument(pdfReader, new PdfWriter(dest));   ){
            ImageData img = ImageDataFactory.create(signMeta.getImgPath());
            Image image = new Image(img);
            List<KeywordLocation> keywordLocationList = signMeta.getKeywordLocationList();
            for (KeywordLocation keywordLocation : keywordLocationList) {
                Integer pageNum = keywordLocation.getPageNum();
                List<IPdfTextLocation> ipdfTextLocations = keywordLocation.getIpdfTextLocations();
                PdfPage page = pdfDoc.getPage(pageNum);
                // 低级别
                PdfCanvas pdfCanvas = new PdfCanvas(page);
                // 我们想在已经存在的内容下添加内容，因此我们使用newContentStreamBefore()方法。
                // 如果你想要在已经存在的内容上添加内容，你应该使用newContentStreamAfter()方法
                // 这些方法会创建一个新的内容流，并且把它添加到页面中
                PdfStream pdfStream = signMeta.isContentBefore() ? page.newContentStreamBefore()
                        : page.newContentStreamAfter();
                pdfCanvas.attachContentStream(pdfStream);
                for (IPdfTextLocation ipdfTextLocation : ipdfTextLocations) {
                    Rectangle rectangle = ipdfTextLocation.getRectangle();
                    rectangle.setX(rectangle.getX()+signMeta.getOffsetX());
                    rectangle.setY(rectangle.getY() + signMeta.getOffsetY());
                    //Rectangle rectangle = new Rectangle(0, image.getImageHeight() + 80, image.getImageWidth() + 100,
                    //					image.getImageHeight() + 10);
                    // pdf canvas 填充CanvasRenderer 可以知道是否填充满 当前区域 区域
                    // https://blog.csdn.net/u012397189/article/details/91346951
                    // pdfCanvas.rectangle(rectangle);
                    // pdfCanvas.saveState();
                    // 设置透明
                    // PdfExtGState pdfExtGState = new PdfExtGState();
                    // pdfExtGState.setFillOpacity(0.8f);
                    // pdfCanvas.setExtGState(pdfExtGState);
                    // pdfCanvas.addImageAt(img,0,300,false);
                    // 恢复存储前状态
                    // pdfCanvas.restoreState();
                    // 填充区域 高级别api
                    Canvas canvas = new Canvas(pdfCanvas, rectangle);
                    canvas.add(image);
                    canvas.close();
                }
            }
        }catch (Exception e){
            throw ExceptionUtil.wrapRuntime(e);
        }finally {
            IoUtil.close(source);
            IoUtil.close(dest);
        }
    }

    /**
     * 提取关键字
     * @param source 原文件路径
     * @param keyword 关键字
     * @return
     */
    @SneakyThrows
    public static List<KeywordLocation> extractKeywordLocation(String source, String keyword) {
        return extractKeywordLocation(new FileInputStream(source), null, keyword);
    }
    @SneakyThrows
    public static List<KeywordLocation> extractKeywordLocation(File source,
                                                               String keyword) {
        return extractKeywordLocation(new FileInputStream(source), null, keyword);
    }

    @SneakyThrows
    public static List<KeywordLocation> extractKeywordLocation(File source, Integer specifyPageNum,
                                                               String keyword) {
        return extractKeywordLocation(new FileInputStream(source), specifyPageNum, keyword);
    }
    @SneakyThrows
    public static List<KeywordLocation> extractKeywordLocation(InputStream source,
                                                               String keyword) {
        return extractKeywordLocation(source, null, keyword);
    }
    /**
     * 提取关键字
     * @param source 源文件路径
     * @param specifyPageNum 指定页码
     * @param keyword 关键字
     * @return
     */

    public static List<KeywordLocation> extractKeywordLocation(InputStream source, Integer specifyPageNum, String keyword) {
        List<KeywordLocation> allKeywords = new ArrayList<>();
        try (PdfDocument pdfDoc = new PdfDocument(new PdfReader(source))) {
            if (specifyPageNum != null) {
                PdfPage page = pdfDoc.getPage(specifyPageNum);
                allKeywords.addAll(extractPageKeywordLocation(page, specifyPageNum, keyword));
            }
            else {
                int numberOfPages = pdfDoc.getNumberOfPages();
                for (int pageNum = 1; pageNum <= numberOfPages; pageNum++) {
                    PdfPage page = pdfDoc.getPage(pageNum);
                    allKeywords.addAll(extractPageKeywordLocation(page, pageNum, keyword));
                }
            }
        }catch (Exception e){
            throw ExceptionUtil.wrapRuntime(e);
        }
        return allKeywords;
    }



    /**
     * 提取页面关键字位置
     * @param page
     * @param pageNum
     * @param keyword
     * @return
     */
    public static List<KeywordLocation> extractPageKeywordLocation(PdfPage page, Integer pageNum, String keyword) {
        List<KeywordLocation> localKeywords = new ArrayList<>();
        // Create a text extraction renderer
        RegexBasedLocationExtractionStrategy strategy = new RegexBasedLocationExtractionStrategy(keyword);
        // Note: If you want to re-use the PdfCanvasProcessor, you must call
        // PdfCanvasProcessor.reset()
        PdfCanvasProcessor parser = new PdfCanvasProcessor(strategy);
        parser.processPageContent(page);
        Collection<IPdfTextLocation> resultantLocations = strategy.getResultantLocations();
        if (CollectionUtil.isNotEmpty(resultantLocations)) {
            KeywordLocation keywordLocation = new KeywordLocation();
            keywordLocation.setPageNum(pageNum);
            keywordLocation.getIpdfTextLocations().addAll(resultantLocations);
            localKeywords.add(keywordLocation);
        }
        return localKeywords;
    }

}
