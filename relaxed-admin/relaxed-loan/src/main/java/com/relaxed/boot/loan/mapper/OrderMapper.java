package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.relaxed.boot.loan.converter.OrderConverter;
import com.relaxed.boot.loan.model.entity.Order;
import com.relaxed.boot.loan.model.entity.ProjectTemplate;
import com.relaxed.boot.loan.model.qo.OrderQO;
import com.relaxed.boot.loan.model.vo.OrderPageVO;
import com.relaxed.boot.loan.model.vo.ProjectTemplatePageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaAliasQueryWrapperX;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;
import org.apache.ibatis.annotations.Param;

/**
 * 
 *
 * @author yakir 2023-01-10 10:32:31
 */
public interface OrderMapper extends ExtendMapper<Order> {

    /**
    * 分页查询
    * @param pageParam 分页参数
    * @param qo 查询参数
    * @return PageResult<OrderPageVO> VO分页数据
    */
   default PageResult<OrderPageVO> queryPage(PageParam pageParam, OrderQO qo){
		IPage<OrderPageVO> page = this.prodPage(pageParam);
		LambdaAliasQueryWrapperX<Order> wrapper = WrappersX.lambdaAliasQueryX(Order.class);
		this.selectByPage(page, wrapper);
		return new PageResult<>(page.getRecords(), page.getTotal());
   }
	IPage<OrderPageVO> selectByPage(IPage<OrderPageVO> page,
											  @Param(Constants.WRAPPER) Wrapper<Order> queryWrapper);
}
