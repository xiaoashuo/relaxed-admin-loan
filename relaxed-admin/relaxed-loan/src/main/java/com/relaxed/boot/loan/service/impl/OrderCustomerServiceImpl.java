package com.relaxed.boot.loan.service.impl;

import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.loan.converter.OrderCustomerConverter;
import com.relaxed.boot.loan.model.entity.OrderCustomer;
import com.relaxed.boot.loan.model.entity.Province;
import com.relaxed.boot.loan.model.vo.OrderCustomerPageVO;
import com.relaxed.boot.loan.model.qo.OrderCustomerQO;
import com.relaxed.boot.loan.mapper.OrderCustomerMapper;
import com.relaxed.boot.loan.service.OrderCustomerService;
import com.relaxed.boot.loan.service.ProvinceService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author yakir 2023-01-10 10:32:19
 */
@RequiredArgsConstructor
@Service
public class OrderCustomerServiceImpl extends ExtendServiceImpl<OrderCustomerMapper, OrderCustomer>
		implements OrderCustomerService {

	private final ProvinceService provinceService;

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult<OrderCustomerPageVO> 分页数据
	 */
	@Override
	public PageResult<OrderCustomerPageVO> queryPage(PageParam pageParam, OrderCustomerQO qo) {
		return baseMapper.queryPage(pageParam, qo);
	}

	@Override
	public OrderCustomer getByOrderId(Long orderId) {
		return getOne(Wrappers.<OrderCustomer>lambdaQuery().eq(OrderCustomer::getOrderId, orderId));
	}

	@Override
	public OrderCustomerPageVO getPageVoByOrderId(Long orderId) {
		OrderCustomer orderCustomer = getByOrderId(orderId);
		OrderCustomerPageVO orderCustomerPageVO = OrderCustomerConverter.INSTANCE.poToPageVo(orderCustomer);
		String residenceCode = orderCustomer.getResidenceCode();
		String mailingCode = orderCustomer.getMailingCode();
		List<String> regionCodes = StrUtil.split(residenceCode, StrPool.COMMA);
		Map<String, Province> provinceMap = provinceService.mapByRegionCodes(regionCodes);
		StringBuilder residenceCodeTextBuilder = new StringBuilder();
		for (String regionCode : regionCodes) {
			Province province = provinceMap.get(regionCode);
			String text = province == null ? "" : province.getRegionName();
			residenceCodeTextBuilder.append(text).append(StrPool.SLASH);
		}
		int length = residenceCodeTextBuilder.length();
		String residenceCodeText = length > 0 ? residenceCodeTextBuilder.deleteCharAt(length - 1).toString() : "未知";
		orderCustomerPageVO.setResidenceCodeText(residenceCodeText);
		List<String> mailingCodes = StrUtil.split(mailingCode, StrPool.COMMA);
		Map<String, Province> mailingCodeProvinceMap = provinceService.mapByRegionCodes(mailingCodes);
		StringBuilder mailingCodeTextBuilder = new StringBuilder();
		for (String regionCode : regionCodes) {
			Province province = mailingCodeProvinceMap.get(regionCode);
			String text = province == null ? "" : province.getRegionName();
			mailingCodeTextBuilder.append(text).append(StrPool.SLASH);
		}
		int mailingCodeTextLength = mailingCodeTextBuilder.length();
		String mailingCodeText = mailingCodeTextLength > 0
				? mailingCodeTextBuilder.deleteCharAt(mailingCodeTextLength - 1).toString() : "未知";
		orderCustomerPageVO.setMailingCodeText(mailingCodeText);
		return orderCustomerPageVO;
	}

}
