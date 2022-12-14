package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.loan.converter.ProductConverter;
import com.relaxed.boot.loan.model.entity.Product;
import com.relaxed.boot.loan.model.qo.ProductQO;
import com.relaxed.boot.loan.model.vo.ProductPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 信托产品表
 *
 * @author yakir 2022-12-14 11:36:23
 */
public interface ProductMapper extends ExtendMapper<Product> {

    /**
    * 分页查询
    * @param pageParam 分页参数
    * @param qo 查询参数
    * @return PageResult<ProductPageVO> VO分页数据
    */
   default PageResult<ProductPageVO> queryPage(PageParam pageParam, ProductQO qo){
		IPage<Product> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<Product> wrapper = WrappersX.lambdaQueryX(Product.class);
		this.selectPage(page, wrapper);
		IPage<ProductPageVO> voPage = page.convert(ProductConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
   }
}