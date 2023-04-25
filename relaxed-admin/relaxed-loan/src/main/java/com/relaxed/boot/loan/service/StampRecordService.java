package com.relaxed.boot.loan.service;

import com.relaxed.boot.loan.model.entity.StampRecord;
import com.relaxed.boot.loan.model.vo.StampRecordPageVO;
import com.relaxed.boot.loan.model.qo.StampRecordQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

import java.util.List;

/**
 * 签章记录
 *
 * @author yakir 2023-01-29 09:31:45
 */
public interface StampRecordService extends ExtendService<StampRecord> {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult&lt;StampRecordPageVO&gt; 分页数据
	 */
	PageResult<StampRecordPageVO> queryPage(PageParam pageParam, StampRecordQO qo);

	List<StampRecord> listByStatus(Integer status);

}
