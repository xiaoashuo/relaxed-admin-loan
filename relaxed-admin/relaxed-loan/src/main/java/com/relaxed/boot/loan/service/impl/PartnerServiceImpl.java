package com.relaxed.boot.loan.service.impl;

import com.relaxed.boot.loan.model.entity.Partner;
import com.relaxed.boot.loan.model.vo.PartnerPageVO;
import com.relaxed.boot.loan.model.qo.PartnerQO;
import com.relaxed.boot.loan.mapper.PartnerMapper;
import com.relaxed.boot.loan.service.PartnerService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 合作方
 *
 * @author yakir 2022-12-14 11:36:23
 */
@Service
public class PartnerServiceImpl extends ExtendServiceImpl<PartnerMapper, Partner> implements PartnerService {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult<PartnerPageVO> 分页数据
    */
    @Override
    public PageResult<PartnerPageVO> queryPage(PageParam pageParam, PartnerQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

}
