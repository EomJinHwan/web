package com.ssamz.biz.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil_web {
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			// MySQL JDBC 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project1", "root", "1111");
			System.out.println("연결 성공!");
				
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버를 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 오류.");
			e.printStackTrace();
		}
		
		return connection;	
	}
	
	public static void close(Connection connection, ResultSet resultSet, PreparedStatement preparedstatement) {
		try {		
			if (connection != null)
				connection.close();
			if (resultSet != null)
				resultSet.close();
			if (preparedstatement != null)
				preparedstatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
