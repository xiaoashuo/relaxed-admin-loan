package com.relaxed.boot.loan.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.loan.model.entity.BankCode;
import com.relaxed.boot.loan.model.vo.BankCodePageVO;
import com.relaxed.boot.loan.model.qo.BankCodeQO;
import com.relaxed.boot.loan.mapper.BankCodeMapper;
import com.relaxed.boot.loan.service.BankCodeService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.domain.SelectData;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public List<SelectData> querySelectData() {
        return list().stream().map(item -> {
            SelectData<Void> selectData = new SelectData<>();
            selectData.setLabel(item.getBankName());
            selectData.setValue(item.getBankCode());
            return selectData;
        }).collect(Collectors.toList());
    }

    @Override
    public BankCode getByBankCode(String bankCode) {
        return getOne(Wrappers.lambdaQuery(BankCode.class )
                .eq(BankCode::getBankCode,bankCode));
    }
}
