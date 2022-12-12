package com.relaxed.boot.loan.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.StampingProperties;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.parser.PdfCanvasProcessor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.IPdfTextLocation;
import com.itextpdf.kernel.pdf.canvas.parser.listener.RegexBasedLocationExtractionStrategy;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.BackgroundImage;
import com.itextpdf.signatures.BouncyCastleDigest;
import com.itextpdf.signatures.IExternalDigest;
import com.itextpdf.signatures.IExternalSignature;
import com.itextpdf.signatures.PdfSignatureAppearance;
import com.itextpdf.signatures.PdfSigner;
import com.itextpdf.signatures.PrivateKeySignature;
import lombok.SneakyThrows;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Yakir
 * @Topic PdfUtil
 * @Description
 * @date 2022/12/12 16:14
 * @Version 1.0
 */
public class PdfUtil {


    public void t(String keystore,String password) throws Exception {
        //读取keystore ，获得私钥和证书链 jks
        KeyStore ks = KeyStore.getInstance("JKS");
        char[] pwdByteArray = password.toCharArray();
        ks.load(new FileInputStream(keystore), pwdByteArray);
        String alias = ks.aliases().nextElement();
        PrivateKey pk = (PrivateKey) ks.getKey(alias, pwdByteArray);
        //获取证书链
        Certificate[] chain = ks.getCertificateChain(alias);
    }



