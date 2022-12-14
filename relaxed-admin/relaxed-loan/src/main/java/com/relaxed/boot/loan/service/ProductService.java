package com.relaxed.boot.loan.service;

import com.relaxed.boot.loan.model.entity.Product;
import com.relaxed.boot.loan.model.vo.ProductPageVO;
import com.relaxed.boot.loan.model.qo.ProductQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

/**
 * 信托产品表
 *
 * @author yakir 2022-12-14 11:36:23
 */
public interface ProductService extends ExtendService<Product> {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult&lt;ProductPageVO&gt; 分页数据
    */
    PageResult<ProductPageVO> queryPage(PageParam pageParam, ProductQO qo);

}