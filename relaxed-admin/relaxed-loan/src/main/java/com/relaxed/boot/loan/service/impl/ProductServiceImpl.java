package com.relaxed.boot.loan.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.loan.model.entity.Product;
import com.relaxed.boot.loan.model.vo.ProductPageVO;
import com.relaxed.boot.loan.model.qo.ProductQO;
import com.relaxed.boot.loan.mapper.ProductMapper;
import com.relaxed.boot.loan.service.ProductService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.domain.SelectData;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 信托产品表
 *
 * @author yakir 2022-12-14 11:36:23
 */
@Service
public class ProductServiceImpl extends ExtendServiceImpl<ProductMapper, Product> implements ProductService {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult<ProductPageVO> 分页数据
	 */
	@Override
	public PageResult<ProductPageVO> queryPage(PageParam pageParam, ProductQO qo) {
		return baseMapper.queryPage(pageParam, qo);
	}

	@Override
	public List<SelectData> querySelectData() {
		List<Product> productList = list();
		List<SelectData> productSelectData = productList.stream().map(e -> {
			SelectData<Void> selectData = new SelectData<>();
			selectData.setLabel(e.getProductName());
			selectData.setValue(e.getProductId());
			return selectData;
		}).collect(Collectors.toList());
		return productSelectData;
	}

	@Override
	public Product getByProductCode(Integer productCode) {
		return getOne(Wrappers.lambdaQuery(Product.class).eq(Product::getProductCode, productCode));
	}

}
