package com.relaxed.boot.loan.service;

import com.relaxed.boot.loan.model.entity.Certificate;
import com.relaxed.boot.loan.model.vo.CertificatePageVO;
import com.relaxed.boot.loan.model.qo.CertificateQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.domain.SelectData;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

import java.util.List;

/**
 * 签章证书表
 *
 * @author yakir 2022-12-14 11:36:23
 */
public interface CertificateService extends ExtendService<Certificate> {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult&lt;CertificatePageVO&gt; 分页数据
	 */
	PageResult<CertificatePageVO> queryPage(PageParam pageParam, CertificateQO qo);

	/**
	 * 存储证书
	 * @param certificate
	 * @return
	 */
	boolean saveCertificate(Certificate certificate);

	List<SelectData> queryCertificateList();

	boolean removeCertificateById(Integer certificateId);

}
