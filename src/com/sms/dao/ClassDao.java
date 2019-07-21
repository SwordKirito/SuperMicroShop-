package com.sms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sms.model.Class;
import com.sms.model.Good;
import com.sms.util.StringUtil;


public class ClassDao {
	
	
	public int getClassIdByName(Connection con,String name) throws SQLException {
		
		int id=0;
		
		StringBuffer sb=new StringBuffer("select * from CLASS where class_name=?");
		
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			id = rs.getInt("class_id");
		}
		return id;
		
	}
	
	
	public  int add(Connection con,Class cclass) throws Exception{
		
		String sql="insert into CLASS values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,cclass.getClass_name());
		pstmt.setString(2,cclass.getClass_remark());
		
		return pstmt.executeUpdate();
	}
	/**
	 * 返回结果集
	 * @param con
	 * @param bookType
	 * @return
	 * @throws Exception 
	 */
	public ResultSet list(Connection con,Class cclass) throws Exception{
		
	//select * from class where "class_name" like '%学%' and "class_remark" like '%学%';
		StringBuffer sb=new StringBuffer("select * from CLASS");
		if(StringUtil.isNotEmpty(cclass.getClass_name())){
			sb.append(" and class_name like'%"+cclass.getClass_name()+"%'");
		}
		if(StringUtil.isNotEmpty(cclass.getClass_remark())){
			sb.append(" and class_remark like'%"+cclass.getClass_remark()+"%'");
		}
		sb.append(" order by class_id ");
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	/**
	 * 删除宠物类别
	 * @param con
	 * @param id
	 * @return
	 * @throws Exceptrion
	 */
public int delete(Connection con,Class cclass)throws Exception{
		
		int id = cclass.getClass_id();
		String sql="delete from CLASS where class_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,id);
		return pstmt.executeUpdate();
	}
	
public int delete(Connection con,String idstr)throws Exception{
		
		int id = Integer.parseInt(idstr);
		String sql="delete from CLASS where class_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,id);
		return pstmt.executeUpdate();
	}

public int delete(Connection con,int id)throws Exception{

	String sql="delete from CLASS where class_id=?";
	PreparedStatement pstmt = con.prepareStatement(sql);
	pstmt.setInt(1,id);
	return pstmt.executeUpdate();
}
	/**
	 * 修改宠物类别
	 * @param con
	 * @param id
	 * @return
	 * @throws Exceptrion
	 */
	public int update(Connection con,Class cclass)throws Exception{
		//update CLASS set "class_name"='街舞服饰' ,"class_remark"='街舞，流行，嘻哈' where "class_id"=24;
		String sql="update CLASS set class_name=?,class_remark=? where class_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1,cclass.getClass_name());
		pstmt.setString(2,cclass.getClass_remark());
		pstmt.setInt(3,cclass.getClass_id());
		return pstmt.executeUpdate();
	}
}
