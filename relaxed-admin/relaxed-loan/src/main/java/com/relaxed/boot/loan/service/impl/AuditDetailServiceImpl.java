package com.relaxed.boot.loan.service.impl;

import com.relaxed.boot.loan.model.entity.AuditDetail;
import com.relaxed.boot.loan.model.vo.AuditDetailPageVO;
import com.relaxed.boot.loan.model.qo.AuditDetailQO;
import com.relaxed.boot.loan.mapper.AuditDetailMapper;
import com.relaxed.boot.loan.service.AuditDetailService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 审核拒绝原因表
 *
 * @author yakir 2023-01-10 10:32:31
 */
@Service
public class AuditDetailServiceImpl extends ExtendServiceImpl<AuditDetailMapper, AuditDetail> implements AuditDetailService {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult<AuditDetailPageVO> 分页数据
    */
    @Override
    public PageResult<AuditDetailPageVO> queryPage(PageParam pageParam, AuditDetailQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

}
