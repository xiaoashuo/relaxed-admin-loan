package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.Product;
import com.relaxed.boot.loan.model.vo.ProductPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 信托产品表模型转换器
 *
 * @author yakir 2022-12-14 11:36:23
 */
@Mapper
public interface ProductConverter {

	ProductConverter INSTANCE = Mappers.getMapper(ProductConverter.class);

	/**
	 * PO 转 PageVO
	 * @param product 信托产品表
	 * @return ProductPageVO 信托产品表PageVO
	 */
	ProductPageVO poToPageVo(Product product);

}
