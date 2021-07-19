package com.servlet;
//处理注册请求的Servlet

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.dao.UserDaoImpl;
import com.toolclass.User;

public class RegistSerlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//注册的业务处理
		//1、获取到用户提交的用户信息
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		//2、判断用户名是否可用
		UserDao userDao = new UserDaoImpl();
		
		User user = userDao.getUserByUsername(username);
		if (user != null) {
			//注册失败回到注册页面，并进行相应的提示
			//转发
			req.setAttribute("registMsg", "用户名已经存在！");
			req.getRequestDispatcher("regist.jsp").forward(req, resp);
		}else {
			//注册成功去往登录页面
			//将用户注册的信息插入到数据库
			userDao.insertUser(username, password);
			
			resp.sendRedirect("login.jsp");
		}
	}
}
