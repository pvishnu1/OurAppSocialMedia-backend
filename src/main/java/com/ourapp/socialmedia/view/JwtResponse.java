package com.ourapp.socialmedia.view;

import java.util.List;

import com.ourapp.socialmedia.entity.UserPost;

import lombok.Data;


@Data
public class JwtResponse {
	
    private String token;
    private String type = "Bearer";
    private String emailId;
    private String userName;
    
    private List<UserPost> userPosts;

	public JwtResponse(String token,String emailId, String userName, List<UserPost> userPosts) {
		super();
		this.token = token;
		this.emailId = emailId;
		this.userName = userName;
		this.userPosts = userPosts;
	}

}
