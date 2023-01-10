package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.loan.converter.BankCodeConverter;
import com.relaxed.boot.loan.model.entity.BankCode;
import com.relaxed.boot.loan.model.qo.BankCodeQO;
import com.relaxed.boot.loan.model.vo.BankCodePageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 银行编码表
 *
 * @author yakir 2023-01-10 10:13:02
 */
public interface BankCodeMapper extends ExtendMapper<BankCode> {

    /**
    * 分页查询
    * @param pageParam 分页参数
    * @param qo 查询参数
    * @return PageResult<BankCodePageVO> VO分页数据
    */
   default PageResult<BankCodePageVO> queryPage(PageParam pageParam, BankCodeQO qo){
		IPage<BankCode> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<BankCode> wrapper = WrappersX.lambdaQueryX(BankCode.class);
		wrapper.eqIfPresent(BankCode::getBankCode,qo.getBankCode());
		wrapper.likeIfPresent(BankCode::getBankName,qo.getBankName());
		this.selectPage(page, wrapper);
		IPage<BankCodePageVO> voPage = page.convert(BankCodeConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
   }
}
