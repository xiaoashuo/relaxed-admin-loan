package com.relaxed.boot.loan.util.keystore.pkcs12;

/**
 * @author Yakir
 * @Topic KeystoreException
 * @Description
 * @date 2022/12/13 19:37
 * @Version 1.0
 */
public class KeystoreExtension {

	private String oid;

	private boolean critical;

	private byte[] value;

	public String getOid() {
		return oid;
	}

	public byte[] getValue() {
		return value;
	}

	public boolean isCritical() {
		return critical;
	}

}
