package com.sms.model;

import javax.swing.ImageIcon;

public class Good {
	int good_id;
	String good_name;
	String good_descrip;
	int good_cost;
	int good_price;
	String good_place;
	ImageIcon good_picture;
	int good_hot;
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Good() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Good(String good_name, String good_place) {
		super();
		this.good_name = good_name;
		this.good_place = good_place;
	}


	public Good(String good_name, String good_descrip, int good_cost, int good_price, String good_place,
			ImageIcon good_picture, int good_hot) {
		super();
		this.good_name = good_name;
		this.good_descrip = good_descrip;
		this.good_cost = good_cost;
		this.good_price = good_price;
		this.good_place = good_place;
		this.good_picture = good_picture;
		this.good_hot = good_hot;
	}
	
	public Good(int good_id, String good_name, String good_descrip, int good_cost, int good_price, String good_place,
			ImageIcon good_picture, int good_hot) {
		super();
		this.good_id = good_id;
		this.good_name = good_name;
		this.good_descrip = good_descrip;
		this.good_cost = good_cost;
		this.good_price = good_price;
		this.good_place = good_place;
		this.good_picture = good_picture;
		this.good_hot = good_hot;
	}

	public int getGood_id() {
		return good_id;
	}
	public void setGood_id(int good_id) {
		this.good_id = good_id;
	}
	public String getGood_name() {
		return good_name;
	}
	public void setGood_name(String good_name) {
		this.good_name = good_name;
	}
	public String getGood_descrip() {
		return good_descrip;
	}
	public void setGood_descrip(String good_descrip) {
		this.good_descrip = good_descrip;
	}
	public int getGood_cost() {
		return good_cost;
	}
	public void setGood_cost(int good_cost) {
		this.good_cost = good_cost;
	}
	public int getGood_price() {
		return good_price;
	}
	public void setGood_price(int good_price) {
		this.good_price = good_price;
	}
	public String getGood_place() {
		return good_place;
	}
	public void setGood_place(String good_place) {
		this.good_place = good_place;
	}
	public ImageIcon getGood_picture() {
		return good_picture;
	}
	public void setGood_picture(ImageIcon good_picture) {
		this.good_picture = good_picture;
	}
	public int getGood_hot() {
		return good_hot;
	}
	public void setGood_hot(int good_hot) {
		this.good_hot = good_hot;
	}
	@Override
	public String toString() {
		return good_name;
	}
	
	
	
}
