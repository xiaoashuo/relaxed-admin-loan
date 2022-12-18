package com.relaxed.boot.loan.util;

import cn.hutool.core.io.IoUtil;
import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author Yakir
 * @Topic FileConvert
 * @Description
 * @date 2022/12/18 16:10
 * @Version 1.0
 */
public class FileConvert {
    private static String license = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" + "<License>\n" + "    <Data>\n"
            + "        <Products>\n" + "            <Product>Aspose.Total for Java</Product>\n"
            + "            <Product>Aspose.Words for Java</Product>\n" + "        </Products>\n"
            + "        <EditionType>Enterprise</EditionType>\n"
            + "        <SubscriptionExpiry>20991231</SubscriptionExpiry>\n"
            + "        <LicenseExpiry>20991231</LicenseExpiry>\n"
            + "        <SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber>\n" + "    </Data>\n"
            + "    <Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature>\n"
            + "</License>";

    public static void initLicense() throws Exception {
        ByteArrayInputStream inputStream = IoUtil.toStream(license.getBytes(StandardCharsets.UTF_8));
        License aposeLic = new License();
        aposeLic.setLicense(inputStream);
    }

    /**
     * doc to pdf 注意在linux会出现中文转换不了 需手动安装字体库 解决方案如下
     * https://blog.csdn.net/ZGL_cyy/article/details/122837609
     * 解决办法：把Windows下字体全部拷贝到Linux目录下，并安装。具体步骤： <pre>
     * 1） cd /usr/share/fonts 进入该目录下，新建文件件chinese
     * 2） mkdir chinese
     * 3） 找到 Windows 的中文字体，上传到 Linux 的 chinese 文件夹下面。Windows
     * 下面的字体目录在：C:\Windows\Fonts。将里面所有的文件传输至 chinese目录下
     * 4）进入刚才建的chinese目录，给字体赋予权限，并执行以下操作：
     * chmod 777 *.TTF
     * chmod 777 *.TTC
     * rm *.FON
     * mkfontscale (如果提示 mkfontscale: command not found，需自行安装 # yum install mkfontscale)
     * mkfontdir
     * fc-cache (如果提示 fc-cache: command not found，则需要安装 # yum install fontconfig
     * 若发现还是乱码 则重启项目 重新载入字体即可
     * )
     * </pre>
     * @author yakir
     * @date 2022/4/1 14:21
     * @param inputStream
     * @param outputStream
     */
    public static void doc2pdf(InputStream inputStream, OutputStream outputStream) {

        try {
            initLicense();
            Document doc = new Document(inputStream); // Address是将要被转化的word文档
            // 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, // EPUB, XPS, SWF 相互转换
            doc.save(outputStream, SaveFormat.PDF);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            IoUtil.close(inputStream);
            IoUtil.close(outputStream);
        }
    }
}
