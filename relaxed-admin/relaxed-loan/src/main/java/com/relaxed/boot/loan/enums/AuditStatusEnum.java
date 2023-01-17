package com.relaxed.boot.loan.enums;

public enum AuditStatusEnum {

	WAITING_EXAM("P", "审核中"), EXAM_PASS("Y", "审核通过"), EXAM_DENY("N", "审核拒绝"), EXAM_INTERNAL_ERR("E", "系统内部异常");

	private String code;

	private String desc;

	AuditStatusEnum(String code, String desc) {
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
