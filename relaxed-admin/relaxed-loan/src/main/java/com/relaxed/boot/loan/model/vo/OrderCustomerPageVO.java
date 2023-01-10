package com.relaxed.boot.loan.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 分页视图对象
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Data
@Schema(title = "分页视图对象")  
public class OrderCustomerPageVO {
    private static final long serialVersionUID = 1L;

    /**
     * 客户编号，关联t_customer表
     */
    @Schema(title = "客户编号，关联t_customer表", description = "客户编号，关联t_customer表")
    private Long customerId;
    
    /**
     * 合作方客户编号
     */
    @Schema(title = "合作方客户编号", description = "合作方客户编号")
    private String partnerCustomerNo;
    
    /**
     * 授信编号，关联t_credit表
     */
    @Schema(title = "授信编号，关联t_credit表", description = "授信编号，关联t_credit表")
    private Long creditId;
    
    /**
     * 订单号，关联t_order表
     */
    @Schema(title = "订单号，关联t_order表", description = "订单号，关联t_order表")
    private Long orderId;
    
    /**
     * 合作方客户编号
     */
    @Schema(title = "合作方客户编号", description = "合作方客户编号")
    private String orgUserNo;
    
    /**
     * 客户姓名
     */
    @Schema(title = "客户姓名", description = "客户姓名")
    private String realName;
    
    /**
     * 证件类型
     */
    @Schema(title = "证件类型", description = "证件类型")
    private String certificateType;
    
    /**
     * 证件号码
     */
    @Schema(title = "证件号码", description = "证件号码")
    private String certificateNo;
    
    /**
     * 客户类型，1-在校学生2-在职人员3-自雇人员4-其他人士99-人群未知
     */
    @Schema(title = "客户类型，1-在校学生2-在职人员3-自雇人员4-其他人士99-人群未知", description = "客户类型，1-在校学生2-在职人员3-自雇人员4-其他人士99-人群未知")
    private String customType;
    
    /**
     * 性别，枚举值：
0-未知的性别1-男性2-女性9-未说明的性别
     */
    @Schema(title = "性别")
    private String gender;
    
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
    
    /**
     * 国籍，3位字符拉丁字母，参照 GB/T2659-2000
     */
    @Schema(title = "国籍，3位字符拉丁字母，参照 GB/T2659-2000", description = "国籍，3位字符拉丁字母，参照 GB/T2659-2000")
    private String nationality;
    
    /**
     * 手机号码
     */
    @Schema(title = "手机号码", description = "手机号码")
    private String mobileNo;
    
    /**
     * 最高学历，枚举值：
10-研究生20-本科30-大专40-中专、职高、技校60-高中70-初中80-小学90-其他91-初中及以下99-未知
     */
    @Schema(title = "最高学历")
    private Integer educationCode;
    
    /**
     * 最高学位，枚举值：
1-名誉博士2-博士3-硕士4-学士5-无9-未知
     */
    @Schema(title = "最高学位知")
    private Integer degreeCode;
    
    /**
     * 现居住地行政区划，用6位数字型代码表示，参照GB/T2260-2007
     */
    @Schema(title = "现居住地行政区划，用6位数字型代码表示，参照GB/T2260-2007", description = "现居住地行政区划，用6位数字型代码表示，参照GB/T2260-2007")
    private String residenceRegion;
    
    /**
     * 现居住地址
     */
    @Schema(title = "现居住地址", description = "现居住地址")
    private String residenceAddress;
    
    /**
     * 现居住地址省市区编码
     */
    @Schema(title = "现居住地址省市区编码", description = "现居住地址省市区编码")
    private String residenceCode;
    
    /**
     * 现居住地址邮政编码
     */
    @Schema(title = "现居住地址邮政编码", description = "现居住地址邮政编码")
    private String residencePostCode;
    
    /**
     * 居住状况，枚举值：
1-自置2-按揭3-亲属楼宇4-集体宿舍5-租房6-共有住宅7-其他11-自有12-租住9-未知
     */
    @Schema(title = "居住状况")
    private String residenceStatus;
    
    /**
     * 住宅电话
     */
    @Schema(title = "住宅电话", description = "住宅电话")
    private String homePhone;
    
    /**
     * 户籍地址
     */
    @Schema(title = "户籍地址", description = "户籍地址")
    private String permanentAddress;
    
