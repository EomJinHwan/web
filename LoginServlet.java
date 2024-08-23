package com.ssamz.web.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssamz.biz.user.UserDAO_web;
import com.ssamz.biz.user.UserVO_web;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//html 에서 입력받은 id, password 를 user_id, user_pw에 저장
		String user_id = request.getParameter("id");
		String user_pw = request.getParameter("password");

		// 사용자 ID로 UserVO_web 객체를 생성합니다.
		UserVO_web vo = new UserVO_web();
		vo.setUser_id(user_id);

		// 데이터베이스에서 사용자 정보를 조회합니다.
		UserDAO_web dao = new UserDAO_web();
		UserVO_web user = dao.getUser(vo);

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();

		if (user != null) {
				//데이터베이스 pw 		입력한 pw
			if (user.getUser_pw().equals(user_pw)) {
				out.println(user.getName() + "님 로그인 환영!<br>");
				out.println("<a href='/getBoardList.do'>글 목록 이동</a>");
			} else {
				out.println("비밀번호 오류 입니다<br>");
				out.println("<a href='/'> 다시 로그인</a>");
			}
		} else {
			out.println("아이디 오류입니다 <br>");
			out.println("<a href='/'> 다시 로그인 </a>");
		}

	}

}
