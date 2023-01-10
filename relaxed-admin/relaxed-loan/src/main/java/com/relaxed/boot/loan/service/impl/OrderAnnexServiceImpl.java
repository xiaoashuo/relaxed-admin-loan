package com.relaxed.boot.loan.service.impl;

import com.relaxed.boot.loan.model.entity.OrderAnnex;
import com.relaxed.boot.loan.model.vo.OrderAnnexPageVO;
import com.relaxed.boot.loan.model.qo.OrderAnnexQO;
import com.relaxed.boot.loan.mapper.OrderAnnexMapper;
import com.relaxed.boot.loan.service.OrderAnnexService;
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
public class OrderAnnexServiceImpl extends ExtendServiceImpl<OrderAnnexMapper, OrderAnnex> implements OrderAnnexService {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult<OrderAnnexPageVO> 分页数据
    */
    @Override
    public PageResult<OrderAnnexPageVO> queryPage(PageParam pageParam, OrderAnnexQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

}
