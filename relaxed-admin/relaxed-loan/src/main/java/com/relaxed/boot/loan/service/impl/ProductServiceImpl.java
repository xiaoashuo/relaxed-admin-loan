package com.relaxed.boot.loan.service.impl;

import com.relaxed.boot.loan.model.entity.Product;
import com.relaxed.boot.loan.model.vo.ProductPageVO;
import com.relaxed.boot.loan.model.qo.ProductQO;
import com.relaxed.boot.loan.mapper.ProductMapper;
import com.relaxed.boot.loan.service.ProductService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 信托产品表
 *
 * @author yakir 2022-12-14 11:36:23
 */
@Service
public class ProductServiceImpl extends ExtendServiceImpl<ProductMapper, Product> implements ProductService {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult<ProductPageVO> 分页数据
    */
    @Override
    public PageResult<ProductPageVO> queryPage(PageParam pageParam, ProductQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

}
