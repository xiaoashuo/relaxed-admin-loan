package com.relaxed.boot.loan.service.impl;

import com.relaxed.boot.loan.model.entity.Audit;
import com.relaxed.boot.loan.model.vo.AuditPageVO;
import com.relaxed.boot.loan.model.qo.AuditQO;
import com.relaxed.boot.loan.mapper.AuditMapper;
import com.relaxed.boot.loan.service.AuditService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 审核表
 *
 * @author yakir 2023-01-10 10:32:31
 */
@Service
public class AuditServiceImpl extends ExtendServiceImpl<AuditMapper, Audit> implements AuditService {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult<AuditPageVO> 分页数据
    */
    @Override
    public PageResult<AuditPageVO> queryPage(PageParam pageParam, AuditQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

}
