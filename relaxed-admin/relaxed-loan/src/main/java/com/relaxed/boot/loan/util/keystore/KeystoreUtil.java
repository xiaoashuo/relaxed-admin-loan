package com.relaxed.boot.loan.util.keystore;

import cn.hutool.core.io.FileUtil;
import com.relaxed.boot.loan.util.keystore.pkcs12.KeystoreExtension;
import com.relaxed.boot.loan.util.keystore.pkcs12.Pkcs;
import lombok.SneakyThrows;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.*;

import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Yakir
 * @Topic KeystoreUtil
 * @Description 参考 https://blog.csdn.net/Talent1223/article/details/120431662
 * https://blog.csdn.net/qq_30336433/article/details/83819572
 * https://juejin.cn/post/6921607505999036429#heading-0
 * https://www.jianshu.com/p/f16b1cd8148a http://aizuda.com/article/1087160
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
		keyStore.load(null, null);
		keyStore.setKeyEntry(alias, privateKey, keyPassword.toCharArray(), new Certificate[] { certificate });

		FileOutputStream outputStream = new FileOutputStream(filePath);
		keyStore.store(outputStream, keyPassword.toCharArray());

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
		keyStore.load(null, null);
		keyStore.setKeyEntry(alias, privateKey, keyPassword.toCharArray(), new Certificate[] { certificate });

		FileOutputStream outputStream = new FileOutputStream(filePath);
		keyStore.store(outputStream, keyPassword.toCharArray());

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
	 * 从CERTIFICATE文本中提取public key字符串 CERTIFICATE本质是文本以"-----BEGIN CERTIFICATE-----"
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
	 * @param signingKey 不包括"-----BEGIN PUBLIC KEY-----"和"-----END PUBLIC KEY-----"
	 */
	public static PublicKey getRsaPublicKey(String signingKey) {
		try {
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(new BASE64Decoder().decodeBuffer(signingKey));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(keySpec);
			return publicKey;
		}
		catch (Exception e) {
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

	public static X509Certificate getSelfCertificate(X500Name subject, X500Name issuer, Date startDate, long validity,
			String signAlg, PrivateKey privateKey, PublicKey publicKey, CertificateExtensions var5)
			throws CertificateException, InvalidKeyException, SignatureException, NoSuchAlgorithmException,
			NoSuchProviderException {
		try {
			Date endDate = new Date();
			endDate.setTime(startDate.getTime() + validity * 1000L);
			CertificateValidity var8 = new CertificateValidity(startDate, endDate);
			X509CertInfo certInfo = new X509CertInfo();
			certInfo.set("version", new CertificateVersion(2));
			certInfo.set("serialNumber", new CertificateSerialNumber((new Random()).nextInt() & 2147483647));
			AlgorithmId var10 = AlgorithmId.get(signAlg);
			certInfo.set("algorithmID", new CertificateAlgorithmId(var10));
			certInfo.set("subject", subject);
			certInfo.set("key", new CertificateX509Key(publicKey));
			certInfo.set("validity", var8);
			certInfo.set("issuer", issuer);
			if (var5 != null) {
				certInfo.set("extensions", var5);
			}

			X509CertImpl var6 = new X509CertImpl(certInfo);
			var6.sign(privateKey, signAlg);
			return var6;
		}
		catch (IOException var11) {
			throw new CertificateEncodingException("getSelfCert: " + var11.getMessage());
		}
	}

	@SneakyThrows
	public static ByteArrayOutputStream generateKeyStore(KeystoreMeta keystoreMeta) {

		// 密钥长度
		final int keySize = 2048;
		final long validity = keystoreMeta.getValidity(); // 10 years
		final String alias = keystoreMeta.getAlias();
		final char[] keyPassword = keystoreMeta.getPassword();
		// keytool工具
		// 签名算法
		String signAlg = "SHA1WithRSA";
		CertAndKeyGen keyGen = new CertAndKeyGen("RSA", signAlg);

		// 根据密钥长度生成公钥和私钥
		keyGen.generate(keySize);

		PrivateKey privateKey = keyGen.getPrivateKey();
		PublicKey publicKey = keyGen.getPublicKeyAnyway();

		// 证书
		// 通用信息
		X500Name issuerX500Name = new X500Name(keystoreMeta.getIssuer());
		X500Name subjectX500Name = new X500Name(keystoreMeta.getSubject());
		// 不可以带E此种方法构建
		X509Certificate certificate = getSelfCertificate(subjectX500Name, issuerX500Name, new Date(), validity, signAlg,
				privateKey, publicKey, null);
		// X500Name x500Name = new X500Name(commonName, organizationalUnit, organization,
		// city, state, country);
		// X509Certificate certificate = keyGen.getSelfCertificate(x500Name, new Date(),
		// (long) validity * 24 * 60 * 60);

		KeyStore keyStore = KeyStore.getInstance(keystoreMeta.getKeystoreType());
		keyStore.load(null, null);
		keyStore.setKeyEntry(alias, privateKey, keyPassword, new Certificate[] { certificate });

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		keyStore.store(outputStream, keyPassword);
		outputStream.close();
		System.out.println("keyStore file created ...");
		return outputStream;
	}

	private static KeyPair getKey() throws NoSuchAlgorithmException {
		// 密钥对 生成器，RSA算法 生成的 提供者是 BouncyCastle
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", new BouncyCastleProvider());
		generator.initialize(1024); // 密钥长度 1024
		// 证书中的密钥 公钥和私钥
		KeyPair keyPair = generator.generateKeyPair();
		return keyPair;
	}

	@SneakyThrows
	public static ByteArrayOutputStream generateKeyStoreV3(KeystoreMeta keystoreMeta) {

		// 密钥长度
		final int keySize = 2048;
		final long validity = keystoreMeta.getValidity(); // 10 years
		final String alias = keystoreMeta.getAlias();
		final char[] keyPassword = keystoreMeta.getPassword();
		// keytool工具
		// 签名算法
		String signAlg = "SHA1WithRSA";

		// // 密钥对 生成器，RSA算法 生成的 提供者是 BouncyCastle
		// KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", new
		// BouncyCastleProvider());
		// generator.initialize(1024); // 密钥长度 1024
		// // 证书中的密钥 公钥和私钥
		// KeyPair keyPair = generator.generateKeyPair();
		CertAndKeyGen keyGen = new CertAndKeyGen("RSA", signAlg);
		// 根据密钥长度生成公钥和私钥
		keyGen.generate(keySize);
		PrivateKey privateKey = keyGen.getPrivateKey();
		PublicKey publicKey = keyGen.getPublicKeyAnyway();
		// 证书
		// 通用信息
		// V3证书支持E
		Certificate certificate = generateCertificateV3(keystoreMeta, new Date(), privateKey, publicKey, null);
		// X500Name issuerX500Name = new X500Name(keystoreMeta.getIssuer());
		// X500Name subjectX500Name = new X500Name(keystoreMeta.getSubject());
		// 此处证书不支持E
		// X509Certificate certificate = getSelfCertificate(subjectX500Name,
		// issuerX500Name,
		// new Date(), validity, signAlg, privateKey, publicKey, null);
		// X500Name x500Name = new X500Name(commonName, organizationalUnit, organization,
		// city, state, country);
		// X509Certificate certificate = keyGen.getSelfCertificate(x500Name, new Date(),
		// (long) validity * 24 * 60 * 60);

		KeyStore keyStore = KeyStore.getInstance(keystoreMeta.getKeystoreType());
		keyStore.load(null, null);
		keyStore.setKeyEntry(alias, privateKey, keyPassword, new Certificate[] { certificate });

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		keyStore.store(outputStream, keyPassword);
		outputStream.close();
		System.out.println("keyStore file created ...");
		return outputStream;
	}

	public static Certificate generateCertificateV3(KeystoreMeta keystoreMeta, Date startDate, PrivateKey privateKey,
			PublicKey publicKey, List<KeystoreExtension> extensions) {

		ByteArrayInputStream bout = null;
		X509Certificate cert = null;
		try {

			Date endDate = new Date();
			endDate.setTime(startDate.getTime() + keystoreMeta.getValidity() * 1000L);
			// 证书序列号
			BigInteger serial = BigInteger.probablePrime(256, new Random());
			X509v3CertificateBuilder builder = new JcaX509v3CertificateBuilder(
					new org.bouncycastle.asn1.x500.X500Name(keystoreMeta.getIssuer()), serial, startDate, endDate,
					new org.bouncycastle.asn1.x500.X500Name(keystoreMeta.getSubject()), publicKey);
			JcaContentSignerBuilder jBuilder = new JcaContentSignerBuilder("SHA1withRSA");
			SecureRandom secureRandom = new SecureRandom();
			jBuilder.setSecureRandom(secureRandom);
			ContentSigner singer = jBuilder.setProvider(new BouncyCastleProvider()).build(privateKey);
			// 分发点
			ASN1ObjectIdentifier cRLDistributionPoints = new ASN1ObjectIdentifier("2.5.29.31");
			org.bouncycastle.asn1.x509.GeneralName generalName = new org.bouncycastle.asn1.x509.GeneralName(
					org.bouncycastle.asn1.x509.GeneralName.uniformResourceIdentifier, keystoreMeta.getCertificateCRL());
			org.bouncycastle.asn1.x509.GeneralNames seneralNames = new org.bouncycastle.asn1.x509.GeneralNames(
					generalName);
			org.bouncycastle.asn1.x509.DistributionPointName distributionPoint = new org.bouncycastle.asn1.x509.DistributionPointName(
					seneralNames);
			org.bouncycastle.asn1.x509.DistributionPoint[] points = new org.bouncycastle.asn1.x509.DistributionPoint[1];
			points[0] = new org.bouncycastle.asn1.x509.DistributionPoint(distributionPoint, null, null);
			CRLDistPoint cRLDistPoint = new CRLDistPoint(points);
			builder.addExtension(cRLDistributionPoints, true, cRLDistPoint);
			// 用途
			ASN1ObjectIdentifier keyUsage = new ASN1ObjectIdentifier("2.5.29.15");
			// | KeyUsage.nonRepudiation | KeyUsage.keyCertSign
			builder.addExtension(keyUsage, true, new KeyUsage(KeyUsage.digitalSignature | KeyUsage.keyEncipherment));
			// 基本限制 X509Extension.java
			ASN1ObjectIdentifier basicConstraints = new ASN1ObjectIdentifier("2.5.29.19");
			builder.addExtension(basicConstraints, true, new BasicConstraints(true));
			// privKey:使用自己的私钥进行签名，CA证书
			if (extensions != null) {
				for (KeystoreExtension ext : extensions) {
					builder.addExtension(new ASN1ObjectIdentifier(ext.getOid()), ext.isCritical(),
							ASN1Primitive.fromByteArray(ext.getValue()));
				}
			}
			X509CertificateHolder holder = builder.build(singer);
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			bout = new ByteArrayInputStream(holder.toASN1Structure().getEncoded());
			cert = (X509Certificate) cf.generateCertificate(bout);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (bout != null) {
				try {
					bout.close();
				}
				catch (IOException e) {
				}
			}
		}
		return cert;
	}

	public static void main(String[] args) throws Exception {
		createKeyStoreFile1();
		KeystoreMeta keystoreMeta = new KeystoreMeta().validity(3650).password("123456")
				.certificateCRL("https://relaxed.cn").alias("xiaoxi").issuer().CN("Yakir").OU("relaxed研发部")
				.O("relaxed有限公司").L("上海").E("relaxed@qq.com").ST("上海").C("CN").end().subject().CN("Yakir")
				.OU("relaxed研发部").O("relaxed有限公司").L("上海").E("relaxed@qq.com").ST("上海").C("CN").end();
		ByteArrayOutputStream byteArrayOutputStream = generateKeyStoreV3(keystoreMeta);
		String dir = "D:\\other\\oss\\";
		FileUtil.writeBytes(byteArrayOutputStream.toByteArray(), new File(dir, "test.p12"));
		// Pkcs pkcs = new Pkcs();
		// Map<String, byte[]> result = pkcs.createCert(keystoreMeta);
		//
		// String targetPath="D:\\mnt\\itext7\\keystore\\";
		// FileOutputStream outPutStream = new
		// FileOutputStream(targetPath+"keystore.p12"); // ca.jks
		// outPutStream.write(result.get("keyStoreData"));
		// outPutStream.close();
		// FileOutputStream fos = new FileOutputStream(new
		// File(targetPath+"keystore.cer"));
		// fos.write(result.get("certificateData"));
		// fos.flush();
		// fos.close();
		// loadCert();

	}

	private static void loadCert() throws KeyStoreException, IOException, NoSuchAlgorithmException,
			CertificateException, UnrecoverableKeyException {
		String KEYSTORE = "D:\\mnt\\keystore.p12";
		char[] PASSWORD = "123456".toCharArray();
		// 读取keystore ，获得私钥和证书链 jks
		KeyStore ks = KeyStore.getInstance("PKCS12");
		ks.load(new FileInputStream(KEYSTORE), PASSWORD);
		String alias = ks.aliases().nextElement();
		PrivateKey pk = (PrivateKey) ks.getKey(alias, PASSWORD);
		// 获取证书链
		Certificate[] chain = ks.getCertificateChain(alias);
		System.out.println(pk);
	}

}
