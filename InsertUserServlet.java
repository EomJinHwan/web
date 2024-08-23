package com.ssamz.web.user;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssamz.biz.user.UserDAO_web;
import com.ssamz.biz.user.UserVO_web;

public class InsertUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String encoding;
	
	public void init(ServletConfig config) throws ServletException {
		encoding = config.getInitParameter("boardEncoding");
		System.out.println("Encoding : " + encoding);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		// 사용자 입력정보 추출 및 인코딩 설정
		request.setCharacterEncoding("UTF-8");

		//변수 선언
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String selfInfo = request.getParameter("selfinfo");
		String[] languages = request.getParameterValues("languages");
		String age = request.getParameter("age");
		String phone = request.getParameter("phone");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String birthString = year + "-" + month + "-" + day;
        
        LocalDate birth = null;
        try {
            birth = LocalDate.parse(birthString);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format");
            return;
        }
		
		
		// 2. DB 연동 처리
		UserVO_web vo = new UserVO_web();
		
		vo.setUser_id(id);
		vo.setUser_pw(password);
		vo.setName(name);
		vo.setPhone(phone);
		vo.setBirth(birth);
		
		UserDAO_web dao = new UserDAO_web();
		
		dao.InsertUser(vo);
		
		// 3. 화면 이동
		response.sendRedirect("login.html");

	}
}
