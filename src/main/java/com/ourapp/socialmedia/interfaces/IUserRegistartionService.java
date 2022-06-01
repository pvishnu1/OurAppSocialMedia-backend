package com.ourapp.socialmedia.interfaces;

import com.ourapp.socialmedia.entity.User;

public interface IUserRegistartionService {

	public User registerUser(User user) throws Exception;
	
	public User findUser(String userName) throws Exception;
}
