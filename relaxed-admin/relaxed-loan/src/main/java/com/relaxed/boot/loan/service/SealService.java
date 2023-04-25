package com.relaxed.boot.loan.service;

import com.relaxed.boot.loan.model.entity.Seal;
import com.relaxed.boot.loan.model.vo.SealPageVO;
import com.relaxed.boot.loan.model.qo.SealQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.domain.SelectData;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

import java.util.List;

/**
 * 签章图片表
 *
 * @author yakir 2022-12-15 10:25:25
 */
public interface SealService extends ExtendService<Seal> {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult&lt;SealPageVO&gt; 分页数据
	 */
	PageResult<SealPageVO> queryPage(PageParam pageParam, SealQO qo);

	boolean saveSeal(Seal seal);

	List<SelectData> querySealList();

	List<SealPageVO> queryList();

	boolean removeSealById(Integer sealId);

}
