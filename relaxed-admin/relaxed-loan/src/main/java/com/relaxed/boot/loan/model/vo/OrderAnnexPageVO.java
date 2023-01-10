package com.relaxed.boot.loan.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 分页视图对象
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Data
@Schema(title = "分页视图对象")  
public class OrderAnnexPageVO {
    private static final long serialVersionUID = 1L;

    /**
     * 文件主键
     */
    @Schema(title = "文件主键", description = "文件主键")
    private Long fileId;
    
    /**
     * 订单编号,关联t_order表
     */
    @Schema(title = "订单编号,关联t_order表", description = "订单编号,关联t_order表")
    private Long orderId;
    
    /**
     * 文件流水号
     */
    @Schema(title = "文件流水号", description = "文件流水号")
    private String fileNo;
    
    /**
     *  文件名
     */
    @Schema(title = " 文件名", description = " 文件名")
    private String fileName;
    
    /**
     * 文件类型（https://www.yuque.com/ntgnx4/hoyh19/ughdvq#g8rHZ）
     */
    @Schema(title = "文件类型（https://www.yuque.com/ntgnx4/hoyh19/ughdvq#g8rHZ）", description = "文件类型（https://www.yuque.com/ntgnx4/hoyh19/ughdvq#g8rHZ）")
    private Integer fileType;
    
    /**
     * 文件网址
     */
    @Schema(title = "文件网址", description = "文件网址")
    private String fileUrl;
    
    /**
     * 备注
     */
    @Schema(title = "备注", description = "备注")
    private String remark;
    
    /**
     * 上传时间
     */
    @Schema(title = "上传时间", description = "上传时间")
    private LocalDateTime uploadTime;
    
    /**
     * 逻辑删除标识，未删除为 0，已删除为删除时间
     */
    @Schema(title = "逻辑删除标识，未删除为 0，已删除为删除时间", description = "逻辑删除标识，未删除为 0，已删除为删除时间")
    private Integer valid;
    
    /**
     * 当前影像资料是否可以修改 0可修改,1不可修改
     */
    @Schema(title = "当前影像资料是否可以修改 0可修改,1不可修改", description = "当前影像资料是否可以修改 0可修改,1不可修改")
    private Integer updStatus;
    
    /**
     * 是否推送给云融0未推送,1已推送
     */
    @Schema(title = "是否推送给云融0未推送,1已推送", description = "是否推送给云融0未推送,1已推送")
    private Integer isPush;
    
    /**
     * 通知批次号
     */
    @Schema(title = "通知批次号", description = "通知批次号")
    private String noticeBatchNo;
    
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
    
    /**
     * 审核记录
     */
    @Schema(title = "审核记录", description = "审核记录")
    private String auditRecord;
    
    /**
     * 审核标记1.红色2.橙色3.黄色4.绿色5.蓝色6:√,7:×
     */
    @Schema(title = "审核标记1.红色2.橙色3.黄色4.绿色5.蓝色6:√,7:×", description = "审核标记1.红色2.橙色3.黄色4.绿色5.蓝色6:√,7:×")
    private Integer auditFlag;
    

}