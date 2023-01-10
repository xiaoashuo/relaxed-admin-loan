package com.relaxed.boot.loan.service;

import com.relaxed.boot.loan.model.entity.BillItem;
import com.relaxed.boot.loan.model.vo.BillItemPageVO;
import com.relaxed.boot.loan.model.qo.BillItemQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

/**
 * 
 *
 * @author yakir 2023-01-10 10:32:31
 */
public interface BillItemService extends ExtendService<BillItem> {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult&lt;BillItemPageVO&gt; 分页数据
    */
    PageResult<BillItemPageVO> queryPage(PageParam pageParam, BillItemQO qo);

}