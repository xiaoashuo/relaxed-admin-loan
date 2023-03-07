package com.relaxed.boot.risk.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.risk.mapper.RiskModelMapper;
import com.relaxed.boot.risk.model.converter.RiskModelConverter;
import com.relaxed.boot.risk.model.entity.RiskModel;
import com.relaxed.boot.risk.model.qo.RiskModelQO;
import com.relaxed.boot.risk.model.vo.RiskModelVO;
import com.relaxed.boot.risk.service.RiskModelService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;

import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import com.relaxed.extend.mybatis.plus.toolkit.PageUtil;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 业务层实现
 * </p>
 *
 * @author Yakir
 * @since 2021-08-29T09:04:20.388
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class RiskModelServiceImpl extends ExtendServiceImpl<RiskModelMapper, RiskModel> implements RiskModelService {

	@Override
	public RiskModel getByModelName(String modelName) {
		return baseMapper.getByModelName(modelName);
	}

	@Override
	public PageResult<RiskModelVO> selectByPage(PageParam pageParam, RiskModelQO modelQO) {
		IPage<RiskModel> page = PageUtil.prodPage(pageParam);
		LambdaQueryWrapperX<RiskModel> wrapper = WrappersX.lambdaQueryX(RiskModel.class);
		 wrapper.eq(ObjectUtil.isNotNull(modelQO.getId()),
				RiskModel::getId, modelQO.getId())
				 .eqIfPresent(RiskModel::getModelName,modelQO.getModelName())
				 .eqIfPresent(RiskModel::getGuid,modelQO.getGuid())
				 .orderByDesc(RiskModel::getId);
		this.baseMapper.selectPage(page, wrapper);
		IPage<RiskModelVO> voPage = page.convert(RiskModelConverter.INSTANCE::poToVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

	@Override
	public List<RiskModelVO> listByStatus(Integer status) {
		List<RiskModel> list = baseMapper.listByStatus(status);
		return RiskModelConverter.INSTANCE.poToVOs(list);
	}

	@Override
	public RiskModelVO getByGuid(String guid) {
		log.info("model service getByGuid,{}", guid);
		RiskModel model = baseMapper.getByGuid(guid);
		return model != null ? RiskModelConverter.INSTANCE.poToVo(model) : null;
	}

}
