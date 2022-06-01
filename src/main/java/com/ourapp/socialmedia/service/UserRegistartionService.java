package com.ourapp.socialmedia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ourapp.socialmedia.entity.User;
import com.ourapp.socialmedia.interfaces.IUserRegistartionService;
import com.ourapp.socialmedia.repository.UserRepository;

@Service
public class UserRegistartionService implements IUserRegistartionService {

	Logger logger = LoggerFactory.getLogger(UserRegistartionService.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public User registerUser(User user) throws Exception {
		logger.debug("user Details is: {}",user);
		try {
			return userRepository.save(user);
		}catch(Exception ex) {
			logger.debug("Exception is: {}",ex.getCause());
			throw new Exception(ex.getCause());
		}
	}

	@Override
	public User findUser(String userName) throws Exception {
		logger.debug("findUser user name is: {}",userName);
		try {
			return userRepository.findByUserName(userName);
		}catch(Exception ex) {
			logger.debug("Exception in findUser is: {}",ex.getCause());
			throw new Exception(ex.getCause());
		}
	}
	
	

}
