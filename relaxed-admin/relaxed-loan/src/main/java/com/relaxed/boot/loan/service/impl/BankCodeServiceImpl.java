package com.relaxed.boot.loan.service.impl;

import com.relaxed.boot.loan.model.entity.BankCode;
import com.relaxed.boot.loan.model.vo.BankCodePageVO;
import com.relaxed.boot.loan.model.qo.BankCodeQO;
import com.relaxed.boot.loan.mapper.BankCodeMapper;
import com.relaxed.boot.loan.service.BankCodeService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 银行编码表
 *
 * @author yakir 2023-01-10 10:13:02
 */
@Service
public class BankCodeServiceImpl extends ExtendServiceImpl<BankCodeMapper, BankCode> implements BankCodeService {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult<BankCodePageVO> 分页数据
    */
    @Override
    public PageResult<BankCodePageVO> queryPage(PageParam pageParam, BankCodeQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

}
