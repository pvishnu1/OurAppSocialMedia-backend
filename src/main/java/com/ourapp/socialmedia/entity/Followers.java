package com.ourapp.socialmedia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="TBL_FOLLOWERS")
public class Followers {

	@Id
    @Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	private Long followerId;
	
	private Long followingId;
	

	public Followers(Long follower, Long following) {
		this.followerId = follower;
		this.followingId = following;
	}


}
