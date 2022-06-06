package com.ourapp.socialmedia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourapp.socialmedia.entity.User;
import com.ourapp.socialmedia.entity.UserPost;

public interface UserPostRepository extends JpaRepository<UserPost, Integer>{

	List<UserPost> findByUser(User user);
}
