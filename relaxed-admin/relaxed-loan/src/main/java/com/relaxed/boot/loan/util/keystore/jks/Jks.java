package com.relaxed.boot.loan.util.keystore.jks;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.X500Name;

import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.X509Certificate;

/**
 * @author Yakir
 * @Topic Jks
 * @Description https://blog.csdn.net/Talent1223/article/details/120431662
 * @date 2022/12/13 20:37
 * @Version 1.0
 */
public class Jks {
    public static void main(String[] args) throws Exception {
        //1.创建空密钥库
        KeyStore keyStore = KeyStore.getInstance("JKS");

        keyStore.load(null,null);
        CertAndKeyGen gen = new CertAndKeyGen("RSA","SHA1WithRSA");

        gen.generate(1024);

        Key key=gen.getPrivateKey();

        X509Certificate cert=gen.getSelfCertificate(new X500Name("CN=ROOT"), (long)365*24*3600);

        X509Certificate[] chain = new X509Certificate[1];

        chain[0]=cert;
        //设置私钥
        keyStore.setKeyEntry("mykey", key, "password".toCharArray(), chain);

        //2.
        keyStore.store(new FileOutputStream("D:\\mnt\\itext7\\keystore\\mytestkey.jks"), "123456".toCharArray());


    }
}
