package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.loan.converter.BillItemConverter;
import com.relaxed.boot.loan.model.entity.BillItem;
import com.relaxed.boot.loan.model.qo.BillItemQO;
import com.relaxed.boot.loan.model.vo.BillItemPageVO;
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
public interface BillItemMapper extends ExtendMapper<BillItem> {

    /**
    * 分页查询
    * @param pageParam 分页参数
    * @param qo 查询参数
    * @return PageResult<BillItemPageVO> VO分页数据
    */
   default PageResult<BillItemPageVO> queryPage(PageParam pageParam, BillItemQO qo){
		IPage<BillItem> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<BillItem> wrapper = WrappersX.lambdaQueryX(BillItem.class);
		this.selectPage(page, wrapper);
		IPage<BillItemPageVO> voPage = page.convert(BillItemConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
   }
}