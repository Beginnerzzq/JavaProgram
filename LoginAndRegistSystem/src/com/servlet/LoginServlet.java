package com.servlet;
//处理登录请求的Servlet

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.dao.UserDaoImpl;
import com.toolclass.User;


public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//登录功能的实现
		
		
		System.out.println("登录请求过来了。。。");
		
		
		//获取到用户的用户名和密码，进行登录业务的处理
		/*
		 * HttpServletRequest：请求对象。Servlet容器会在请求到达后，创建出一个request对象，
		 * 						将Http请求相关的信息全部封装到该对象中
		 */
		
		//获取用户名
		String username = req.getParameter("username");
		//获取密码
		String password = req.getParameter("password");
		System.out.println(username + "," + password);
		
		//验证用户名密码是否正确
		
		//通过响应对象HttpServletResponse，给客户端响应数据
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		//获取Dao对象
		UserDao userDao = new UserDaoImpl();
		
		User user = userDao.getUserByUsernameAndPassword(username, password);
		
		if(user != null) {
			//登录成功
			out.println("login success!");
		}else {
			//通过重定向的方式去往登录页面
			/*
			 * 服务器会给浏览器发送一个302状态码以及一个新地址
			 */
			//resp.sendRedirect("login.jsp");
			
			/*
			 * 转发：
			 */
			//转发之前，绑定数据，就是将想要交给下一个组件（JSP）处理的数据，绑定到request对象中。
			req.setAttribute("loginMsg", "用户名或密码错误!");
			
			//获取转发器
			RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
			//开始转发
			rd.forward(req, resp);
			
		}
		
		
		
		
		
		
	}
}