package com.relaxed.boot.loan.service.impl;

import com.relaxed.boot.loan.model.entity.Seal;
import com.relaxed.boot.loan.model.vo.SealPageVO;
import com.relaxed.boot.loan.model.qo.SealQO;
import com.relaxed.boot.loan.mapper.SealMapper;
import com.relaxed.boot.loan.service.SealService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 签章图片表
 *
 * @author yakir 2022-12-15 10:25:25
 */
@Service
public class SealServiceImpl extends ExtendServiceImpl<SealMapper, Seal> implements SealService {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult<SealPageVO> 分页数据
    */
    @Override
    public PageResult<SealPageVO> queryPage(PageParam pageParam, SealQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

}
