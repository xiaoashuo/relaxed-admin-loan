package com.relaxed.boot.loan.util.keystore;

import com.relaxed.boot.loan.util.keystore.pkcs12.Pkcs;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.X500Name;

import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.Map;

/**
 * @author Yakir
 * @Topic KeystoreUtil
 * @Description
 * 参考
 * https://blog.csdn.net/Talent1223/article/details/120431662
 * https://blog.csdn.net/qq_30336433/article/details/83819572
 * https://juejin.cn/post/6921607505999036429#heading-0
 * https://www.jianshu.com/p/f16b1cd8148a
 * http://aizuda.com/article/1087160
 * @date 2022/12/13 19:34
 * @Version 1.0
 */
public class KeystoreUtil {
    public static void createKeyStoreFile() throws Exception {
        String filePath = "D:\\mnt\\itext7\\keystore\\home.keystore";
        final int keySize = 2048;
        final String commonName = "xu.dm";
        final String organizationalUnit = "com.home";
        final String organization = "home";
        final String city = "km";
        final String state = "yn";
        final String country = "cn";
        final long validity = 3650; // 10 years
        final String alias = "home";
        final String keyPassword = "123456";
        // keytool工具
        CertAndKeyGen keyGen = new CertAndKeyGen("RSA", "SHA1WithRSA");
        // 通用信息
        X500Name x500Name = new X500Name(commonName, organizationalUnit, organization, city, state, country);
        // 根据密钥长度生成公钥和私钥
        keyGen.generate(keySize);

        PrivateKey privateKey = keyGen.getPrivateKey();

        // 证书
        X509Certificate certificate = keyGen.getSelfCertificate(x500Name, new Date(), (long) validity * 24 * 60 * 60);


        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(null,null);
        keyStore.setKeyEntry(alias,privateKey,keyPassword.toCharArray(),new Certificate[]{certificate});

        FileOutputStream outputStream = new FileOutputStream(filePath);
        keyStore.store(outputStream,keyPassword.toCharArray());

        outputStream.close();
        System.out.println("keyStore file created ...");
    }
    public static void createKeyStoreFile1() throws Exception {
        String filePath = "D:\\mnt\\itext7\\keystore\\homejks.jks";
        final int keySize = 2048;
        final String commonName = "xu.dm";
        final String organizationalUnit = "com.home";
        final String organization = "home";
        final String city = "km";
        final String state = "yn";
        final String country = "cn";
        final long validity = 3650; // 10 years
        final String alias = "home";
        final String keyPassword = "123456";
        // keytool工具
        CertAndKeyGen keyGen = new CertAndKeyGen("RSA", "SHA1WithRSA");
        // 通用信息
        X500Name x500Name = new X500Name(commonName, organizationalUnit, organization, city, state, country);
        // 根据密钥长度生成公钥和私钥
        keyGen.generate(keySize);

        PrivateKey privateKey = keyGen.getPrivateKey();

        // 证书
        X509Certificate certificate = keyGen.getSelfCertificate(x500Name, new Date(), (long) validity * 24 * 60 * 60);


        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(null,null);
        keyStore.setKeyEntry(alias,privateKey,keyPassword.toCharArray(),new Certificate[]{certificate});

        FileOutputStream outputStream = new FileOutputStream(filePath);
        keyStore.store(outputStream,keyPassword.toCharArray());

        outputStream.close();
        System.out.println("keyStore file created ...");
    }
    public static PrivateKey getPrivateKey() throws Exception {
        String storepass = "13987664391";
        String keyAlias = "honor";
        BASE64Encoder base64Encoder = new BASE64Encoder();
        KeyStore keystore = KeyStore.getInstance("PKCS12");
        keystore.load(KeystoreUtil.class.getResourceAsStream("/key/home.pkcs12"), storepass.toCharArray());
        PrivateKey key = (PrivateKey) keystore.getKey(keyAlias, storepass.toCharArray());
        System.out.println(key.toString());
        String privateKeyStr = base64Encoder.encode(key.getEncoded());
        System.out.println();
        System.out.println("-----BEGIN PRIVATE KEY-----");
        System.out.println(privateKeyStr);
        System.out.println("-----END PRIVATE KEY-----");

        Certificate certificate = keystore.getCertificate(keyAlias);
        PublicKey publicKey = certificate.getPublicKey();
        System.out.println(publicKey);

        // 打印certificate的base64编码
        String certificateString = base64Encoder.encode(certificate.getEncoded());
        System.out.println();
        System.out.println("-----BEGIN CERTIFICATE-----");
        System.out.println(certificateString);
        System.out.println("-----END CERTIFICATE-----");

        return key;
    }

    /**
     * 从CERTIFICATE文本中提取public key字符串
     * CERTIFICATE本质是文本以"-----BEGIN CERTIFICATE-----"
     * 并以"-----END CERTIFICATE-----"结束
     */
    public static String getPublicKeyFromCertificate() throws CertificateException {
        InputStream inputStream = KeystoreUtil.class.getResourceAsStream("/key/home.PKCS12.cer");
        CertificateFactory ft = CertificateFactory.getInstance("X.509");
        X509Certificate certificate = (X509Certificate) ft.generateCertificate(inputStream);
        PublicKey publicKey = certificate.getPublicKey();
        BASE64Encoder b64 = new BASE64Encoder();
        String result = b64.encode(publicKey.getEncoded());
        System.out.println("-----BEGIN PUBLIC KEY-----");
        System.out.println(result);
        System.out.println("-----END PUBLIC KEY-----");
        return result;
    }

    /**
     * 从public key字符串中创建PublicKey对象
     *
     * @param signingKey 不包括"-----BEGIN PUBLIC KEY-----"和"-----END PUBLIC KEY-----"
     */
    public static PublicKey getRsaPublicKey(String signingKey) {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(new BASE64Decoder().decodeBuffer(signingKey));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据Keystore生成密钥对
     */
    public static KeyPair getKeyPair() throws Exception {
        String storepass = "13987664391";
        String keyAlias = "honor";
        KeyStore keystore = KeyStore.getInstance("PKCS12");
        keystore.load(KeystoreUtil.class.getResourceAsStream("/key/home.pkcs12"), storepass.toCharArray());

        RSAPrivateCrtKey key = (RSAPrivateCrtKey) keystore.getKey(keyAlias, storepass.toCharArray());
        RSAPublicKeySpec spec = new RSAPublicKeySpec(key.getModulus(), key.getPublicExponent());
        PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(spec);
        return new KeyPair(publicKey, key);
    }


    public static void main(String[] args) throws Exception {
        createKeyStoreFile1();;
//        KeystoreMeta keystoreMeta = new KeystoreMeta()
//                .password("123456")
//                .certificateCRL("https://relaxed.cn")
//                .alias("xiaoxi")
//                .info().CN("Yakir")
//                .OU("relaxed研发部")
//                .O("relaxed有限公司")
//                .L("上海")
//                .E("relaxed@qq.com")
//                .ST("上海")
//                .C("CN").end();
//        Pkcs pkcs = new Pkcs();
//        Map<String, byte[]> result = pkcs.createCert(keystoreMeta);
//
//        String targetPath="D:\\mnt\\itext7\\keystore\\";
//        FileOutputStream outPutStream = new FileOutputStream(targetPath+"keystore.p12"); // ca.jks
//        outPutStream.write(result.get("keyStoreData"));
//        outPutStream.close();
//        FileOutputStream fos = new FileOutputStream(new File(targetPath+"keystore.cer"));
//        fos.write(result.get("certificateData"));
//        fos.flush();
//        fos.close();
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
