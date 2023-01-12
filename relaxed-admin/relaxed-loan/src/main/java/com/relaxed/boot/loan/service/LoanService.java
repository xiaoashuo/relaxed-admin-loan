package com.relaxed.boot.loan.service;

import com.relaxed.boot.loan.model.entity.Loan;
import com.relaxed.boot.loan.model.vo.LoanDetailVO;
import com.relaxed.boot.loan.model.vo.LoanPageVO;
import com.relaxed.boot.loan.model.qo.LoanQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

/**
 * 借款合同表
 *
 * @author yakir 2023-01-10 10:32:19
 */
public interface LoanService extends ExtendService<Loan> {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult&lt;LoanPageVO&gt; 分页数据
    */
    PageResult<LoanPageVO> queryPage(PageParam pageParam, LoanQO qo);

    LoanDetailVO getLoanDetail(Long loanId);
}
