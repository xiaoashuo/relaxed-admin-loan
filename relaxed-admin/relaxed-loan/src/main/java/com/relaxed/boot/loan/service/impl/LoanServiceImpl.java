package com.relaxed.boot.loan.service.impl;

import com.relaxed.boot.loan.model.entity.Loan;
import com.relaxed.boot.loan.model.vo.LoanPageVO;
import com.relaxed.boot.loan.model.qo.LoanQO;
import com.relaxed.boot.loan.mapper.LoanMapper;
import com.relaxed.boot.loan.service.LoanService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 借款合同表
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Service
public class LoanServiceImpl extends ExtendServiceImpl<LoanMapper, Loan> implements LoanService {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult<LoanPageVO> 分页数据
    */
    @Override
    public PageResult<LoanPageVO> queryPage(PageParam pageParam, LoanQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

}
