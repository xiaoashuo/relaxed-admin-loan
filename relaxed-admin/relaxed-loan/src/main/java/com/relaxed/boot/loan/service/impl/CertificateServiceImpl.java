package com.relaxed.boot.loan.service.impl;

import cn.hutool.core.util.IdUtil;
import com.relaxed.boot.common.system.utils.file.*;
import com.relaxed.boot.framework.config.RelaxedConfig;
import com.relaxed.boot.loan.enums.CertificateEnum;
import com.relaxed.boot.loan.model.entity.Certificate;
import com.relaxed.boot.loan.model.vo.CertificatePageVO;
import com.relaxed.boot.loan.model.qo.CertificateQO;
import com.relaxed.boot.loan.mapper.CertificateMapper;
import com.relaxed.boot.loan.service.CertificateService;
import com.relaxed.boot.loan.util.keystore.KeystoreMeta;
import com.relaxed.boot.loan.util.keystore.KeystoreUtil;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

/**
 * 签章证书表
 *
 * @author yakir 2022-12-14 11:36:23
 */
@Service
public class CertificateServiceImpl extends ExtendServiceImpl<CertificateMapper, Certificate> implements CertificateService {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult<CertificatePageVO> 分页数据
    */
    @Override
    public PageResult<CertificatePageVO> queryPage(PageParam pageParam, CertificateQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

    @Override
    public boolean saveCertificate(Certificate certificate) {
        Integer authorizeType = certificate.getAuthorizeType();
        Integer keystoreType = certificate.getKeystoreType();

        if (CertificateEnum.AUTHORIZE_TYPE.SYSTEM.getVal().equals(authorizeType)){
            String certificateSubject = certificate.getCertificateSubject();
            String certificateAlias = certificate.getCertificateAlias();
            CertificateEnum.KEYSTORE_TYPE keystoreTypeEnum = CertificateEnum.KEYSTORE_TYPE.getByVal(keystoreType);
            //系统生成
            KeystoreMeta keystoreMeta = new KeystoreMeta()
                    .password("123456")
                    .validity(3650)
                    .certificateCRL("https://relaxed.cn")
                    .keystoreType(keystoreTypeEnum.getDesc())
                    .alias(certificateAlias)
                    .subject().CN(certificateAlias)
                    .OU(certificateSubject)
                    .O(certificateSubject)
                    .C("CN").end()
                    .issuer()
                    .CN("Yakir")
                    .OU("relaxed研发部")
                    .O("relaxed有限公司")
                    .L("上海")
                    .E("relaxed@qq.com")
                    .ST("上海")
                    .C("CN").end();
            String filename = IdUtil.simpleUUID() + keystoreTypeEnum.getSuffix();
            ByteArrayOutputStream byteArrayOutputStream = KeystoreUtil.generateKeyStoreV3(keystoreMeta);
            ByteArrayMultipartFile file = new ByteArrayMultipartFile(byteArrayOutputStream.toByteArray(), filename);
            FileMeta fileMeta = FileUtils.upload(RelaxedConfig.getProfile(),"keystore", file,
                    FileConfig.create().splitDate(false));
            String address = RelaxedConfig.getUrl() + fileMeta.getRelativeFilePath();
            certificate.setCertificateAddress(address);
        }
        boolean result = save(certificate);
        return result;
    }
}
