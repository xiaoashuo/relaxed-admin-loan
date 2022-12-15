package com.relaxed.boot.loan.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 签章证书表分页视图对象
 *
 * @author yakir 2022-12-14 11:36:23
 */
@Data
@Schema(title = "签章证书表分页视图对象")  
public class CertificatePageVO {
    private static final long serialVersionUID = 1L;

    /**
     * 签章证书主键id
     */
    @Schema(title = "签章证书主键id", description = "签章证书主键id")
    private Integer certificateId;
    
    /**
     * 证书别名
     */
    @Schema(title = "证书别名", description = "证书别名")
    private String certificateAlias;
    
    /**
     * 主体类型 1个人 2公司
     */
    @Schema(title = "主体类型 1个人 2公司", description = "主体类型 1个人 2公司")
    private Integer subjectType;
    
    /**
     * 个人及个人名称,公司及名称
     */
    @Schema(title = "个人及个人名称,公司及名称", description = "个人及个人名称,公司及名称")
    private String certificateSubject;
    
    /**
     * 主体证件号 个人 身份证号 公司 则营业执照编码
     */
    @Schema(title = "主体证件号 个人 身份证号 公司 则营业执照编码", description = "主体证件号 个人 身份证号 公司 则营业执照编码")
    private String certificateNo;
    
    /**
     * 证书密码
     */
    @Schema(title = "证书密码", description = "证书密码")
    private String certificatePwd;
    
    /**
     * 证书描述
     */
    @Schema(title = "证书描述", description = "证书描述")
    private String certificateRemark;
    /**
     * 证书存储库类型 1jks 2pkcs12
     */
    @Schema(title = "证书存储库类型", description = "证书存储库类型")
    private Integer keystoreType;
    /**
     * 证书授权类型
     */
    @Schema(title = "证书授权类型", description = "证书授权类型")
    private Integer authorizeType;
    /**
     * 证书地址
     */
    @Schema(title = "证书地址", description = "证书地址")
    private String certificateAddress;
    /**
     * 创建人
     */
    @Schema(title = "创建人", description = "创建人")
    private Integer createBy;
    
    /**
     * 修改人
     */
    @Schema(title = "修改人", description = "修改人")
    private Integer updateBy;
    
    /**
     * 逻辑删除标识，未删除为 0，已删除为删除时间
     */
    @Schema(title = "逻辑删除标识，未删除为 0，已删除为删除时间", description = "逻辑删除标识，未删除为 0，已删除为删除时间")
    private Long deleted;
    
    /**
     * 创建时间
     */
    @Schema(title = "创建时间", description = "创建时间")
    private LocalDateTime createdTime;
    
    /**
     * 更新时间
     */
    @Schema(title = "更新时间", description = "更新时间")
    private LocalDateTime updatedTime;
    

}