package com.relaxed.boot.loan.job;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.itextpdf.signatures.PdfSignatureAppearance;
import com.relaxed.boot.common.system.utils.file.ByteArrayMultipartFile;
import com.relaxed.boot.common.system.utils.file.FileConfig;
import com.relaxed.boot.common.system.utils.file.FileMeta;
import com.relaxed.boot.common.system.utils.file.FileMultipartFile;
import com.relaxed.boot.common.system.utils.file.FileUtils;
import com.relaxed.boot.framework.config.RelaxedConfig;
import com.relaxed.boot.loan.constants.LoanUploadPath;
import com.relaxed.boot.loan.enums.CertificateEnum;
import com.relaxed.boot.loan.enums.StampEnum;
import com.relaxed.boot.loan.model.entity.Certificate;
import com.relaxed.boot.loan.model.entity.Order;
import com.relaxed.boot.loan.model.entity.OrderAnnex;
import com.relaxed.boot.loan.model.entity.ProjectTemplate;
import com.relaxed.boot.loan.model.entity.Seal;
import com.relaxed.boot.loan.model.entity.StampRecord;
import com.relaxed.boot.loan.service.CertificateService;
import com.relaxed.boot.loan.service.LoanService;
import com.relaxed.boot.loan.service.OrderAnnexService;
import com.relaxed.boot.loan.service.OrderService;
import com.relaxed.boot.loan.service.ProjectTemplateService;
import com.relaxed.boot.loan.service.SealService;
import com.relaxed.boot.loan.service.StampRecordService;
import com.relaxed.boot.loan.util.KeywordLocation;
import com.relaxed.boot.loan.util.LogFormatUtil;
import com.relaxed.boot.loan.util.PdfUtil;
import com.relaxed.boot.loan.util.SignInfo;
import com.relaxed.boot.loan.util.pdf.MultiSignMeta;
import com.relaxed.boot.loan.util.pdf.PdfTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author Yakir
 * @Topic SettleCertificateGenerate
 * @Description
 * @date 2023/1/28 17:49
 * @Version 1.0
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class StampHandleJob {
    private final StampRecordService stampRecordService;

    private final LoanService loanService;

    private final OrderService orderService;

    private final ProjectTemplateService projectTemplateService;

    private final OrderAnnexService orderAnnexService;

    private final SealService sealService;
    private final CertificateService certificateService;
    public static void main(String[] args) {
        String partnerBizNo="123";
        log.info(LogFormatUtil.format("签章任务处理","开始",LocalDateTime.now()));
        log.info(LogFormatUtil.format("签章任务处理","处理中",LocalDateTime.now(),"关联订单不存在,订单号{}",partnerBizNo));
        log.info(LogFormatUtil.format("签章任务处理","处理中",LocalDateTime.now(),"关联签章信息不存在,订单号{}",partnerBizNo));
        log.info(LogFormatUtil.format("签章任务处理","处理中",LocalDateTime.now(),"关联附件不存在,订单号{}",partnerBizNo));
        log.info(LogFormatUtil.format("签章任务处理","结束",LocalDateTime.now()));
    }
    @Scheduled(fixedRate = 60*1000)
    public void stampHandleJob(){
        log.info(LogFormatUtil.format("签章任务处理","开始",LocalDateTime.now()));
        List<StampRecord> waitStampRecordList=stampRecordService.listByStatus(StampEnum.Status.WAIT_START.getVal());
        if (CollectionUtil.isEmpty(waitStampRecordList)){
            log.info(LogFormatUtil.format("签章任务处理","效验", LocalDateTime.now(),"待查数据为空"));
            return;
        }
        for (StampRecord stampRecord : waitStampRecordList) {
            String partnerBizNo = stampRecord.getPartnerBusinessId();
            Order order = orderService.getByPartnerBizNo(partnerBizNo);
            if (order==null){
                log.info(LogFormatUtil.format("签章任务处理","处理中",LocalDateTime.now(),"关联订单不存在,订单号{}",partnerBizNo));
                continue;
            }
            Integer fileType = stampRecord.getFileType();
            Integer projectId = order.getProjectId();
            Long orderId = order.getOrderId();

            ProjectTemplate projectTemplate = projectTemplateService.getByPidAndFileType(projectId, fileType);
            if (projectTemplate==null){
                log.info(LogFormatUtil.format("签章任务处理","处理中",LocalDateTime.now(),"关联签章信息不存在,订单号{}",partnerBizNo));
                continue;
            }
            OrderAnnex orderAnnex= orderAnnexService.getByOrderIdAndFileType(order.getOrderId(),fileType);
            if (orderAnnex==null){
                log.info(LogFormatUtil.format("签章任务处理","处理中",LocalDateTime.now(),"关联附件不存在,订单号{}",partnerBizNo));
                continue;
            }
            String fileUrl = orderAnnex.getFileUrl();

            File originFile = FileUtils.download(RelaxedConfig.getProfile(),fileUrl);
            Integer destFileType = projectTemplate.getDestFileType();
            Integer keystoreId = projectTemplate.getKeystoreId();
            Certificate keystore = certificateService.getById(keystoreId);
            Integer keystoreType = keystore.getKeystoreType();
            CertificateEnum.KEYSTORE_TYPE keystoreEnum = CertificateEnum.KEYSTORE_TYPE.getByVal(keystoreType);
            Integer sealWay = projectTemplate.getSealWay();
            if (StampEnum.SealWay.KEYWORD.getVal().equals(sealWay)){
                String sealKeyword = projectTemplate.getSealKeyword();
                Integer sealId = projectTemplate.getSealId();
                Seal seal = sealService.getById(sealId);
                String keystorePath=RelaxedConfig.getProfile()+keystore.getCertificateAddress();
                String certificatePwd = keystore.getCertificatePwd();
                char[] pwdCharArray = certificatePwd.toCharArray();
                try {
                    ByteArrayOutputStream signedStream=new ByteArrayOutputStream();
                    KeyStore ks = KeyStore.getInstance(keystoreEnum.getDesc());
                    ks.load(new FileInputStream(keystorePath), pwdCharArray);
                    String alias = ks.aliases().nextElement();
                    PrivateKey pk = (PrivateKey) ks.getKey(alias, pwdCharArray);
                    // 获取证书链
                    java.security.cert.Certificate[] chain = ks.getCertificateChain(alias);
                    String sealImagePath=RelaxedConfig.getProfile()+seal.getSealAddress();
                    //关键字坐标
                    List<KeywordLocation> keywordLocations = PdfUtil.extractKeywordLocation(originFile, sealKeyword);
                    MultiSignMeta signInfo=new MultiSignMeta();
                    signInfo.setReason("测试");
                    signInfo.setLocation("北京市");
                    signInfo.setContact("xxx@qq.com");
                    signInfo.setImagePath(sealImagePath);
                    signInfo.setFieldName("sign");
                    signInfo.setChain(chain);
                    signInfo.setDescription("relaxed Signed on " + new Date());
                    signInfo.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC);
                    signInfo.setPk(pk);
                    signInfo.setOffsetX(-20);
                    signInfo.setOffsetY(-50);
                    signInfo.setWidth(100);
                    signInfo.setHeight(100);
                    signInfo.setKeywordLocationList(keywordLocations);
                    PdfTemplate.multiSign(new FileInputStream(originFile),signedStream,signInfo);
                    String originalMainFileName = FileNameUtil.mainName(originFile);
                    String originalextName = FileNameUtil.extName(originFile);
                    String signedFileName = originalMainFileName + "_已签署."+originalextName;

                    ByteArrayMultipartFile uploadFile = new ByteArrayMultipartFile(signedStream.toByteArray(), signedFileName);

                    FileMeta fileMeta = FileUtils.upload(RelaxedConfig.getProfile(), LoanUploadPath.ANNEX_RELATIVE_PATH, uploadFile,
                            FileConfig.create().splitDate(true));
                    String relativeFilePath = fileMeta.getRelativeFilePath();
                    String fileId=fileMeta.getFileId();
                    String filename = fileMeta.getFilename();
                    OrderAnnex stampOrderAnnex = new OrderAnnex();
                    stampOrderAnnex.setOrderId(orderId);
                    stampOrderAnnex.setFileNo(fileId);
                    stampOrderAnnex.setFileName(filename);
                    stampOrderAnnex.setFileType(destFileType);
                    stampOrderAnnex.setFileUrl(relativeFilePath);
                    orderAnnexService.save(stampOrderAnnex);

                    stampRecord.setStatus(StampEnum.Status.SUCCESS.getVal());
                    stampRecordService.updateById(stampRecord);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else if (StampEnum.SealWay.TEMPLATE.getVal().equals(sealWay)){

            }

        }
        log.info(LogFormatUtil.format("签章任务处理","结束",LocalDateTime.now()));


    }
}
