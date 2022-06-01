package com.ourapp.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourapp.socialmedia.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserName(String username);
}
