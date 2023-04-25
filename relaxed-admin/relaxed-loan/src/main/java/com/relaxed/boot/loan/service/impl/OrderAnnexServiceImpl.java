package com.relaxed.boot.loan.service.impl;

import cn.hutool.core.text.StrPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.framework.config.RelaxedConfig;
import com.relaxed.boot.loan.model.entity.OrderAnnex;
import com.relaxed.boot.loan.model.vo.OrderAnnexPageVO;
import com.relaxed.boot.loan.model.qo.OrderAnnexQO;
import com.relaxed.boot.loan.mapper.OrderAnnexMapper;
import com.relaxed.boot.loan.service.OrderAnnexService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import com.relaxed.starter.download.domain.DownloadModel;
import org.springframework.stereotype.Service;

/**
 * @author yakir 2023-01-10 10:32:19
 */
@Service
public class OrderAnnexServiceImpl extends ExtendServiceImpl<OrderAnnexMapper, OrderAnnex>
		implements OrderAnnexService {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult<OrderAnnexPageVO> 分页数据
	 */
	@Override
	public PageResult<OrderAnnexPageVO> queryPage(PageParam pageParam, OrderAnnexQO qo) {
		return baseMapper.queryPage(pageParam, qo);
	}

	@Override
	public OrderAnnex getByOrderIdAndFileType(Long orderId, Integer fileType) {
		return getOne(Wrappers.lambdaQuery(OrderAnnex.class).eq(OrderAnnex::getOrderId, orderId)
				.eq(OrderAnnex::getFileType, fileType));
	}

	@Override
	public DownloadModel download(Long fileId) {
		OrderAnnex orderAnnex = getById(fileId);
		String fileUrl = orderAnnex.getFileUrl();
		String fullPath = RelaxedConfig.getProfile() + fileUrl;
		return new DownloadModel(fullPath, StrPool.SLASH);
	}

}
