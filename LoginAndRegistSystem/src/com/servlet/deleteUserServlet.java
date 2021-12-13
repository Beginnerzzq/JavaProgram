package com.servlet;
//处理注销用户请求的servlet

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.dao.UserDaoImpl;
import com.toolclass.User;

public class deleteUserServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 更改密码功能实现
		System.out.println("注销用户请求过来了。。。");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println(username + "," + password);
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		//获取Dao对象
		UserDao userDao = new UserDaoImpl();
		
		//判断删除之前，改用户是否存在
		User user = userDao.getUserByUsernameAndPassword(username, password);
		if(user != null) {
			//注销用户（删除）
			userDao.deleteUser(username, password);
		}
		//删除之后看看是否还在
		User user2 = userDao.getUserByUsernameAndPassword(username, password);
		if(user2 != null) {
			out.print("注销失败！请检查用户名与密码是否正确");
		}else {
			out.print("已注销！如果想重新登录，请注册新的账号");
		}
	}
}