package com.sms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sms.model.Good;
import com.sms.model.Order;
import com.sms.util.StringUtil;

public class OrderDao {
	
	
	public Order searchByID(Connection con,String id) throws SQLException {
		
		Order order = new Order();		
		StringBuffer sb=new StringBuffer("select * from \"ORDER\" ");
		if(StringUtil.isNotEmpty(id)){
			sb.append(" and order_id ="+id);
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
		
			int order_id;
			int order_total;
			Date order_btime;
			Date order_ftime;
			int order_confirm;
			int order_complete;
			int cus_id;
			

			order_id  = rs.getInt("order_id") ;
			order_total  = rs.getInt("order_id") ;
			order_btime = rs.getDate("order_btime");
			order_ftime = rs.getDate("order_ftime");
			order_confirm  = rs.getInt("order_confirm") ;
			order_complete  = rs.getInt("order_complete") ;
			cus_id  = rs.getInt("cus_id") ;
			
			order.setCus_id(cus_id);
			order.setOrder_btime(order_btime);
			order.setOrder_complete(order_complete);
			order.setOrder_confirm(order_confirm);
			order.setOrder_ftime(order_ftime);
			order.setOrder_id(order_id);
			order.setOrder_total(order_total);
		}
		
		return order;
	}
	
	
	public int delete(Connection con,String idstr)throws Exception{
		
		int id = Integer.parseInt(idstr);
		
		String sql="delete from \"ORDER\" where order_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,id);
		return pstmt.executeUpdate();
	}
	public int delete(Connection con,int id)throws Exception{
		
		String sql="delete from \"ORDER\" where order_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,id);
		return pstmt.executeUpdate();
	}

	public int delete(Connection con,Order order)throws Exception{
		int id = order.getOrder_id();
		String sql="delete from \"ORDER\" where order_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,id);
		return pstmt.executeUpdate();
	}




	public ResultSet listForInit(Connection con,Order order) throws Exception{
		
		StringBuffer sb=new StringBuffer("select ORD_GOO.order_id,cus_username,order_btime,order_ftime "
				+ " from \"ORDER\" left outer join ORD_GOO on \"ORDER\".order_id=ORD_GOO.order_id "
				+ " left outer join GOOD on GOOD.good_id = ORD_GOO.good_id "
				+ " left outer join CUSTOMER on CUSTOMER.cus_id = \"ORDER\".cus_id ");

		if(order.getOrder_id()!=0){
			sb.append(" and ORD_GOO.order_id ="+order.getOrder_id());
		}
		if(order.getCus_id()!=0){
			sb.append(" and \"ORDER\".cus_id ="+order.getCus_id());
		}
		

		sb.append(" group by ORD_GOO.order_id,cus_username,order_btime,order_ftime ");
		sb.append(" order by ORD_GOO.order_id ");
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}


