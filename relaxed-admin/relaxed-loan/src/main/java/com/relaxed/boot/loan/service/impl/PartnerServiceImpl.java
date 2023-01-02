package com.relaxed.boot.loan.service.impl;

import com.relaxed.boot.loan.model.entity.Partner;
import com.relaxed.boot.loan.model.vo.PartnerPageVO;
import com.relaxed.boot.loan.model.qo.PartnerQO;
import com.relaxed.boot.loan.mapper.PartnerMapper;
import com.relaxed.boot.loan.service.PartnerService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.domain.SelectData;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 合作方
 *
 * @author yakir 2022-12-14 11:36:23
 */
@Service
public class PartnerServiceImpl extends ExtendServiceImpl<PartnerMapper, Partner> implements PartnerService {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult<PartnerPageVO> 分页数据
	 */
	@Override
	public PageResult<PartnerPageVO> queryPage(PageParam pageParam, PartnerQO qo) {
		return baseMapper.queryPage(pageParam, qo);
	}

	@Override
	public List<SelectData> queryPartnerList() {
		return list().stream().map(item -> {
			SelectData<Void> selectItem = new SelectData<>();
			selectItem.setLabel(item.getPartnerAlias());
			selectItem.setValue(item.getPartnerId());
			return selectItem;
		}).collect(Collectors.toList());
	}

}
