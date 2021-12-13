package com.servlet;
//处理更改密码请求的servlet

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.dao.UserDaoImpl;
import com.toolclass.User;

public class ChangePasswordServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 更改密码功能实现
		System.out.println("更改密码请求过来了。。。");
		String username = req.getParameter("username");
		String passwordFirst = req.getParameter("passwordFirst");
		String passwordSecond = req.getParameter("passwordSecond");
		System.out.println(username + "," + passwordFirst + "," + passwordSecond);
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		//获取Dao对象
		UserDao userDao = new UserDaoImpl();
		//拿到用户类
		User user = userDao.getUserByUsernameAndPassword(username, passwordFirst);
		if(user != null) {
			//用户存在，说明可以进行修改密码的操作
			userDao.changeUserByUsername(username, passwordFirst, passwordSecond);
			//修改完成后给予信息
			out.print("修改成功！");
		}else {
			out.print("用户名或密码错误！修改失败！");
		}
	}
}