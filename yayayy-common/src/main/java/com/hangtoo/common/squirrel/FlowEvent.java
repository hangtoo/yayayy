package com.hangtoo.common.squirrel;
enum FlowEvent {
	APPLY("申请", "APPLY"), REJECT("拒绝", "REJECT"), DEPARTMENT_APPROVE("部门领导审批通过", "DEPARTMENT_APPROVE"), HR_APPROVE("HR审批通过", "HR_APPROVE");
	String name;
	String code;

	FlowEvent(String name, String code) {
		this.name = name;
		this.code = code;
	}
}