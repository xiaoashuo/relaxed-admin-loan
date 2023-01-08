package com.relaxed.boot.loan.service;

import com.relaxed.boot.loan.model.entity.TemplateArea;
import com.relaxed.boot.loan.model.vo.TemplateAreaPageVO;
import com.relaxed.boot.loan.model.qo.TemplateAreaQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

/**
 * 模板文件文本域表
 *
 * @author Yakir 2022-12-16 11:18:37
 */
public interface TemplateAreaService extends ExtendService<TemplateArea> {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult&lt;TemplateAreaPageVO&gt; 分页数据
	 */
	PageResult<TemplateAreaPageVO> queryPage(PageParam pageParam, TemplateAreaQO qo);

	boolean removeByTemplateId(Integer templateId);
}