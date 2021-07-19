package com.dao;

import com.toolclass.User;

public interface UserDao {
	
	public User getUserByUsernameAndPassword(String username,String password);
	
	public User getUserByUsername(String username);
	
	//如果注册的信息特别多，一定要封装成类传递。这里只有两条，从简。。。
	public void insertUser(String username,String password);
}
