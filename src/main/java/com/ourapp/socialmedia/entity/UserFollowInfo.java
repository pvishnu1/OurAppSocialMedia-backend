package com.ourapp.socialmedia.entity;

import lombok.Data;

@Data
public class UserFollowInfo {
	
	private Long userId;
	
	private String userName;
	
	private boolean isFollowing;
	
	private String emailId;
	
	private String followersCount;

}
