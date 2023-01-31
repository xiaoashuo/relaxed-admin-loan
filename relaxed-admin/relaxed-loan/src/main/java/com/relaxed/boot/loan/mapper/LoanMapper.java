package com.relaxed.boot.loan.mapper;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.loan.converter.LoanConverter;
import com.relaxed.boot.loan.model.entity.Loan;
import com.relaxed.boot.loan.model.entity.Order;
import com.relaxed.boot.loan.model.qo.LoanQO;
import com.relaxed.boot.loan.model.vo.LoanPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 借款合同表
 *
 * @author yakir 2023-01-10 10:32:19
 */
public interface LoanMapper extends ExtendMapper<Loan> {

    /**
    * 分页查询
    * @param pageParam 分页参数
    * @param qo 查询参数
    * @return PageResult<LoanPageVO> VO分页数据
    */
   default PageResult<LoanPageVO> queryPage(PageParam pageParam, LoanQO qo){
		IPage<Loan> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<Loan> wrapper = WrappersX.lambdaQueryX(Loan.class);
	   if (MapUtil.isEmpty(pageParam.getSort())){
		   wrapper.orderByDesc(Loan::getCreatedTime);
	   }
		this.selectPage(page, wrapper);
		IPage<LoanPageVO> voPage = page.convert(LoanConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
   }
}
