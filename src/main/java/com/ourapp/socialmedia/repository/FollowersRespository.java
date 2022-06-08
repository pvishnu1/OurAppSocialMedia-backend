package com.ourapp.socialmedia.repository;

import com.ourapp.socialmedia.entity.Followers;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface FollowersRespository extends JpaRepository<Followers, Long> {

	Followers save(Followers entity);

}
