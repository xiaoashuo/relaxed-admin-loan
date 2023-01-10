package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.loan.converter.BillConverter;
import com.relaxed.boot.loan.model.entity.Bill;
import com.relaxed.boot.loan.model.qo.BillQO;
import com.relaxed.boot.loan.model.vo.BillPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 
 *
 * @author yakir 2023-01-10 10:32:31
 */
public interface BillMapper extends ExtendMapper<Bill> {

    /**
    * 分页查询
    * @param pageParam 分页参数
    * @param qo 查询参数
    * @return PageResult<BillPageVO> VO分页数据
    */
   default PageResult<BillPageVO> queryPage(PageParam pageParam, BillQO qo){
		IPage<Bill> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<Bill> wrapper = WrappersX.lambdaQueryX(Bill.class);
		this.selectPage(page, wrapper);
		IPage<BillPageVO> voPage = page.convert(BillConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
   }
}