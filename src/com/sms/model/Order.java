package com.sms.model;

import java.util.Date;

public class Order {
	int order_id;
	int order_total;
	Date order_btime;
	Date order_ftime;
	int order_confirm;
	int order_complete;
	int cus_id;

	/*
	 * Order(0,0);未确认
	Order(1,0);已确认，未完成
	Order(1,1);已确认，已完成
	Order(-1,0);已取消
	*/
	
	public Order() {
		super();
		order_confirm=0;
		order_complete=0;
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Order(int order_confirm, int order_complete) {
		super();
		this.order_confirm = order_confirm;
		this.order_complete = order_complete;
	}



	public Order(int order_id, int order_total, Date order_btime, int cus_id) {
		super();
		this.order_id = order_id;
		this.order_total = order_total;
		this.order_btime = order_btime;
		this.cus_id = cus_id;
	}

	public Order(int order_total, Date order_btime, Date order_ftime, int order_confirm, int order_complete,
			int cus_id) {
		super();
		this.order_total = order_total;
		this.order_btime = order_btime;
		this.order_ftime = order_ftime;
		this.order_confirm = order_confirm;
		this.order_complete = order_complete;
		this.cus_id = cus_id;
	}
	public Order(int order_id, int order_total, Date order_btime, Date order_ftime, int order_confirm,
			int order_complete, int cus_id) {
		super();
		this.order_id = order_id;
		this.order_total = order_total;
		this.order_btime = order_btime;
		this.order_ftime = order_ftime;
		this.order_confirm = order_confirm;
		this.order_complete = order_complete;
		this.cus_id = cus_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getOrder_total() {
		return order_total;
	}
	public void setOrder_total(int order_total) {
		this.order_total = order_total;
	}
	public Date getOrder_btime() {
		return order_btime;
	}
	public void setOrder_btime(Date order_btime) {
		this.order_btime = order_btime;
	}
	public Date getOrder_ftime() {
		return order_ftime;
	}
	public void setOrder_ftime(Date order_ftime) {
		this.order_ftime = order_ftime;
	}
	public int getOrder_confirm() {
		return order_confirm;
	}
	public void setOrder_confirm(int order_confirm) {
		this.order_confirm = order_confirm;
	}
	public int getOrder_complete() {
		return order_complete;
	}
	public void setOrder_complete(int order_complete) {
		this.order_complete = order_complete;
	}
	public int getCus_id() {
		return cus_id;
	}
	public void setCus_id(int cus_id) {
		this.cus_id = cus_id;
	}
	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", order_total=" + order_total + ", order_btime=" + order_btime
				+ ", order_ftime=" + order_ftime + ", order_confirm=" + order_confirm + ", order_complete="
				+ order_complete + ", cus_id=" + cus_id + "]";
	}
	
	
	
	
	
}
