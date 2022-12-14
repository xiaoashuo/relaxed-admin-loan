package com.relaxed.boot.loan.service.impl;

import com.relaxed.boot.loan.model.entity.Certificate;
import com.relaxed.boot.loan.model.vo.CertificatePageVO;
import com.relaxed.boot.loan.model.qo.CertificateQO;
import com.relaxed.boot.loan.mapper.CertificateMapper;
import com.relaxed.boot.loan.service.CertificateService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

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

}
