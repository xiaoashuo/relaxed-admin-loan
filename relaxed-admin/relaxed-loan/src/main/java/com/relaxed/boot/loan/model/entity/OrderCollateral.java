package com.relaxed.boot.loan.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 
 *
 * @author yakir 2023-01-10 10:32:31
 */
@Data
@TableName("t_order_collateral")
@Schema(title = "")  
public class OrderCollateral {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
    @Schema(title = "主键", description = "主键")
	private Integer collateralId;
    
	/**
	 * 订单编号
	 */
    @Schema(title = "订单编号", description = "订单编号")
	private Long orderId;
    
	/**
	 * 权属人姓名
	 */
    @Schema(title = "权属人姓名", description = "权属人姓名")
	private String propOwner;
    
	/**
	 * 权属人身份证
	 */
    @Schema(title = "权属人身份证", description = "权属人身份证")
	private String propIdNo;
    
	/**
	 * 权证号
	 */
    @Schema(title = "权证号", description = "权证号")
	private String propCertNo;
    
	/**
	 * 省市区代号
	 */
    @Schema(title = "省市区代号", description = "省市区代号")
	private String districtCode;
    
	/**
	 * 省市区中文(坐落地址)
	 */
    @Schema(title = "省市区中文(坐落地址)", description = "省市区中文(坐落地址)")
	private String addressPrex;
    
	/**
	 * 小区
	 */
    @Schema(title = "小区", description = "小区")
	private String communityNo;
    
	/**
	 * 楼栋
	 */
    @Schema(title = "楼栋", description = "楼栋")
	private String buildingNo;
    
	/**
	 * 门牌号
	 */
    @Schema(title = "门牌号", description = "门牌号")
	private String roomNo;
    
	/**
	 * 地址明细(坐落地址)
	 */
    @Schema(title = "地址明细(坐落地址)", description = "地址明细(坐落地址)")
	private String addressDetail;
    
	/**
	 * 所在楼层
	 */
    @Schema(title = "所在楼层", description = "所在楼层")
	private String floor;
    
	/**
	 * 楼层总数
	 */
    @Schema(title = "楼层总数", description = "楼层总数")
	private String totalFloor;
    
	/**
	 * 建筑面积
	 */
    @Schema(title = "建筑面积", description = "建筑面积")
	private String floorage;
    
	/**
	 * 房屋性质
1.商品房
2.公房
3.经济房
4.其他

	 */
    @Schema(title = "房屋性质 ")
	private String houseNature;
    
	/**
	 * 房层用途
1.住宅
2.公寓
3.别墅
4.办公
5.商业
6.工业
7.其他
	 */
    @Schema(title = "房层用途")
	private String housePurpose;
    
	/**
	 * 共有权人数
	 */
    @Schema(title = "共有权人数", description = "共有权人数")
	private String ownershipQty;
    
	/**
	 * 其他共有权人姓名
	 */
    @Schema(title = "其他共有权人姓名", description = "其他共有权人姓名")
	private String ownershipName;
    
	/**
	 * 房龄
	 */
    @Schema(title = "房龄", description = "房龄")
	private String houseAge;
    
	/**
	 * 房层抵押情况
1.有抵押
2.无抵押
	 */
    @Schema(title = "房层抵押情况")
	private Integer collateralSituation;
    
	/**
	 * 房屋租赁情况
1.未出租
2.承租人已放弃优先购买权,不影响房屋交付
	 */
    @Schema(title = "房屋租赁情况")
	private Integer leaseSituation;
    
	/**
	 * 评估方式
1.系统估值
2.人工估值
	 */
    @Schema(title = "评估方式")
	private Integer evalValuationWay;
    
	/**
	 * 评估价值
	 */
    @Schema(title = "评估价值", description = "评估价值")
	private String evalValuation;
    
	/**
	 * 评估机构代码
	 */
    @Schema(title = "评估机构代码", description = "评估机构代码")
	private String evalAgencyCode;
    
	/**
	 * 评估机构名称
	 */
    @Schema(title = "评估机构名称", description = "评估机构名称")
	private String evalAgency;
    
	/**
	 * 是否满两年
1.满两年
2.末满两年
	 */
    @Schema(title = "是否满两年")
	private Integer ownTwoYear;
    
	/**
	 * 是否唯一住房 1.唯一住房 2.非唯一住房
	 */
    @Schema(title = "是否唯一住房 1.唯一住房 2.非唯一住房", description = "是否唯一住房 1.唯一住房 2.非唯一住房")
	private Integer onlyHousing;
    
	/**
	 * 资产抵押顺位
1.一抵
2.二抵
	 */
    @Schema(title = "资产抵押顺位")
	private Integer collateralRanking;
    
	/**
	 * 抵押物认定价值
	 */
    @Schema(title = "抵押物认定价值", description = "抵押物认定价值")
	private String identifyValuation;
    
	/**
	 * 抵押率
	 */
    @Schema(title = "抵押率", description = "抵押率")
	private String collateralRate;
    
	/**
	 * 前手抵押余额
	 */
    @Schema(title = "前手抵押余额", description = "前手抵押余额")
	private String collateralBalance;
    
	/**
	 * 备注
	 */
    @Schema(title = "备注", description = "备注")
	private String remark;
    
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
    @Schema(title = "创建时间", description = "创建时间")
	private LocalDateTime createdTime;
    
	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(title = "更新时间", description = "更新时间")
	private LocalDateTime updatedTime;
    

}
