package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.TrustPlan;
import com.relaxed.boot.loan.model.vo.TrustPlanPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 信托计划配置表模型转换器
 *
 * @author yakir 2022-12-14 11:36:23
 */
@Mapper
public interface TrustPlanConverter {

	TrustPlanConverter INSTANCE = Mappers.getMapper(TrustPlanConverter.class);

	/**
	 * PO 转 PageVO
	 * @param trustPlan 信托计划配置表
	 * @return TrustPlanPageVO 信托计划配置表PageVO
	 */
	TrustPlanPageVO poToPageVo(TrustPlan trustPlan);

}
