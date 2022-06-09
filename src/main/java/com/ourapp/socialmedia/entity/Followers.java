package com.ourapp.socialmedia.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="TBL_FOLLOWERS")
public class Followers implements Serializable {

	private static final long serialVersionUID = 4887904943282174032L;
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "follower_id_seq")
	@SequenceGenerator(name = "follower_id_seq", sequenceName = "FOLLOWERID_SEQ")
    private Long id;
	
	@Column(name="follower_Id")
	private Long followerId;
	
	@Column(name = "following_Id")
	private Long followingId;

	public Followers(Long follower, Long following) {
		this.followerId = follower;
		this.followingId = following;
	}
	
}
