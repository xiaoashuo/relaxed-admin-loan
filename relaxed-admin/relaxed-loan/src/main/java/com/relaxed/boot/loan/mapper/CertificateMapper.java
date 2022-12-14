package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.loan.converter.CertificateConverter;
import com.relaxed.boot.loan.model.entity.Certificate;
import com.relaxed.boot.loan.model.qo.CertificateQO;
import com.relaxed.boot.loan.model.vo.CertificatePageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 签章证书表
 *
 * @author yakir 2022-12-14 11:36:23
 */
public interface CertificateMapper extends ExtendMapper<Certificate> {

    /**
    * 分页查询
    * @param pageParam 分页参数
    * @param qo 查询参数
    * @return PageResult<CertificatePageVO> VO分页数据
    */
   default PageResult<CertificatePageVO> queryPage(PageParam pageParam, CertificateQO qo){
		IPage<Certificate> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<Certificate> wrapper = WrappersX.lambdaQueryX(Certificate.class);
		this.selectPage(page, wrapper);
		IPage<CertificatePageVO> voPage = page.convert(CertificateConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
   }
}