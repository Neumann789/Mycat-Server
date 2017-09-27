package com.test.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

public class TestTransaction {
	
	public static Connection connection;
	
	static{
		String jdbcdriver="com.mysql.jdbc.Driver";
        String jdbcurl="jdbc:mysql://127.0.0.1:8066/MYDB?useUnicode=true&characterEncoding=utf-8";
        String username="root";
        String password="123456";
        System.out.println("开始连接mysql:"+jdbcurl);
        try {
            Class.forName(jdbcdriver);
            connection = DriverManager.getConnection(jdbcurl,username,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		
	}
	
	public static void main(String[] args) throws Throwable {
		
		boolean isAutoCommit = false;
		
		String sql = "insert into t_user(id,user_name,age) values(?,?,?)";
		
		connection.setAutoCommit(isAutoCommit);
		
		PreparedStatement ps = connection.prepareStatement(sql);
		
		Random random = new Random();
		
		int randomId = random.nextInt(1000);
		
		ps.setLong(1, randomId);
		ps.setString(2, "tom"+randomId);
		ps.setLong(3, randomId);
		
		ps.execute();
		
		System.out.println("randomId:"+randomId);
		
		if(!isAutoCommit){
			connection.rollback();
			System.out.println("执行回滚操作!");
		}
		
		ps.close();
		connection.close();
		
	}
	
	
	
	public static void  testUpdate(){
		
		
		
	}
	
	
	
	
	
	
	

}
