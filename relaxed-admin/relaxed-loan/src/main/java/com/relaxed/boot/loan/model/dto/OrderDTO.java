package com.relaxed.boot.loan.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Yakir
 * @Topic OrderDTO
 * @Description
 * @date 2023/1/13 11:41
 * @Version 1.0
 */
@Data
@Schema(title  = "订单dto")
public class OrderDTO {
    @Schema(description = "订单id")
    private Long orderId;
    @Schema(description = "项目id")
    private Integer projectId;

    @Schema(title = "期数单位")
    private String periodUnit;
    @Schema(title = "利率单位", description = "利率单位")
    private Integer interestRateUnit;
    @Schema(title = "还款方式", description = "还款方式")
    private Integer repaymentWay;
    /**
     * 申请金额（单位:元）
     */
    @Schema(title = "申请金额（单位:元）", description = "申请金额（单位:元）")
    private BigDecimal applyAmount;


    /**
     * 合作方业务号
     */
    @Schema(title = "合作方业务号", description = "合作方业务号")
    private String partnerBizNo;
    /**
     * 借款期限
     */
    @Schema(title = "借款期限", description = "借款期限")
    private Integer loanPeriod;
    /**
     * 利率
     */
    @Schema(title = "利率", description = "利率")
    private BigDecimal interestRate;


    /**
     * 证件类型
     */
    @Schema(title = "证件类型", description = "证件类型")
    private String certificateType;
    @Schema(title = "客户类型，1-在校学生2-在职人员3-自雇人员4-其他人士99-人群未知", description = "客户类型，1-在校学生2-在职人员3-自雇人员4-其他人士99-人群未知")
    private String customType;
    /**
     * 性别，枚举值：
     0-未知的性别1-男性2-女性9-未说明的性别
     */
    @Schema(title = "性别，枚举值： 0-未知的性别1-男性2-女性9-未说明的性别", description = "性别，枚举值： 0-未知的性别1-男性2-女性9-未说明的性别")
    private Integer gender;

    /**
     * 年龄
     */
    @Schema(title = "年龄", description = "年龄")
    private Integer age;

    /**
     * 出生日期
     */
    @Schema(title = "出生日期", description = "出生日期")
    private LocalDate birthDate;

    @NotEmpty(message = "姓名不能为空")
    private String realName;

    @NotEmpty(message = "手机号不能为空")
    private String mobileNo;
    @NotEmpty(message = "身份证号不能为空")
    private String certificateNo;
    @Schema(description = "邮箱")
    private String email;//否	string	邮箱地址
    @NotEmpty(message = "证件有效期起始日期不能为空")
    private LocalDate certificateStartDate;//
    @NotEmpty(message = "证件有效期到期日期不能为空")
    private LocalDate certificateEndDate;
    @NotEmpty(message = "现居住地址不能为空")
    private String residenceAddress;
    @NotEmpty(message = "通讯地址不能为空")
    private String mailingAddress;
    @NotEmpty(message = "现居住地址Code不能为空")
    private String residenceCode;
    @NotEmpty(message = "通讯地址Code不能为空")
    private String mailingCode;
    private Integer jobYears;
    @NotNull(message = "年收入不能为空")
    private Integer annualIncome;
    private String companyName;
    private Integer graceDays;
    private BigDecimal penaltyRate;
    /**
     * 就业状态
     */
    @NotNull(message = "就业状态不能为空")
    private Integer employmentStatus;
    /**
     * 职业
     */
    @NotNull(message = "职业不能为空")
    private String professionCode;
    /**
     * 贷款用途
     */
    @NotNull(message = "贷款用途不能为空")
    private Integer loanPurpose;

    @NotEmpty(message = "合同号不能为空")
    private String contractNo;
    @Schema(title = "担保方式")
    private String guaranteeWay;
    @Schema(title = "投入分类（V0100:个人贷款）", description = "投入分类（V0100:个人贷款）")
    private String investType;
    @Schema(title = "业务种类明细")
    private Integer businessDetail;
    @Schema(title = "行业明细（V0100:个人贷款）", description = "行业明细（V0100:个人贷款）")
    private String industryDetail;
    @Schema(title = "还款来源")
    private Integer repaymentSource;
    @Schema(title = "投向行业")
    private Integer investIndustry;
    @Schema(title = "贷款类型（1:流动资金贷款 2:固定资产贷款 3:境外筹资转贷 4:买方信贷 5:出口卖方信贷 6:项目融资 7:其他贷款）", description = "贷款类型（1:流动资金贷款 2:固定资产贷款 3:境外筹资转贷 4:买方信贷 5:出口卖方信贷 6:项目融资 7:其他贷款）")
    private Integer loanType;
}
