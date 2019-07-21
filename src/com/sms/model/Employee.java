package com.sms.model;

import javax.swing.ImageIcon;

public class Employee {

	private int emp_id;
	private String emp_name;
	private String emp_pwd;

	private int emp_age;
	private int emp_salary;
	private int emp_work_time;

	private int shop_id;
	private String emp_tele;
	private String emp_qq;

	private ImageIcon emp_photo ;
	
	

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(String emp_name, String emp_pwd) {
		super();
		this.emp_name = emp_name;
		this.emp_pwd = emp_pwd;
	}

	public Employee(int emp_id) {
		super();
		this.emp_id = emp_id;
	}
	

	public Employee(String emp_name, String emp_pwd, int emp_age, int emp_salary, int emp_work_time, int shop_id,
			String emp_tele, String emp_qq, ImageIcon emp_photo) {
		super();
		this.emp_name = emp_name;
		this.emp_pwd = emp_pwd;
		this.emp_age = emp_age;
		this.emp_salary = emp_salary;
		this.emp_work_time = emp_work_time;
		this.shop_id = shop_id;
		this.emp_tele = emp_tele;
		this.emp_qq = emp_qq;
		this.emp_photo = emp_photo;
	}

	public Employee(int emp_id, String emp_name, String emp_pwd, int emp_age, int emp_salary, int emp_work_time,
			int shop_id, String emp_tele, String emp_qq, ImageIcon emp_photo) {
		super();
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.emp_pwd = emp_pwd;
		this.emp_age = emp_age;
		this.emp_salary = emp_salary;
		this.emp_work_time = emp_work_time;
		this.shop_id = shop_id;
		this.emp_tele = emp_tele;
		this.emp_qq = emp_qq;
		this.emp_photo = emp_photo;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEmp_pwd() {
		return emp_pwd;
	}

	public void setEmp_pwd(String emp_pwd) {
		this.emp_pwd = emp_pwd;
	}

	public int getEmp_age() {
		return emp_age;
	}

	public void setEmp_age(int emp_age) {
		this.emp_age = emp_age;
	}

	public int getEmp_salary() {
		return emp_salary;
	}

	public void setEmp_salary(int emp_salary) {
		this.emp_salary = emp_salary;
	}

	public int getEmp_work_time() {
		return emp_work_time;
	}

	public void setEmp_work_time(int emp_work_time) {
		this.emp_work_time = emp_work_time;
	}

	public int getShop_id() {
		return shop_id;
	}

	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}

	public String getEmp_tele() {
		return emp_tele;
	}

	public void setEmp_tele(String emp_tele) {
		this.emp_tele = emp_tele;
	}

	public String getEmp_qq() {
		return emp_qq;
	}

	public void setEmp_qq(String emp_qq) {
		this.emp_qq = emp_qq;
	}

	public ImageIcon getEmp_photo() {
		return emp_photo;
	}

	public void setEmp_photo(ImageIcon emp_photo) {
		this.emp_photo = emp_photo;
	}

	@Override
	public String toString() {
		return "Employee [emp_id=" + emp_id + ", emp_name=" + emp_name + ", emp_pwd=" + emp_pwd + ", emp_age=" + emp_age
				+ ", emp_salary=" + emp_salary + ", emp_work_time=" + emp_work_time + ", shop_id=" + shop_id
				+ ", emp_tele=" + emp_tele + ", emp_qq=" + emp_qq + ", emp_photo=" + emp_photo + "]";
	}

	
		
	
	
}
