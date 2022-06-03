package com.ourapp.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourapp.socialmedia.entity.UserPost;

public interface UserPostRepository extends JpaRepository<UserPost, Integer>{

	
}
