package com.sms.util;

/**
 * 字符串工具类
 * @author 逆风
 *
 */
public class StringUtil {
	public static boolean isEmpty(String str){
		if(str==null||"".equals(str.trim())){
			return true;
		}
		else{
			return false;
		}
	}
	public static boolean isNotEmpty(String str){
		if(str!=null && !"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	public static void main(String[] args) {
		String s1="   ";
		String s2="1";
		String s3=" s s ";
		String s4=null;
		if(StringUtil.isEmpty(s1)){
			System.out.println("s1是空的");
		}
		if(StringUtil.isNotEmpty(s1)){
			System.out.println("s1不是空的");
		}
		if(StringUtil.isEmpty(s2)){
			System.out.println("s2是空的");
		}
		if(StringUtil.isNotEmpty(s2)){
			System.out.println("s2不是空的");
		}
		if(StringUtil.isEmpty(s3)){
			System.out.println("s3是空的");
		}
		if(StringUtil.isNotEmpty(s3)){
			System.out.println("s3不是空的");
		}
		if(StringUtil.isEmpty(s4)){
			System.out.println("s4是空的");
		}
		if(StringUtil.isNotEmpty(s4)){
			System.out.println("s4不是空的");
		}
		
	}
}
