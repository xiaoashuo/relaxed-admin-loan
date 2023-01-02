package com.relaxed.boot.loan.service;

import com.relaxed.boot.loan.model.entity.Partner;
import com.relaxed.boot.loan.model.vo.PartnerPageVO;
import com.relaxed.boot.loan.model.qo.PartnerQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.domain.SelectData;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

import java.util.List;

/**
 * 合作方
 *
 * @author yakir 2022-12-14 11:36:23
 */
public interface PartnerService extends ExtendService<Partner> {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult&lt;PartnerPageVO&gt; 分页数据
	 */
	PageResult<PartnerPageVO> queryPage(PageParam pageParam, PartnerQO qo);

	List<SelectData> queryPartnerList();

}
