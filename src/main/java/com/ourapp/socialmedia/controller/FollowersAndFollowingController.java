package com.ourapp.socialmedia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ourapp.socialmedia.entity.FollowData;
import com.ourapp.socialmedia.entity.Followers;
import com.ourapp.socialmedia.repository.FollowersRespository;

@RestController
@RequestMapping("/ourApp/follow")
public class FollowersAndFollowingController {
	
	@Autowired
	FollowersRespository followersRespository;

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	
	@PostMapping
	public ResponseEntity<String> followUser(@RequestBody FollowData followdata) {
		try {
	
			followersRespository.save(new Followers(followdata.getFollower(), followdata.getFollowing()));
	       return ResponseEntity.ok("followed ");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage()); 
		}		
	}
	
	

}
