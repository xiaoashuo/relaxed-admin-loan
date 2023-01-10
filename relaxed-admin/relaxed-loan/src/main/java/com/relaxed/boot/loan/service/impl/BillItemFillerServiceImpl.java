package com.relaxed.boot.loan.service.impl;

import com.relaxed.boot.loan.model.entity.BillItemFiller;
import com.relaxed.boot.loan.model.vo.BillItemFillerPageVO;
import com.relaxed.boot.loan.model.qo.BillItemFillerQO;
import com.relaxed.boot.loan.mapper.BillItemFillerMapper;
import com.relaxed.boot.loan.service.BillItemFillerService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Service
public class BillItemFillerServiceImpl extends ExtendServiceImpl<BillItemFillerMapper, BillItemFiller> implements BillItemFillerService {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult<BillItemFillerPageVO> 分页数据
    */
    @Override
    public PageResult<BillItemFillerPageVO> queryPage(PageParam pageParam, BillItemFillerQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

}
