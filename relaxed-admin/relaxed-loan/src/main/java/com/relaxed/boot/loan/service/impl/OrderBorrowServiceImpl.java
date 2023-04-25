package com.relaxed.boot.loan.service.impl;

import cn.hutool.core.util.StrUtil;
import com.relaxed.boot.loan.model.entity.OrderBorrow;
import com.relaxed.boot.loan.model.vo.OrderBorrowPageVO;
import com.relaxed.boot.loan.model.qo.OrderBorrowQO;
import com.relaxed.boot.loan.mapper.OrderBorrowMapper;
import com.relaxed.boot.loan.service.OrderBorrowService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 共同借款人
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Service
public class OrderBorrowServiceImpl extends ExtendServiceImpl<OrderBorrowMapper, OrderBorrow>
		implements OrderBorrowService {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult<OrderBorrowPageVO> 分页数据
	 */
	@Override
	public PageResult<OrderBorrowPageVO> queryPage(PageParam pageParam, OrderBorrowQO qo) {
		return baseMapper.queryPage(pageParam, qo);
	}

}
