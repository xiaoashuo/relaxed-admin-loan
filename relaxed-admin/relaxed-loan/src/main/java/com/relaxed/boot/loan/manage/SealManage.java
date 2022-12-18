package com.relaxed.boot.loan.manage;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.IdUtil;
import com.relaxed.boot.framework.config.RelaxedConfig;
import com.relaxed.boot.loan.model.dto.PreviewInfoDTO;
import com.relaxed.boot.loan.model.entity.Seal;
import com.relaxed.boot.loan.model.entity.Template;
import com.relaxed.boot.loan.service.SealService;
import com.relaxed.boot.loan.service.TemplateService;
import com.relaxed.boot.loan.util.FileConvert;
import com.relaxed.boot.loan.util.KeywordLocation;
import com.relaxed.boot.loan.util.PdfUtil;
import com.relaxed.boot.loan.util.PreviewSignInfo;
import com.relaxed.common.core.exception.BusinessException;
import com.relaxed.common.model.result.SysResultCode;
import com.relaxed.starter.download.domain.DownloadModel;
import com.relaxed.starter.download.functions.DownloadCallback;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * @author Yakir
 * @Topic SealManage
 * @Description
 * @date 2022/12/18 16:00
 * @Version 1.0
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class SealManage {
    private final SealService sealService;

    private final TemplateService templateService;

    public DownloadModel preview(PreviewInfoDTO previewInfoDTO) {
        Integer templateId = previewInfoDTO.getTemplateId();
        Template template = templateService.getById(templateId);
        Assert.notNull(template,()->{
            throw new BusinessException(SysResultCode.SERVER_ERROR.getCode(),"模板不存在");
        });
        //模板路径
        String templatePath = template.getTemplatePath();
        File templateWordFile = new File(templatePath);

        try {
            //pdf 模板
            ByteArrayOutputStream templatePdfFileByte=new ByteArrayOutputStream();
            FileConvert.doc2pdf(new FileInputStream(templateWordFile),templatePdfFileByte);
            Integer sealId = previewInfoDTO.getSealId();
            Seal seal = sealService.getById(sealId);

            //签章路径
            String sealPath = seal.getSealPath();
            String keyword = previewInfoDTO.getKeyword();

            String fileName = IdUtil.simpleUUID() + ".pdf";
            String profile = RelaxedConfig.getProfile();
            File dirFile = new File(profile, "preview");
            FileUtil.mkdir(dirFile);
            File targetFile=new File(dirFile,fileName);

            byte[] pdfContent = templatePdfFileByte.toByteArray();
            ByteArrayInputStream pdfByteInputStream = new ByteArrayInputStream(pdfContent);
            List<KeywordLocation> keywordLocations = PdfUtil.extractKeywordLocation(pdfByteInputStream,null, keyword);
            pdfByteInputStream.reset();
            PreviewSignInfo previewSignInfo = new PreviewSignInfo();
            previewSignInfo.setImgPath(sealPath);
            previewSignInfo.setContentBefore(false);
            previewSignInfo.setKeywordLocationList(keywordLocations);
            PdfUtil.addImage(pdfByteInputStream,targetFile,previewSignInfo);
            DownloadModel downloadModel = new DownloadModel(dirFile.getAbsolutePath(), fileName, "pdf", StrPool.SLASH);
            DownloadCallback downloadCallback = () -> {
                // 清理工作
                FileUtil.del(targetFile);
            };
            downloadModel.setDownloadCallback(downloadCallback);
            return downloadModel;
        }catch (Exception e){
          throw new BusinessException(SysResultCode.SERVER_ERROR.getCode(),"预览文件异常",e);
        }finally {

        }


    }
}
