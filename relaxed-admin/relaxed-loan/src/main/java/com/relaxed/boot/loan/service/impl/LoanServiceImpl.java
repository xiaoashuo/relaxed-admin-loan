package com.relaxed.boot.loan.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.loan.model.entity.Loan;
import com.relaxed.boot.loan.model.entity.OrderCustomer;
import com.relaxed.boot.loan.model.vo.LoanDetailVO;
import com.relaxed.boot.loan.model.vo.LoanPageVO;
import com.relaxed.boot.loan.model.qo.LoanQO;
import com.relaxed.boot.loan.mapper.LoanMapper;
import com.relaxed.boot.loan.service.LoanService;
import com.relaxed.boot.loan.service.OrderCustomerService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 借款合同表
 *
 * @author yakir 2023-01-10 10:32:19
 */
@RequiredArgsConstructor
@Service
public class LoanServiceImpl extends ExtendServiceImpl<LoanMapper, Loan> implements LoanService {
    private final OrderCustomerService orderCustomerService;


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

    @Override
    public LoanDetailVO getLoanDetail(Long loanId) {
        Loan loan = getById(loanId);
        Assert.notNull(loan, "借据信息不存在");
        Long orderId = loan.getOrderId();
        OrderCustomer orderCustomer = orderCustomerService.getByOrderId(orderId);
        LoanDetailVO loanDetailVO = new LoanDetailVO();
        loanDetailVO.setLoan(loan);
        loanDetailVO.setOrderCustomer(orderCustomer);
        return loanDetailVO;
    }

    @Override
    public List<Loan> listByStatus(Integer status) {
        return list(Wrappers.<Loan>lambdaQuery().eq(Loan::getLoanStatus, status));
    }
}
