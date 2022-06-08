package com.ourapp.socialmedia.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class FollowData {
	
	private Long follower;
	
	private Long following;

}