    /**
     * 户籍所在地行政区划，用6位数字型代码表示，参照GB/T2260-2007
     */
    @Schema(title = "户籍所在地行政区划，用6位数字型代码表示，参照GB/T2260-2007", description = "户籍所在地行政区划，用6位数字型代码表示，参照GB/T2260-2007")
    private String permanentRegion;
    
    /**
     * 籍贯
     */
    @Schema(title = "籍贯", description = "籍贯")
    private String nativePlace;
    
    /**
     * 婚姻状况，枚举值：
10-未婚20-已婚21-初婚22-再婚23-复婚30-丧偶40-离婚90-未说明的婚姻状况91-单身99-未知
     */
    @Schema(title = "婚姻状况")
    private String maritalStatus;
    
    /**
     * 用户电子邮箱
     */
    @Schema(title = "用户电子邮箱", description = "用户电子邮箱")
    private String eMail;
    
    /**
     * 就业状态，
11-国家公务员
13-专业技术人员
17-职员
21-企业管理人员
24-工人
27-农民
31-学生
37-现役军人
51-自由职业者
54-个体经营者
70-无业人员
80-退（离）休人员
90-其他
91-在职
99-未知
     */
    @Schema(title = "就业状态")
    private String employmentStatus;
    
    /**
     * 职业
0-国家机关、党群组织、企业、事业单位负责人
1-专业技术人员
3-办事人员和有关人员
4-商业、服务业人员
5-农、林、牧、渔、水利业生产人员
6-生产、运输设备操作人员及有关人员
X-军人
Y-不便分类的其他从业人员
Z-未知
     */
    @Schema(title = "职业")
    private String professionCode;
    
    /**
     * 职务
1:高级领导（行政级别局级及局级以上领导或大公司高级管理人员）
2:中级领导（行政级别局级以下领导或大公司中级管理人员）
3:一般员工
9:未知
     */
    @Schema(title = "职务")
    private String jobDuties;
    
    /**
     * 职称
0:无
1:高级
2:中级
3:初级
9:未知
     */
    @Schema(title = "职称")
    private String positionalTitles;
    
    /**
     * 现单位名称
     */
    @Schema(title = "现单位名称", description = "现单位名称")
    private String companyName;
    
    /**
     * 现单位所属行业，枚举值：
A-农、林、牧、渔业B-采矿业C-制造业D-电力、热力、燃气及水生产和供应业
E-建筑业F-批发和零售业G-交通运输、仓储和邮储业H-住宿和餐饮业I-信息传输、 软件和信息技术服务J-金融业K-房地产业L-租赁和商务服务业M-科学研究和技术服务业N-水利、环境和公共设施管理业O-居民服务、修理和其他服务业P-教育Q-卫生和社会工作R-文化、体育和娱乐业S-公共管理、社会保障和社会组织T-国际组织9-未知
     */
    @Schema(title = "现单位所属行业")
    private String companyKind;
    
    /**
     * 现单位性质，枚举值：
10-机关、事业单位20-国有企业30-外资企业40-个体、私营企业50-其他（包括三资企业、民营企业、民间团体等）99-未知
     */
    @Schema(title = "现单位性质")
    private String companyTrade;
    
    /**
     * 现单位地址
     */
    @Schema(title = "现单位地址", description = "现单位地址")
    private String companyAddress;
    
    /**
     * 现单位行政区划，用6位数字型代码表示，参照GB/T2260-2007
     */
    @Schema(title = "现单位行政区划，用6位数字型代码表示，参照GB/T2260-2007", description = "现单位行政区划，用6位数字型代码表示，参照GB/T2260-2007")
    private String companyRegion;
    
    /**
     * 现单位地址邮政编码（无法填报时，统一填999999）
     */
    @Schema(title = "现单位地址邮政编码（无法填报时，统一填999999）", description = "现单位地址邮政编码（无法填报时，统一填999999）")
    private String companyPostCode;
    
    /**
     * 现单位固定电话
     */
    @Schema(title = "现单位固定电话", description = "现单位固定电话")
    private String companyPhone;
    
    /**
     * 现单位工资账号
     */
    @Schema(title = "现单位工资账号", description = "现单位工资账号")
    private String salaryAccount;
    
    /**
     * 单位工资账号开户银行代码
     */
    @Schema(title = "单位工资账号开户银行代码", description = "单位工资账号开户银行代码")
    private String accountOpenBank;
    
    /**
     * 通讯地址
     */
    @Schema(title = "通讯地址", description = "通讯地址")
    private String mailingAddress;
    
