package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.dao.UserDaoImpl;
import com.toolclass.User;

public class CheckUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取到用户名
		String username = request.getParameter("username");
		
		UserDao userDao = new UserDaoImpl();
		
		User user = userDao.getUserByUsername(username);
		
		String msg = "";
		if(user == null) {
			//可用
			msg = "<span style='color:white'>用户名可使用</span>";
			
		}else {
			//不可用
			msg = "用户名已存在";
		}
		
		response.setContentType("text/html;charset=utf-8");
		
		response.getWriter().println(msg);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
