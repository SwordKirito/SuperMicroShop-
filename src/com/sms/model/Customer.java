package com.sms.model;

import java.sql.Date;

import javax.swing.ImageIcon;

public class Customer {
	private int cus_id;
	private String cus_username;
	private String cus_pwd;
	private int cus_order_num;
	private int cus_source;
	private Date cus_register_time;
	private ImageIcon cus_picture ;
	private long cus_telephone;
	private String cus_address;
	
	
	
	
	
	
	
	
	
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(int cus_id, String cus_username, String cus_pwd) {
		super();
		this.cus_id = cus_id;
		this.cus_username = cus_username;
		this.cus_pwd = cus_pwd;
	}
	public Customer(int cus_id, String cus_username, String cus_pwd, int cus_order_num, int cus_source,
			Date cus_register_time, ImageIcon cus_picture, long cus_telephone, String cus_address) {
		super();
		this.cus_id = cus_id;
		this.cus_username = cus_username;
		this.cus_pwd = cus_pwd;
		this.cus_order_num = cus_order_num;
		this.cus_source = cus_source;
		this.cus_register_time = cus_register_time;
		this.cus_picture = cus_picture;
		this.cus_telephone = cus_telephone;
		this.cus_address = cus_address;
	}
	public Customer(String cus_username, String cus_pwd) {
		super();
		this.cus_username = cus_username;
		this.cus_pwd = cus_pwd;
	}
	public int getCus_id() {
		return cus_id;
	}
	public void setCus_id(int cus_id) {
		this.cus_id = cus_id;
	}
	public String getCus_username() {
		return cus_username;
	}
	public void setCus_username(String cus_username) {
		this.cus_username = cus_username;
	}
	public String getCus_pwd() {
		return cus_pwd;
	}
	public void setCus_pwd(String cus_pwd) {
		this.cus_pwd = cus_pwd;
	}
	public int getCus_order_num() {
		return cus_order_num;
	}
	public void setCus_order_num(int cus_order_num) {
		this.cus_order_num = cus_order_num;
	}
	public int getCus_source() {
		return cus_source;
	}
	public void setCus_source(int cus_source) {
		this.cus_source = cus_source;
	}
	public Date getCus_register_time() {
		return cus_register_time;
	}
	public void setCus_register_time(Date cus_register_time) {
		this.cus_register_time = cus_register_time;
	}
	public ImageIcon getCus_picture() {
		return cus_picture;
	}
	public void setCus_picture(ImageIcon cus_picture) {
		this.cus_picture = cus_picture;
	}
	public long getcus_telephone() {
		return cus_telephone;
	}
	public void setcus_telephone(long cus_telephone) {
		this.cus_telephone = cus_telephone;
	}
	public String getCus_address() {
		return cus_address;
	}
	public void setCus_address(String cus_address) {
		this.cus_address = cus_address;
	}
	@Override
	public String toString() {
		return "Customer [cus_id=" + cus_id + ", cus_username=" + cus_username + ", cus_pwd=" + cus_pwd
				+ ", cus_order_num=" + cus_order_num + ", cus_source=" + cus_source + ", cus_register_time="
				+ cus_register_time + ", cus_picture=" + cus_picture + ", cus_telephone=" + cus_telephone + ", cus_address="
				+ cus_address + "]";
	}

	
	
	
	
}