    /**
     * 通讯地址省市区编码
     */
    @Schema(title = "通讯地址省市区编码", description = "通讯地址省市区编码")
    private String mailingCode;
    
    /**
     * 通讯地址邮政编码
     */
    @Schema(title = "通讯地址邮政编码", description = "通讯地址邮政编码")
    private String mailingPostCode;
    
    /**
     * 通讯地址行政区划，用6位数字型代码表示，参照GB/T2260-2007
     */
    @Schema(title = "通讯地址行政区划，用6位数字型代码表示，参照GB/T2260-2007", description = "通讯地址行政区划，用6位数字型代码表示，参照GB/T2260-2007")
    private String mailingRegion;
    
    /**
     * 完整通讯地址
     */
    @Schema(title = "完整通讯地址", description = "完整通讯地址")
    private String fullMailingAddress;
    
    /**
     * 现单位工作年限
     */
    @Schema(title = "现单位工作年限", description = "现单位工作年限")
    private Integer jobYears;
    
    /**
     * 年收入
     */
    @Schema(title = "年收入", description = "年收入")
    private String annualIncome;
    
    /**
     * 证件有效期起始日期
     */
    @Schema(title = "证件有效期起始日期", description = "证件有效期起始日期")
    private LocalDate certificateValidityDate;
    
    /**
     * 证件有效期起始日期
     */
    @Schema(title = "证件有效期起始日期", description = "证件有效期起始日期")
    private LocalDate certificateExpiryDate;
    
    /**
     * 证件签发机关名称
     */
    @Schema(title = "证件签发机关名称", description = "证件签发机关名称")
    private String certificateIssuer;
    
    /**
     * 证件签发机关所在地行政区划
     */
    @Schema(title = "证件签发机关所在地行政区划", description = "证件签发机关所在地行政区划")
    private String certificateIssuerRegion;
    
    /**
     * 是否关联关系（1是 0否）
     */
    @Schema(title = "是否关联关系（1是 0否）", description = "是否关联关系（1是 0否）")
    private Integer existsLink;
    
    /**
     * 关联类型（exists_link为1时必填。关联类型：1信托投资公司的股东、2信托投资公司股东的关联企业，如股东的子公司、3信托投资公司自有资金投资的企业、4信托投资公司自有资金投资的企业的关联企业，如投资的企业的子公司、5信托投资公司以托管或信托等其他方式控制的企业、6信托投资公司的董事、监事、经理、7信托投资公司的董事、监事、经理投资持股5%以上或者担任高级管理人员的公司、企业或其他经济组织、8其他关联）
     */
    @Schema(title = "关联类型（exists_link为1时必填。关联类型：1信托投资公司的股东、2信托投资公司股东的关联企业，如股东的子公司、3信托投资公司自有资金投资的企业、4信托投资公司自有资金投资的企业的关联企业，如投资的企业的子公司、5信托投资公司以托管或信托等其他方式控制的企业、6信托投资公司的董事、监事、经理、7信托投资公司的董事、监事、经理投资持股5%以上或者担任高级管理人员的公司、企业或其他经济组织、8其他关联）", description = "关联类型（exists_link为1时必填。关联类型：1信托投资公司的股东、2信托投资公司股东的关联企业，如股东的子公司、3信托投资公司自有资金投资的企业、4信托投资公司自有资金投资的企业的关联企业，如投资的企业的子公司、5信托投资公司以托管或信托等其他方式控制的企业、6信托投资公司的董事、监事、经理、7信托投资公司的董事、监事、经理投资持股5%以上或者担任高级管理人员的公司、企业或其他经济组织、8其他关联）")
    private Integer linkType;
    
    /**
     * 投资入股信托公司金额（LINK_TYPE为1，2，3时股东投资入股信托公司金额。默认传0）
     */
    @Schema(title = "投资入股信托公司金额（LINK_TYPE为1，2，3时股东投资入股信托公司金额。默认传0）", description = "投资入股信托公司金额（LINK_TYPE为1，2，3时股东投资入股信托公司金额。默认传0）")
    private Integer linkInvestMoney;
    
    /**
     * 逻辑删除标识，未删除为 0，已删除为删除时间
     */
    @Schema(title = "逻辑删除标识，未删除为 0，已删除为删除时间", description = "逻辑删除标识，未删除为 0，已删除为删除时间")
    private Integer valid;
    
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
