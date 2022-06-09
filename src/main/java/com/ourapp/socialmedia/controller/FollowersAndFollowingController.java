package com.ourapp.socialmedia.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ourapp.socialmedia.entity.FollowData;
import com.ourapp.socialmedia.entity.Followers;
import com.ourapp.socialmedia.entity.User;
import com.ourapp.socialmedia.entity.UserFollowInfo;
import com.ourapp.socialmedia.repository.FollowersRespository;
import com.ourapp.socialmedia.repository.UserRepository;
import com.ourapp.socialmedia.view.ResponseObjectService;

@RestController
@RequestMapping("/ourApp/follow")
public class FollowersAndFollowingController {
	
	@Autowired
	FollowersRespository followersRespository;
	
	@Autowired
	UserRepository userRepository;

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	
	
	@PostMapping("/followUser")
	public ResponseEntity<String> followUser(@RequestBody FollowData followdata) {
		logger.debug("followUser is ====>{}",followdata);
		try {
			User user = userRepository.findByUserName(followdata.getUserName());
			logger.debug("user is ====>{}",user);
			followersRespository.save(new Followers(user.getId(), followdata.getId()));
	       return ResponseEntity.ok("followed ");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage()); 
		}		
	}
	
	@PostMapping("/unFollowUser")
	public ResponseEntity<String> unFollowUser(@RequestBody FollowData followdata) {
		logger.debug("unFollowUser is ====>{}",followdata);
		try {
			User user = userRepository.findByUserName(followdata.getUserName());
			logger.debug("user is ====>{}",user);
			followersRespository.unFollowUser(user.getId(), followdata.getId());
	       return ResponseEntity.ok("unFollowed ");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage()); 
		}		
	}
	
	
	@GetMapping("/followers/{userName}")
	public int followersForUser(@PathVariable String userName){
		User user = userRepository.findByUserName(userName);
		return followersRespository.findFollowersById(user.getId()).size();
	}
	
	
	@GetMapping("/following/{userName}")
	public int followingForUser(@PathVariable String userName){	
		User user = userRepository.findByUserName(userName);
		return followersRespository.findFollowingById(user.getId()).size();
	}
	
	@GetMapping("/user/follow/available/{userName}")
	public ResponseEntity<ResponseObjectService> getUsersAvailableForFollow(@PathVariable String userName) {
		ResponseObjectService responseObj = new ResponseObjectService();
		logger.debug("userName is ====>{}",userName);
		try {
			List<UserFollowInfo> userFollowInfo = new ArrayList<>();
			User user = userRepository.findByUserName(userName);
			logger.debug("user is ====>{}",user);
			List<User> users = userRepository.findAll();
			logger.debug("users is ====>{}",users);
			
			if(user != null && users != null) {
				List<Long> alreadyFollowingList = followersRespository.findFollowingById(user.getId());
				logger.debug("alreadyFollowingList is ====>{}",alreadyFollowingList);
				logger.debug("alreadyFollowingList after is ====>{}",alreadyFollowingList);
				
				for(User userList : users) {
					if(!user.getId().equals(userList.getId())) {
						UserFollowInfo userInfo = new UserFollowInfo();
						userInfo.setUserId(userList.getId());
						userInfo.setUserName(userList.getUserName());
						userInfo.setEmailId(userList.getEmailId());
						if(alreadyFollowingList.contains(userList.getId())) {
							userInfo.setFollowing(true);
						}
						int followersCount = followersRespository.findFollowersById(userList.getId()).size();
						userInfo.setFollowersCount(followersCount > 0 ? String.valueOf(followersCount) : "0");
						userFollowInfo.add(userInfo);
					}
				}
				responseObj.setStatus("success");
				responseObj.setMessage("success");
				responseObj.setPayload(userFollowInfo);
				logger.debug("UserFollowInfo list is ====>{}",userFollowInfo);
			}else {
				throw new Exception("FAILED");
			}
		} catch (Exception e) {
			responseObj.setStatus("fail");
            responseObj.setMessage("cannot find available users list : " + userName);
            responseObj.setPayload(null);
		}
		logger.debug("ResponseObjectService is ====>{}",responseObj);
		return new ResponseEntity<ResponseObjectService>(responseObj, HttpStatus.OK);
	}
	

}
