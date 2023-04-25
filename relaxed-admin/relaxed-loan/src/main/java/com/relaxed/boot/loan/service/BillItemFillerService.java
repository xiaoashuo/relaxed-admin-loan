package com.relaxed.boot.loan.service;

import com.relaxed.boot.loan.model.entity.BillItemFiller;
import com.relaxed.boot.loan.model.qo.BillItemQO;
import com.relaxed.boot.loan.model.vo.BillItemFillerPageVO;
import com.relaxed.boot.loan.model.qo.BillItemFillerQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

import java.util.List;

/**
 * @author yakir 2023-01-10 10:32:19
 */
public interface BillItemFillerService extends ExtendService<BillItemFiller> {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult&lt;BillItemFillerPageVO&gt; 分页数据
	 */
	PageResult<BillItemFillerPageVO> queryPage(PageParam pageParam, BillItemFillerQO qo);

	List<BillItemFillerPageVO> queryList(BillItemFillerQO billItemQO);

}
