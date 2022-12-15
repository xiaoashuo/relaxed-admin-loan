package com.relaxed.boot.loan.enums;

import com.relaxed.common.core.exception.BusinessException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Yakir
 * @Topic CertificateEnum
 * @Description
 * @date 2022/12/15 12:55
 * @Version 1.0
 */
@Getter
public class CertificateEnum {

    /**
     * 授权类型
     */
    @RequiredArgsConstructor
    @Getter
    public enum AUTHORIZE_TYPE{
        SYSTEM(1,"系统生成"),
        CUSTOM(2,"自定义"),

        ;
        private final Integer val;
        private final String desc;
    }

    /**
     * 密钥库类型 keystore
     */
    @RequiredArgsConstructor
    @Getter
    public enum KEYSTORE_TYPE{
        JKS(1,"JKS","jks"),

        PKCS12(2,"PKCS12","p12"),


        ;
        private final Integer val;
        private final String desc;
        private final String suffix;
        public static KEYSTORE_TYPE getByVal(Integer val){
            for (KEYSTORE_TYPE value : values()) {
                if (value.getVal().equals(val)) {
                    return value;
                }
            }
            throw new BusinessException(400,"算法不支持");
        }
    }

}