    @SneakyThrows
    public static  void  multiSign(String source,String dest,List<KeywordLocation> keywordLocationList, SignInfo signInfo){
        //  No such provider: BC : 问题解决，加BC库支持 算法提供者
        BouncyCastleProvider provider = new BouncyCastleProvider();
        Security.addProvider(provider);
        //私钥
        PrivateKey pk = signInfo.getPk();
        InputStream inputStream=null;
        ByteArrayOutputStream result=new ByteArrayOutputStream();
        FileOutputStream os = null;
        //获取证书链
        Certificate[] chain = signInfo.getChain();
        try {

        //关键字出现页数循环
        for (int i = 0, size=keywordLocationList.size(); i < size; i++) {
            KeywordLocation keywordLocation = keywordLocationList.get(i);
            Integer pageNum = keywordLocation.getPageNum();
            String fieldName = signInfo.getFieldName();
            List<IPdfTextLocation> ipdfTextLocations = keywordLocation.getIpdfTextLocations();
            //关键字出现坐标循环
            for (int j = 0, locationSize=ipdfTextLocations.size(); j < locationSize; j++) {
                IPdfTextLocation iPdfTextLocation = ipdfTextLocations.get(j);
                String tmpFiledName=fieldName+i+"_"+j;
                 Rectangle rectangle = iPdfTextLocation.getRectangle();
                ByteArrayOutputStream tmpOutStream=new ByteArrayOutputStream();
                if (i==0&&j==0){
                    inputStream=new FileInputStream(source);
                }else{
                    inputStream= new ByteArrayInputStream(
                            result.toByteArray());
                }
                PdfReader reader = new PdfReader(inputStream);
                PdfSigner signer = new PdfSigner(reader, tmpOutStream, new StampingProperties().useAppendMode());
                signer.setFieldName(tmpFiledName);
                signer.setCertificationLevel(signInfo.getCertificationLevel());
                PdfSignatureAppearance signatureAppearance = signer.getSignatureAppearance();
                signatureAppearance.setReason(signInfo.getReason());
                signatureAppearance.setLocation(signInfo.getLocation());
                signatureAppearance.setContact(signInfo.getContact());
                if (!signInfo.isExistSignArea()){
                    //TODO 原始文字宽度高度太小，此处使用用户填充的
                    rectangle.setWidth(signInfo.getWidth());
                    rectangle.setHeight(signInfo.getHeight());
                    signatureAppearance.setPageRect(rectangle);
                }
                signatureAppearance.setPageNumber(pageNum);
                signatureAppearance.setImageScale(signInfo.getImageScale());
                signatureAppearance.setLayer2Text(signInfo.getDescription());
                ImageData img = ImageDataFactory.create(signInfo.getImagePath());
                signatureAppearance.setSignatureGraphic(img);
                signatureAppearance.setRenderingMode(signInfo.getRenderingMode());
                IExternalSignature pks = new PrivateKeySignature(pk,signInfo.getDigestAlgorithm() , provider.getName());
                // 摘要算法
                IExternalDigest digest = new BouncyCastleDigest();
                // Sign the document using the detached mode, CMS or CAdES equivalent.数字签名格式，CMS,CADE
                signer.signDetached(digest,pks, chain, null, null, null, 0, PdfSigner.CryptoStandard.CMS);
                result=tmpOutStream;
            }

        }
        os = new FileOutputStream(dest);
        os.write(result.toByteArray());
        os.flush();
        }finally {
            IoUtil.close(result);
            IoUtil.close(inputStream);
            IoUtil.close(os);
        }

    }
    /**
     * 单文件签名
     * @param source
     * @param dest
     * @param signInfo
     */
    @SneakyThrows
    public static  void  sign(String source,String dest, SignInfo signInfo){
        //  No such provider: BC : 问题解决，加BC库支持 算法提供者
        BouncyCastleProvider provider = new BouncyCastleProvider();
        Security.addProvider(provider);
        //私钥
        PrivateKey pk = signInfo.getPk();
        //获取证书链
        Certificate[] chain = signInfo.getChain();
        try (PdfReader reader = new PdfReader(source);
             FileOutputStream os = new FileOutputStream(dest)){
            // 创建签章工具PdfSigner ，StampingProperties useAppendMode 参数是否允许被追加签名
            // false的话，pdf文件只允许被签名一次，多次签名，最后一次有效
            // true的话，pdf可以被追加签名，验签工具可以识别出每次签名之后文档是否被修改
            PdfSigner signer = new PdfSigner(reader, os, new StampingProperties().useAppendMode());
            //使用临时文件签署文档以避免OutOfMemoryExceptions使用大型 PDF：
            // PdfSigner signer = new PdfSigner(reader, new FileOutputStream(dest), temp, new StampingProperties());
            //签名字段名称
            signer.setFieldName(signInfo.getFieldName());
            signer.setCertificationLevel(signInfo.getCertificationLevel());
            // 获取数字签章属性对象
            PdfSignatureAppearance signatureAppearance = signer.getSignatureAppearance();
            signatureAppearance.setReason(signInfo.getReason());
            signatureAppearance.setLocation(signInfo.getLocation());
            signatureAppearance.setContact(signInfo.getContact());
            //模板签名域优先级最高  指定了签名域此属性设置 也不生效
            if (!signInfo.isExistSignArea()){
                // 设置签名的位置，页码，签名域名称，多次追加签名的时候，签名预名称不能一样 图片大小受表单域大小影响（过小导致压缩）
                // 签名的位置， 是图章相对于pdf页面的位置坐标，原点为pdf页面左下角
                // 四个参数的分别是，图章左下角x，图章左下角y，图章右上角x，图章右上角y
                Rectangle pageRect = new Rectangle(signInfo.getX(), signInfo
                        .getY(), signInfo.getWidth(),
                        signInfo.getHeight());
                //若在pdf文件中 设置了签名域  则 此处不需要设置
                signatureAppearance.setPageRect(pageRect);
            }
            signatureAppearance.setPageNumber(signInfo.getPageNumber());
            //设置要应用于背景图像的缩放比例。 默认为0
            signatureAppearance.setImageScale(signInfo.getImageScale());
            //文字描述
            signatureAppearance.setLayer2Text(signInfo.getDescription());
// 读取图章图片
            ImageData img = ImageDataFactory.create(signInfo.getImagePath());
            signatureAppearance.setSignatureGraphic(img);

            // 设置图章的显示方式，如下选择的是只显示图章（还有其他的模式，可以图章和签名描述一同显示）
            signatureAppearance.setRenderingMode(signInfo.getRenderingMode());
            IExternalSignature pks = new PrivateKeySignature(pk,signInfo.getDigestAlgorithm() , provider.getName());
            // 摘要算法
            IExternalDigest digest = new BouncyCastleDigest();
            // Sign the document using the detached mode, CMS or CAdES equivalent.数字签名格式，CMS,CADE
            signer.signDetached(digest,pks, chain, null, null, null, 0, PdfSigner.CryptoStandard.CMS);
        }

    }

