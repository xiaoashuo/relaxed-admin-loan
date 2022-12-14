package com.relaxed.boot.loan.service.impl;

import com.relaxed.boot.loan.model.entity.TrustPlan;
import com.relaxed.boot.loan.model.vo.TrustPlanPageVO;
import com.relaxed.boot.loan.model.qo.TrustPlanQO;
import com.relaxed.boot.loan.mapper.TrustPlanMapper;
import com.relaxed.boot.loan.service.TrustPlanService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 信托计划配置表
 *
 * @author yakir 2022-12-14 11:36:23
 */
@Service
public class TrustPlanServiceImpl extends ExtendServiceImpl<TrustPlanMapper, TrustPlan> implements TrustPlanService {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult<TrustPlanPageVO> 分页数据
    */
    @Override
    public PageResult<TrustPlanPageVO> queryPage(PageParam pageParam, TrustPlanQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

}
