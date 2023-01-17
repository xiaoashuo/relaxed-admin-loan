package com.relaxed.boot.loan.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.loan.model.entity.Bill;
import com.relaxed.boot.loan.model.vo.BillPageVO;
import com.relaxed.boot.loan.model.qo.BillQO;
import com.relaxed.boot.loan.mapper.BillMapper;
import com.relaxed.boot.loan.service.BillService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 
 *
 * @author yakir 2023-01-10 10:32:31
 */
@Service
public class BillServiceImpl extends ExtendServiceImpl<BillMapper, Bill> implements BillService {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult<BillPageVO> 分页数据
    */
    @Override
    public PageResult<BillPageVO> queryPage(PageParam pageParam, BillQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

    @Override
    public Bill getByLoanId(Long loanId) {
        return getOne(Wrappers.lambdaQuery(Bill.class )
                .eq(Bill::getLoanId,loanId));
    }
}
