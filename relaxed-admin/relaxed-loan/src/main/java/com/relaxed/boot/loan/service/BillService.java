package com.relaxed.boot.loan.service;

import com.relaxed.boot.loan.model.entity.Bill;
import com.relaxed.boot.loan.model.vo.BillPageVO;
import com.relaxed.boot.loan.model.qo.BillQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

/**
 * 
 *
 * @author yakir 2023-01-10 10:32:31
 */
public interface BillService extends ExtendService<Bill> {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult&lt;BillPageVO&gt; 分页数据
    */
    PageResult<BillPageVO> queryPage(PageParam pageParam, BillQO qo);

    Bill getByLoanId(Long loanId);
}
