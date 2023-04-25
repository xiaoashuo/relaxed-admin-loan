package com.relaxed.boot.loan.service;

import com.relaxed.boot.loan.model.entity.Audit;
import com.relaxed.boot.loan.model.vo.AuditPageVO;
import com.relaxed.boot.loan.model.qo.AuditQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

/**
 * 审核表
 *
 * @author yakir 2023-01-10 10:32:31
 */
public interface AuditService extends ExtendService<Audit> {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult&lt;AuditPageVO&gt; 分页数据
	 */
	PageResult<AuditPageVO> queryPage(PageParam pageParam, AuditQO qo);

	Audit getByOrderId(Long orderId);

}
