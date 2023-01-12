package com.relaxed.boot.loan.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.loan.converter.BillItemConverter;
import com.relaxed.boot.loan.model.entity.BillItem;
import com.relaxed.boot.loan.model.vo.BillItemPageVO;
import com.relaxed.boot.loan.model.qo.BillItemQO;
import com.relaxed.boot.loan.mapper.BillItemMapper;
import com.relaxed.boot.loan.service.BillItemService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 *
 * @author yakir 2023-01-10 10:32:31
 */
@Service
public class BillItemServiceImpl extends ExtendServiceImpl<BillItemMapper, BillItem> implements BillItemService {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult<BillItemPageVO> 分页数据
    */
    @Override
    public PageResult<BillItemPageVO> queryPage(PageParam pageParam, BillItemQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

    @Override
    public List<BillItemPageVO> queryList(BillItemQO billItemQO) {
        List<BillItem> list = list(Wrappers.lambdaQuery(BillItem.class)
                .eq(BillItem::getBillId, billItemQO.getBillId()));
       return list.stream().map(BillItemConverter.INSTANCE::poToPageVo).collect(Collectors.toList());
    }
}
