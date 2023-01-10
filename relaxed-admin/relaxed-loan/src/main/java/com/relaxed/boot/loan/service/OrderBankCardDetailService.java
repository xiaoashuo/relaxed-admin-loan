package com.relaxed.boot.loan.service;

import com.relaxed.boot.loan.model.entity.OrderBankCardDetail;
import com.relaxed.boot.loan.model.vo.OrderBankCardDetailPageVO;
import com.relaxed.boot.loan.model.qo.OrderBankCardDetailQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

/**
 * 订单银行卡放款详情表
 *
 * @author yakir 2023-01-10 10:32:19
 */
public interface OrderBankCardDetailService extends ExtendService<OrderBankCardDetail> {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult&lt;OrderBankCardDetailPageVO&gt; 分页数据
    */
    PageResult<OrderBankCardDetailPageVO> queryPage(PageParam pageParam, OrderBankCardDetailQO qo);

}