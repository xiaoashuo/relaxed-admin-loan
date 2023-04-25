package com.relaxed.boot.loan.service;

import com.relaxed.boot.loan.model.entity.Province;
import com.relaxed.boot.loan.model.vo.ProvincePageVO;
import com.relaxed.boot.loan.model.qo.ProvinceQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.domain.SelectData;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

import java.util.List;
import java.util.Map;

/**
 * 省市区码表
 *
 * @author Yakir 2023-03-08 10:44:09
 */
public interface ProvinceService extends ExtendService<Province> {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult&lt;ProvincePageVO&gt; 分页数据
	 */
	PageResult<ProvincePageVO> queryPage(PageParam pageParam, ProvinceQO qo);

	List<SelectData> listByParentCode(Integer parentCode);

	Map<String, Province> mapByRegionCodes(List<String> regionCodes);

}
