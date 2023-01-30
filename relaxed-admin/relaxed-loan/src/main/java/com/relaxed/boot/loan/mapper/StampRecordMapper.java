package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.loan.converter.StampRecordConverter;
import com.relaxed.boot.loan.model.entity.StampRecord;
import com.relaxed.boot.loan.model.qo.StampRecordQO;
import com.relaxed.boot.loan.model.vo.StampRecordPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 签章记录
 *
 * @author yakir 2023-01-29 09:31:45
 */
public interface StampRecordMapper extends ExtendMapper<StampRecord> {

    /**
    * 分页查询
    * @param pageParam 分页参数
    * @param qo 查询参数
    * @return PageResult<StampRecordPageVO> VO分页数据
    */
   default PageResult<StampRecordPageVO> queryPage(PageParam pageParam, StampRecordQO qo){
		IPage<StampRecord> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<StampRecord> wrapper = WrappersX.lambdaQueryX(StampRecord.class);
		this.selectPage(page, wrapper);
		IPage<StampRecordPageVO> voPage = page.convert(StampRecordConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
   }
}