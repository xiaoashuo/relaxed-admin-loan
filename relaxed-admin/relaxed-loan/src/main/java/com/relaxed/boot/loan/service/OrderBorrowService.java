package com.relaxed.boot.loan.service;

import com.relaxed.boot.loan.model.entity.OrderBorrow;
import com.relaxed.boot.loan.model.vo.OrderBorrowPageVO;
import com.relaxed.boot.loan.model.qo.OrderBorrowQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

/**
 * 共同借款人
 *
 * @author yakir 2023-01-10 10:32:19
 */
public interface OrderBorrowService extends ExtendService<OrderBorrow> {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult&lt;OrderBorrowPageVO&gt; 分页数据
    */
    PageResult<OrderBorrowPageVO> queryPage(PageParam pageParam, OrderBorrowQO qo);

}