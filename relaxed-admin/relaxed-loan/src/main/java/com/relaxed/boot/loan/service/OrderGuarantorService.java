package com.relaxed.boot.loan.service;

import com.relaxed.boot.loan.model.entity.OrderGuarantor;
import com.relaxed.boot.loan.model.vo.OrderGuarantorPageVO;
import com.relaxed.boot.loan.model.qo.OrderGuarantorQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

/**
 * 担保机构
 *
 * @author yakir 2023-01-10 10:32:19
 */
public interface OrderGuarantorService extends ExtendService<OrderGuarantor> {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult&lt;OrderGuarantorPageVO&gt; 分页数据
    */
    PageResult<OrderGuarantorPageVO> queryPage(PageParam pageParam, OrderGuarantorQO qo);

    OrderGuarantor getByOrderId(Long orderId);

}
