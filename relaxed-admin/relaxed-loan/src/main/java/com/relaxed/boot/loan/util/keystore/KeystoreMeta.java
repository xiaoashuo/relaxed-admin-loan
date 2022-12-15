package com.relaxed.boot.loan.util.keystore;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import lombok.Data;
import lombok.Getter;

import java.util.*;

/**
 * @author Yakir
 * @Topic KeystoreMeta
 * @Description
 *  CN: 名字与姓氏    OU : 组织单位名称
 *  O ：组织名称  L : 城市或区域名称  E : 电子邮件
 *  ST: 州或省份名称  C: 单位的两字母国家代码
 * @date 2022/12/13 20:01
 * @Version 1.0
 */
@Getter
public class KeystoreMeta {

    /**
     * 密码
     */
    private char[] password;
    /**
     * 签发者 颁发机构信息
     */
    private String issuer;
    /**
     * 主体 使用者信息
     */
    private String subject;
    /**
     * 有效期 以小时为单位
     */
    private long validity;
    /**
     * KEYSTORE 密钥库类型 默认PKCS12
     */
    private String keystoreType="PKCS12";
    /**
     * crl分发点 颁发地址
     */
    private String certificateCRL;

    /**
     * 证书别名
     */
    private String alias;
    public KeystoreMeta keystoreType(String  keystoreType){
        this.keystoreType=keystoreType;
        return this;
    }
    public KeystoreMeta validity(long validity){
        this.validity=validity;
        return this;
    }
    public KeystoreMeta alias(String alias){
        this.alias=alias;
        return this;
    }
    public KeystoreMeta certificateCRL(String certificateCRL){
        this.certificateCRL=certificateCRL;
        return this;
    }
    public KeystoreMeta issuer(String issuer){
        this.issuer=issuer;
        return this;
    }

    public KeystoreMeta subject(String subject){
        this.subject=subject;
        return this;
    }
    public KeystoreMeta password(char[] password){
        this.password=password;
        return this;
    }
    public KeystoreMeta password(String password){
        this.password=password.toCharArray();
        return this;
    }
    public Info issuer(){
        return new Info(this,"issuer");
    }
    public Info subject(){
        return new Info(this,"subject");
    }

    public static void main(String[] args) {
        KeystoreMeta keystoreMeta = new KeystoreMeta()
                .password("123456")
                .certificateCRL("https://relaxed.cn")
                .alias("xiaoxi")
                .subject().CN("Yakir")
                .OU("relaxed研发部")
                .O("relaxed有限公司")
                .L("上海")
                .E("relaxed@qq.com")
                .ST("上海")
                .C("CN").end()
                .issuer().
                 CN("Yakir")
                .OU("relaxed研发部")
                .O("relaxed有限公司")
                .L("上海")
                .ST("上海")
                .C("CN").end();
        System.out.println(keystoreMeta);
    }


    public class Info{
        private KeystoreMeta _that;
        private String field;
        /**
         * 名字与姓氏
         */
        private String CN;
        /**
         * 组织单位名称
         */
        private String OU;
        /**
         * 组织名称
         */
        private String O;
        /**
         * 城市或区域名称
         */
        private String L;
        /**
         * 电子邮件
         */
        private String E;
        /**
         * 州或省份名称
         */
        private String ST;
        /**
         * 单位的两字母国家代码
         */
        private String C;

        private LinkedHashMap<String,String> innerParam=new LinkedHashMap<>();
        public Info(KeystoreMeta that,String field){
           this. _that=that;
           this.field=field;
        }

        public Info CN(String val){
            this.CN=val;
            innerParam.put("CN",val);
            return this;
        }
        public Info OU(String val){
            this.OU=val;
            innerParam.put("OU",val);
            return this;
        }
        public Info O(String val){
            this.O=val;
            innerParam.put("O",val);
            return this;
        }
        public Info L(String val){
            this.L=val;
            innerParam.put("L",val);
            return this;
        }
        public Info E(String val){
            this.E=val;
            innerParam.put("E",val);
            return this;
        }
        public Info ST(String val){
            this.ST=val;
            innerParam.put("ST",val);
            return this;
        }
        public Info C(String val){
            this.C=val;
            innerParam.put("C",val);
            return this;
        }



        public KeystoreMeta end(){
            String fullText = MapUtil.join(innerParam, ",", "=", true);
            BeanUtil.setFieldValue(_that,field,fullText);
            return _that;
        }

    }





}