    /**
     * 底层图层绘制背景
     * @param src
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public static void signLayer(String src, String dest,SignInfo signInfo)
            throws GeneralSecurityException, IOException {
        //  No such provider: BC : 问题解决，加BC库支持 算法提供者
        BouncyCastleProvider provider = new BouncyCastleProvider();
        Security.addProvider(provider);
        //私钥
        PdfReader reader = new PdfReader(src);
        PdfSigner signer = new PdfSigner(reader, new FileOutputStream(dest), new StampingProperties());

        // Create the signature appearance
        PdfSignatureAppearance appearance = signer.getSignatureAppearance()
                .setReason(signInfo.getReason())
                .setLocation(signInfo.getLocation());

        // This name corresponds to the name of the field that already exists in the document.
        signer.setFieldName(signInfo.getFieldName());
        appearance.setPageRect(new Rectangle(150,250,300,300));
        // Get the background layer and draw a gray rectangle as a background.
        PdfFormXObject n0 = appearance.getLayer0();
        float x = n0.getBBox().toRectangle().getLeft();
        float y = n0.getBBox().toRectangle().getBottom();
        float width = n0.getBBox().toRectangle().getWidth();
        float height = n0.getBBox().toRectangle().getHeight();

        PdfCanvas canvas = new PdfCanvas(n0, signer.getDocument());


        ImageData img = ImageDataFactory.create(signInfo.getImagePath());
//        Image image = new Image(img);

//        new Canvas(n0,signer.getDocument()).add(image);
//        canvas.saveState();
//        PdfExtGState state = new PdfExtGState();
//        state.setFillOpacity(0f);
//        canvas.setExtGState(state);
//        canvas.addImageAt(img, 103, 614, false);
//        canvas.restoreState();




        // Set the signature information on layer 2
        PdfFormXObject n2 = appearance.getLayer2();
        Paragraph p = new Paragraph("This document was signed by Bruno Specimen.");
        new Canvas(n2, signer.getDocument()).add(p);

        IExternalSignature pks = new PrivateKeySignature(signInfo.getPk(), signInfo.getDigestAlgorithm(), provider.getName());
        IExternalDigest digest = new BouncyCastleDigest();

        // Sign the document using the detached mode, CMS or CAdES equivalent.
        signer.signDetached(digest, pks, signInfo.getChain(), null, null, null, 0, PdfSigner.CryptoStandard.CMS);
    }

    /**
     * 提取关键字
     * @param source 原文件路径
     * @param keyword 关键字
     * @return
     */
    public static List<KeywordLocation> extractKeywordLocation (String source,String keyword)  {
        return extractKeywordLocation(source,null,keyword);
    }

    /**
     * 提取关键字
     * @param source 源文件路径
     * @param specifyPageNum 指定页码
     * @param keyword 关键字
     * @return
     */
    @SneakyThrows
    public static List<KeywordLocation> extractKeywordLocation (String source, Integer specifyPageNum, String keyword) {
        List<KeywordLocation> allKeywords=new ArrayList<>();
        try (PdfDocument pdfDoc = new PdfDocument(new PdfReader(new FileInputStream(source)))){
            if (specifyPageNum!=null){
                PdfPage page = pdfDoc.getPage(specifyPageNum);
                allKeywords.addAll(extractPageKeywordLocation(page,specifyPageNum,keyword));
            }
            else {
                int numberOfPages = pdfDoc.getNumberOfPages();
                for (int pageNum = 1; pageNum <= numberOfPages; pageNum++) {
                    PdfPage page = pdfDoc.getPage(pageNum);
                    allKeywords.addAll(extractPageKeywordLocation(page,pageNum,keyword));
                }
            }
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
    public static  List<KeywordLocation>  extractPageKeywordLocation(PdfPage page , Integer pageNum, String keyword){
        List<KeywordLocation> localKeywords=new ArrayList<>();
        // Create a text extraction renderer
        RegexBasedLocationExtractionStrategy strategy = new RegexBasedLocationExtractionStrategy(keyword);
        // Note: If you want to re-use the PdfCanvasProcessor, you must call PdfCanvasProcessor.reset()
        PdfCanvasProcessor parser = new PdfCanvasProcessor(strategy);
        parser.processPageContent(page);
        Collection<IPdfTextLocation> resultantLocations = strategy.getResultantLocations();
        if (CollectionUtil.isNotEmpty(resultantLocations)){
            KeywordLocation keywordLocation = new KeywordLocation();
            keywordLocation.setPageNum(pageNum);
            keywordLocation.getIpdfTextLocations().addAll(resultantLocations);
            localKeywords.add(keywordLocation);
        }
        return localKeywords;
    }
}
