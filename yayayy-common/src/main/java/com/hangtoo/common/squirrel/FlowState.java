package com.hangtoo.common.squirrel;
enum FlowState {
	DRAFT("草稿", "DRAFT"), APPLYING("申请中", "APPLYING"), REJECTED("拒绝", "REJECTED"), DEPARTMENT_APPROVED("部门领导审批通过", "DEPARTMENT_APPROVED"),PASSED("通过", "PASSED");
	String name;
	String code;

	FlowState(String name, String code) {
		this.name = name;
		this.code = code;
	}
}