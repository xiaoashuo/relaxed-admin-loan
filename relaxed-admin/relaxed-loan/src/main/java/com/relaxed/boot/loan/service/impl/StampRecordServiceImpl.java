package com.relaxed.boot.loan.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.loan.model.entity.StampRecord;
import com.relaxed.boot.loan.model.vo.StampRecordPageVO;
import com.relaxed.boot.loan.model.qo.StampRecordQO;
import com.relaxed.boot.loan.mapper.StampRecordMapper;
import com.relaxed.boot.loan.service.StampRecordService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 签章记录
 *
 * @author yakir 2023-01-29 09:31:45
 */
@Service
public class StampRecordServiceImpl extends ExtendServiceImpl<StampRecordMapper, StampRecord>
		implements StampRecordService {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult<StampRecordPageVO> 分页数据
	 */
	@Override
	public PageResult<StampRecordPageVO> queryPage(PageParam pageParam, StampRecordQO qo) {
		return baseMapper.queryPage(pageParam, qo);
	}

	@Override
	public List<StampRecord> listByStatus(Integer status) {
		return list(Wrappers.lambdaQuery(StampRecord.class).eq(StampRecord::getStatus, status));
	}

}
