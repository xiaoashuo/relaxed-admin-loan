package com.relaxed.boot.loan.util.keystore;

import com.relaxed.boot.loan.util.keystore.pkcs12.Pkcs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Map;

/**
 * @author Yakir
 * @Topic KeystoreUtil
 * @Description
 * 参考
 * https://blog.csdn.net/Talent1223/article/details/120431662
 * https://blog.csdn.net/qq_30336433/article/details/83819572
 * @date 2022/12/13 19:34
 * @Version 1.0
 */
public class KeystoreUtil {
    public static void main(String[] args) throws Exception {
        KeystoreMeta keystoreMeta = new KeystoreMeta()
                .password("123456")
                .certificateCRL("https://relaxed.cn")
                .alias("xiaoxi")
                .info().CN("Yakir")
                .OU("relaxed研发部")
                .O("relaxed有限公司")
                .L("上海")
                .E("relaxed@qq.com")
                .ST("上海")
                .C("CN").end();
        Pkcs pkcs = new Pkcs();
        Map<String, byte[]> result = pkcs.createCert(keystoreMeta);

        String targetPath="D:\\mnt\\itext7\\keystore\\";
        FileOutputStream outPutStream = new FileOutputStream(targetPath+"keystore.p12"); // ca.jks
        outPutStream.write(result.get("keyStoreData"));
        outPutStream.close();
        FileOutputStream fos = new FileOutputStream(new File(targetPath+"keystore.cer"));
        fos.write(result.get("certificateData"));
        fos.flush();
        fos.close();
        //  loadCert();


    }

    private static void loadCert() throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        String KEYSTORE="D:\\mnt\\keystore.p12";
        char[] PASSWORD="123456".toCharArray();
        //读取keystore ，获得私钥和证书链 jks
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream(KEYSTORE), PASSWORD);
        String alias = ks.aliases().nextElement();
        PrivateKey pk = (PrivateKey) ks.getKey(alias, PASSWORD);
        //获取证书链
        Certificate[] chain = ks.getCertificateChain(alias);
        System.out.println(pk);
    }
}
