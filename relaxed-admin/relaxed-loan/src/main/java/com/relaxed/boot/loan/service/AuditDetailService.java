package com.relaxed.boot.loan.service;

import com.relaxed.boot.loan.model.entity.AuditDetail;
import com.relaxed.boot.loan.model.vo.AuditDetailPageVO;
import com.relaxed.boot.loan.model.qo.AuditDetailQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

/**
 * 审核拒绝原因表
 *
 * @author yakir 2023-01-10 10:32:31
 */
public interface AuditDetailService extends ExtendService<AuditDetail> {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult&lt;AuditDetailPageVO&gt; 分页数据
    */
    PageResult<AuditDetailPageVO> queryPage(PageParam pageParam, AuditDetailQO qo);

}