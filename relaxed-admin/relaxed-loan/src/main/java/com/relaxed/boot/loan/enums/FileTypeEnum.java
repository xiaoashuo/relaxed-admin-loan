package com.relaxed.boot.loan.enums;


/**
 * 文件类型
 * @author Yakir
 */

public enum FileTypeEnum {

	A("1", "身份证正面"), B("2", "身份证反面"), C("3", "手持身份证"), D("4", "活体识别照片"),

	E("5", "营业执照（包括三证合一营业执照）"), F("6", "开户许可证"), G("7", "房产证"), H("8", "征信查询授权书"),

	A9("9", "借款协议"), J("10", "代扣授权书"), K("11", "授信协议"),

	L("12", "抵质押协议"), M("13", "出质单"), N("14", "他项权利受理单"), O("15", "他项权证"),

	P("16", "公证受理回执"), Q("17", "公证书"), R("18", "债权转让原始文件"),

	S("19", "押品估值excel表"), T("20", "户口本或户籍证明"), U("21", "银行卡照片"),

	V("22", "库存照片"), W("23", "个人信息授权书"), X("24", "贷款用途证明"), Y("25", "视频面签材料"),

	Z("26", "授权委托书"),

	A2("28", "电子签名存根 E签宝存根"),

	A5("31", "放款凭证"), A6("32", "保单"), A19("33", "收入证明"), A7("34", "银行流水"),

	A8("35", "人行征信报告"), A36("36", "房屋登记簿/住房情况登记表"), A10("37", "房屋评估报告"), A11("38", "房屋抵押影像资料"),

	A12("39", "出租房的承租人申明"), A13("40", "入押回执"), A14("41", "公正回执"), A15("42", "签约申请表"),

	A16("43", "购房合同"), A17("44", "借款协议（已盖章）"), A20("45", "婚姻证明材料"), LOAN_AGREEMENT_STAMP("44", "借款协议(已盖章)"),

	A21("46", "知情确认书"), A22("47", "车辆登记证书"), A23("48", "行驶证"), A24("49", "原始债权借款合同"),

	A25("50", "原始债权征信授权书及个人信息授权书"), A26("51", "原始债权委托扣款授权书"), A27("52", "原始债权的委托担保合同"),

	A53("53", "结清证明"), A29("54", "还款计划表"), A30("55", "国有土地使用证"), A31("56", "上家资料及下家资料"),

	A18("99", "其他");

	private String code;

	private String desc;

	FileTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