	public ResultSet list(Connection con,Order order) throws Exception{
		
		StringBuffer sb=new StringBuffer("select order_id,cus_id,order_btime from \"ORDER\" natural join ORD_GOO natural join GOOD ");
		if(order.getOrder_id()!=0){
			sb.append(" and order_id ="+order.getOrder_id());
		}
		if(order.getCus_id()!=0){
			sb.append(" and cus_id ="+order.getCus_id());
		}
		/*
		sb.append(" and order_confirm =  "+order.getOrder_confirm());
		sb.append(" and order_complete = "+order.getOrder_complete());*/
		
		sb.append(" group by order_id,cus_id,order_btime ");
		sb.append(" order by order_id ");
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	public ResultSet list(Connection con,Order order,Good good) throws Exception{
		
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
	     
        //必须捕获异常
        Date date = null;
	    date=(order.getOrder_btime());
		System.out.println(date);

		//SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
		/*
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD HH24:MI:SS");*/


		String dateString = simpleDateFormat.format(date);
		
		StringBuffer sb=new StringBuffer("select order_id,cus_username,order_btime,order_ftime from " + 
				"((select order_id from ORD_GOO natural join \"ORDER\" ");
		if(good.getGood_id()!=-1){
			sb.append(" and good_id =  "+ good.getGood_id());
		}
		
		if(order.getOrder_id()!=0){
			sb.append(" and order_id = "+order.getOrder_id());
		}
		if(order.getCus_id()!=0){
			sb.append(" and cus_id = "+order.getCus_id());
		}
		if(order.getOrder_btime()!=null){
			System.out.println(dateString+"   哈哈哈哈");
			sb.append(" and order_btime = to_date(" + dateString + ",'yyyy-MM-dd')  ");/*
			sb.append(" and order_btime = to_date("+dateString+",'YYYY-MM-DD HH24:MI:SS')");*/
		}
		sb.append(" and order_confirm =  "+order.getOrder_confirm());
		sb.append(" and order_complete = "+order.getOrder_complete());
		sb.append(") natural join \"ORDER\") natural join CUSTOMER ");
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		
		
		return pstmt.executeQuery();
	}
	
	
public ResultSet listForOrdGood(Connection con,Order order) throws Exception{
		
		if(order.getOrder_id()==0||"".equals(""+order.getOrder_id())) {
			return null;
		}
		
		/*good_id,good_name,good_price,good_num*/
		StringBuffer sb=new StringBuffer("select good_id,good_name,good_price,good_num from(" + 
				"ORD_GOO natural join GOOD ) where order_id = ?" );
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		pstmt.setInt(1, order.getOrder_id());
		return pstmt.executeQuery();
	}
	public ResultSet listForOrdGood(Connection con,String orderidstr) throws Exception{
	
				
		if("".equals(""+orderidstr)||orderidstr==null||orderidstr=="0") {
			return null;
		}
		
		/*good_id,good_name,good_price,good_num*/
		StringBuffer sb=new StringBuffer("select good_id,good_name,good_price,good_num from(" + 
				"ORD_GOO natural join GOOD ) where order_id = ?" );
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		pstmt.setInt(1, Integer.parseInt(orderidstr));
		return pstmt.executeQuery();
	}
	public ResultSet listForOrdGood(Connection con,int orderid) throws Exception{
		
		
		if("".equals(""+orderid)||orderid==0) {
			return null;
		}
		
		/*good_id,good_name,good_price,good_num*/
		StringBuffer sb=new StringBuffer("select good_id,good_name,good_price,good_num from(" + 
				"ORD_GOO natural join GOOD ) where order_id = ?" );
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		pstmt.setInt(1, orderid);
		return pstmt.executeQuery();
	}
	
	public int getOrderTotal(Connection con,Order order) throws SQLException {
		int total=0;
		int id = order.getOrder_id();
		StringBuffer sb = new StringBuffer("select order_id,sum(good_sum) from(" + 
				"select order_id,good_num*good_price good_sum from(" + 
				"\"ORDER\" natural join ORD_GOO natural join GOOD) where order_id = "+id + 
				"group by order_id,good_num,good_price " + 
				")group by order_id");
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			
			total = rs.getInt("sum(good_sum)");
		}
		return total;
	}
	//////////////////////////////////////////////////////////////////////////////////////////////同义词
	//////////////////////////////////////////////////////////////////////////////////////////////同义词
	//////////////////////////////////////////////////////////////////////////////////////////////同义词
	//////////////////////////////////////////////////////////////////////////////////////////////同义词
	//////////////////////////////////////////////////////////////////////////////////////////////同义词
	//////////////////////////////////////////////////////////////////////////////////////////////同义词
	//////////////////////////////////////////////////////////////////////////////////////////////同义词
	//////////////////////////////////////////////////////////////////////////////////////////////同义词
	//////////////////////////////////////////////////////////////////////////////////////////////同义词
	//////////////////////////////////////////////////////////////////////////////////////////////同义词
	//////////////////////////////////////////////////////////////////////////////////////////////同义词
	//////////////////////////////////////////////////////////////////////////////////////////////同义词
	//////////////////////////////////////////////////////////////////////////////////////////////同义词
	//////////////////////////////////////////////////////////////////////////////////////////////同义词
	//////////////////////////////////////////////////////////////////////////////////////////////同义词
	//////////////////////////////////////////////////////////////////////////////////////////////同义词
	//////////////////////////////////////////////////////////////////////////////////////////////同义词
	//////////////////////////////////////////////////////////////////////////////////////////////同义词
	//////////////////////////////////////////////////////////////////////////////////////////////同义词
	//////////////////////////////////////////////////////////////////////////////////////////////同义词
	//////////////////////////////////////////////////////////////////////////////////////////////同义词
	//////////////////////////////////////////////////////////////////////////////////////////////同义词
	//////////////////////////////////////////////////////////////////////////////////////////////同义词
	//////////////////////////////////////////////////////////////////////////////////////////////同义词
	public int getOrderTotal(Connection con,int orderid) throws SQLException {
		int total=0;
		int id = orderid;
		StringBuffer sb = new StringBuffer("select order_id,sum(good_sum) from(" + 
				"select order_id,good_num*good_price good_sum from(" + 
				"\"ORDER\" natural join O_G natural join GOOD) where order_id = "+id + 
				"group by order_id,good_num,good_price,good_id " + 
				")group by order_id");
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			
			total = rs.getInt("sum(good_sum)");
		}
		return total;
	}
	
	public int getOrderTotal(Connection con,String orderidstr) throws SQLException {
		int total=0;
		int id = Integer.parseInt(orderidstr);
		StringBuffer sb = new StringBuffer("select order_id,sum(good_sum) from(" + 
				"select order_id,good_num*good_price good_sum from(" + 
				"\"ORDER\" natural join ORD_GOO natural join GOOD) where order_id = "+id + 
				"group by order_id,good_num,good_price " + 
				")group by order_id");
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			
			total = rs.getInt("sum(good_sum)");
		}
		return total;
	}
	

	public int getOrderSellNum(Connection con,Order order) throws SQLException {

		int id = order.getOrder_id();
		StringBuffer sb=new StringBuffer("select sum(order_num) from ORD_GOO where order_id=? group by order_id");
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		int sum=0;
		if(rs.next()) {
			sum=rs.getInt("sum(order_num)");
		}
		return sum;
	}		
	public int getOrderSellNum(Connection con,int id2) throws SQLException {
		int id = id2;
		StringBuffer sb=new StringBuffer("select sum(order_num) from ORD_GOO where order_id=? group by order_id");
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		int sum=0;
		if(rs.next()) {
			sum=rs.getInt("sum(order_num)");
		}
		return sum;
	}		
	public int getOrderSellNum(Connection con,String idstr) throws SQLException {
		int id = Integer.parseInt(idstr);
		StringBuffer sb=new StringBuffer("select sum(order_num) from ORD_GOO where order_id=? group by order_id");
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		int sum=0;
		if(rs.next()) {
			sum=rs.getInt("sum(order_num)");
		}
		return sum;
	}
	
		
		

	public int setOrderConfirm(Connection con,String idstr) throws SQLException {
		int id = Integer.parseInt(idstr);
		StringBuffer sb=new StringBuffer("update \"ORDER\" SET order_confirm = 1 where order_id = "+id);
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeUpdate();

	}


	public int setOrderCancel(Connection con, String idstr) throws SQLException {
		int id = Integer.parseInt(idstr);
		StringBuffer sb=new StringBuffer("update \"ORDER\" SET order_confirm = -1 where order_id = "+id);
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeUpdate();
	}



	
}
