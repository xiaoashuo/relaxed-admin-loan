package com.relaxed.boot.loan.service;

import com.relaxed.boot.loan.model.entity.OrderAnnex;
import com.relaxed.boot.loan.model.vo.OrderAnnexPageVO;
import com.relaxed.boot.loan.model.qo.OrderAnnexQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;
import com.relaxed.starter.download.domain.DownloadModel;

/**
 * @author yakir 2023-01-10 10:32:19
 */
public interface OrderAnnexService extends ExtendService<OrderAnnex> {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult&lt;OrderAnnexPageVO&gt; 分页数据
	 */
	PageResult<OrderAnnexPageVO> queryPage(PageParam pageParam, OrderAnnexQO qo);

	OrderAnnex getByOrderIdAndFileType(Long orderId, Integer fileType);

	DownloadModel download(Long fileId);

}
