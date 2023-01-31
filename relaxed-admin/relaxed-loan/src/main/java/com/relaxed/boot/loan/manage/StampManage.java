package com.relaxed.boot.loan.manage;

import cn.hutool.core.util.IdUtil;
import com.relaxed.boot.common.system.utils.file.ByteArrayMultipartFile;
import com.relaxed.boot.common.system.utils.file.FileConfig;
import com.relaxed.boot.common.system.utils.file.FileMeta;
import com.relaxed.boot.common.system.utils.file.FileUtils;
import com.relaxed.boot.framework.config.RelaxedConfig;
import com.relaxed.boot.loan.enums.FileTypeEnum;
import com.relaxed.boot.loan.enums.StampEnum;
import com.relaxed.boot.loan.model.entity.Loan;
import com.relaxed.boot.loan.model.entity.Order;
import com.relaxed.boot.loan.model.entity.OrderAnnex;
import com.relaxed.boot.loan.model.entity.OrderCustomer;
import com.relaxed.boot.loan.model.entity.ProjectTemplate;
import com.relaxed.boot.loan.model.entity.StampRecord;
import com.relaxed.boot.loan.model.entity.Template;
import com.relaxed.boot.loan.service.OrderAnnexService;
import com.relaxed.boot.loan.service.OrderCustomerService;
import com.relaxed.boot.loan.service.OrderService;
import com.relaxed.boot.loan.service.ProjectTemplateService;
import com.relaxed.boot.loan.service.StampRecordService;
import com.relaxed.boot.loan.service.TemplateService;
import com.relaxed.boot.loan.util.LogFormatUtil;
import com.relaxed.boot.loan.util.word.IWordTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yakir
 * @Topic StampManage
 * @Description
 * @date 2023/1/31 14:28
 * @Version 1.0
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class StampManage {

    private final ProjectTemplateService projectTemplateService;

    private final TemplateService templateService;
    private final OrderService orderService;

    private final OrderCustomerService orderCustomerService;

    private final IWordTemplate wordTemplate;
    private final OrderAnnexService orderAnnexService;

    private final StampRecordService stampRecordService;
    public void generateRelatedFile(String partnerBizNo,FileTypeEnum fileTypeEnum) {
        String subject=fileTypeEnum.getDesc();
        log.info(LogFormatUtil.format(subject,"开始", LocalDateTime.now(),"订单号{}",partnerBizNo));
        Order order = orderService.getByPartnerBizNo(partnerBizNo);
        Long orderId = order.getOrderId();
        OrderCustomer orderCustomer = orderCustomerService.getByOrderId(orderId);
        ProjectTemplate projectTemplate=projectTemplateService.getByPidAndFileType(order.getProjectId(), Integer.valueOf(fileTypeEnum.getCode()));
        Integer templateId = projectTemplate.getTemplateId();
        Integer keystoreId = projectTemplate.getKeystoreId();
        //模板
        Template template = templateService.getById(templateId);
        String templateName = template.getTemplateName();
        String pdfFileName=templateName+".pdf";
        String templatePath = RelaxedConfig.getProfile()+template.getTemplateUrl();;
        //模板文件
        File templateFile = new File(templatePath);
        //原始数据
        Map<String,Object> data=new HashMap<>();
        data.put("contractNo",order.getPartnerContractNo());
        data.put("username",orderCustomer.getRealName());
        //1.渲染原始模板pdf
        try {
            FileInputStream templateFileStream = new FileInputStream(templateFile);
            templateFileStream.mark(0);
            ByteArrayOutputStream originPdf=new ByteArrayOutputStream();
            wordTemplate.renderPdf(templateFileStream,originPdf,data);
            ByteArrayMultipartFile uploadFile = new ByteArrayMultipartFile(originPdf.toByteArray(), pdfFileName);
            FileMeta fileMeta = FileUtils.upload(RelaxedConfig.getProfile(), "profile/contract", uploadFile,
                    FileConfig.create().splitDate(true));
            String relativeFilePath = fileMeta.getRelativeFilePath();
            String fileNo = IdUtil.getSnowflakeNextIdStr();
            OrderAnnex orderAnnex = new OrderAnnex();
            orderAnnex.setOrderId(orderId);
            orderAnnex.setFileNo(fileNo);
            orderAnnex.setFileName(fileTypeEnum.getDesc());
            orderAnnex.setFileType(Integer.valueOf(fileTypeEnum.getCode()));
            orderAnnex.setFileUrl(relativeFilePath);
            orderAnnex.setRemark("");
            orderAnnexService.save(orderAnnex);

            //插入一条待签章记录
            StampRecord stampRecord = new StampRecord();
            stampRecord.setProjectId(order.getProjectId());
            stampRecord.setProductCode(order.getProductCode());
            stampRecord.setTrustPlanCode(order.getTrustPlanCode());
            stampRecord.setFileType(Integer.valueOf(fileTypeEnum.getCode()));
            stampRecord.setContractName(fileTypeEnum.getDesc()+"原始文件.pdf");
            stampRecord.setStatus(StampEnum.Status.WAIT_START.getVal());
            stampRecord.setPartnerBusinessId(order.getPartnerBizNo());
            stampRecord.setSignWay(0);
            stampRecordService.save(stampRecord);



        } catch (Exception e) {
            log.info(LogFormatUtil.format(subject,"异常",LocalDateTime.now(),"订单号{}",partnerBizNo),e);
            throw new RuntimeException(e);
        }
        log.info(LogFormatUtil.format(subject,"结束",LocalDateTime.now(),"订单号{}",partnerBizNo));
        //2.渲染签章后pdf

    }
}
