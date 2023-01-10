package com.relaxed.boot.loan.service;

import com.relaxed.boot.loan.model.entity.BankCode;
import com.relaxed.boot.loan.model.vo.BankCodePageVO;
import com.relaxed.boot.loan.model.qo.BankCodeQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

/**
 * 银行编码表
 *
 * @author yakir 2023-01-10 10:13:02
 */
public interface BankCodeService extends ExtendService<BankCode> {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult&lt;BankCodePageVO&gt; 分页数据
    */
    PageResult<BankCodePageVO> queryPage(PageParam pageParam, BankCodeQO qo);

}