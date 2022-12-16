package com.relaxed.boot.loan.service;

import com.relaxed.boot.loan.model.entity.Template;
import com.relaxed.boot.loan.model.vo.TemplatePageVO;
import com.relaxed.boot.loan.model.qo.TemplateQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 模板文件配置表
 *
 * @author yakir 2022-12-14 11:36:23
 */
public interface TemplateService extends ExtendService<Template> {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult&lt;TemplatePageVO&gt; 分页数据
    */
    PageResult<TemplatePageVO> queryPage(PageParam pageParam, TemplateQO qo);



    String getByTemplateCode(String templateCode);
}
