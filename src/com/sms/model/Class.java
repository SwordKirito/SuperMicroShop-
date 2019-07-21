package com.sms.model;

public class Class {
	private int class_id;
	private String class_name;
	private String class_remark;
	
	
	
	
	
	
	public Class() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Class(String class_name, String class_remark) {
		super();
		this.class_name = class_name;
		this.class_remark = class_remark;
	}
	public Class(int class_id, String class_name, String class_remark) {
		super();
		this.class_id = class_id;
		this.class_name = class_name;
		this.class_remark = class_remark;
	}
	public int getClass_id() {
		return class_id;
	}
	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public String getClass_remark() {
		return class_remark;
	}
	public void setClass_remark(String class_remark) {
		this.class_remark = class_remark;
	}
	@Override
	public String toString() {
		return class_name;
	}
	
	
	
	
	
	
}
