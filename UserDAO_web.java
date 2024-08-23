package com.ssamz.biz.user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO_web {

	private Connection connection = null;
	private ResultSet resultSet = null;
	private PreparedStatement preparedstatement = null;

	//쿼리문
	private String user_Insert = "insert into member(user_id, user_pw, name, phone, birth, created_at) values(?,?,?,?,?,CURRENT_TIMESTAMP)";
	private String user_Get = "select * from member where user_id = ?";

	// 회원 등록
	public void InsertUser(UserVO_web vo) {
		try {
			// 데이터베이스 연결
			connection = JDBCUtil_web.getConnection();
			preparedstatement = connection.prepareStatement(user_Insert);

			// SQL 쿼리문에 값 설정
			preparedstatement.setString(1, vo.getUser_id());
			preparedstatement.setString(2, vo.getUser_pw());
			preparedstatement.setString(3, vo.getName());
			preparedstatement.setString(4, vo.getPhone());
			Date sqlDate = Date.valueOf(vo.getBirth());
			preparedstatement.setDate(5, sqlDate);
			
			//SQL 명령이 영향을 미친 행의 수를 반환
			int rowsInserted = preparedstatement.executeUpdate();
			
		//예외 처리
		} catch (SQLException e) {
			System.out.println("데이터베이스 작업 중 오류 발생.");
			e.printStackTrace();
		} finally {
			JDBCUtil_web.close(connection, resultSet, preparedstatement);
		}
	}// end InsertUser
	
	// 사용자 조회
	public UserVO_web getUser(UserVO_web vo) {
		UserVO_web user = null;

		try {
			// 데이터베이스 연결
			connection = JDBCUtil_web.getConnection();
			preparedstatement = connection.prepareStatement(user_Get);
			
			// SQL 쿼리문에 값 설정
			preparedstatement.setString(1, vo.getUser_id());
			
			// 쿼리 실행
			resultSet = preparedstatement.executeQuery();
			
			// 결과 처리
			while (resultSet.next()) {
				user= new UserVO_web();
				user.setUser_id(resultSet.getString("user_id"));
				user.setUser_pw(resultSet.getString("user_pw"));
				user.setName(resultSet.getString("name"));
			}

		// 예외 처리
		} catch (SQLException e) {
			System.out.println("데이터베이스 작업 중 오류 발생.");
			e.printStackTrace();
		} finally {
			JDBCUtil_web.close(connection, resultSet, preparedstatement);
		}
		return user;
		
	}// end getUser

}// end class
