package com.relaxed.boot.loan.service.impl;

import com.relaxed.boot.loan.model.entity.Template;
import com.relaxed.boot.loan.model.vo.TemplatePageVO;
import com.relaxed.boot.loan.model.qo.TemplateQO;
import com.relaxed.boot.loan.mapper.TemplateMapper;
import com.relaxed.boot.loan.service.TemplateService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 模板文件配置表
 *
 * @author yakir 2022-12-14 11:36:23
 */
@Service
public class TemplateServiceImpl extends ExtendServiceImpl<TemplateMapper, Template> implements TemplateService {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult<TemplatePageVO> 分页数据
    */
    @Override
    public PageResult<TemplatePageVO> queryPage(PageParam pageParam, TemplateQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

}
