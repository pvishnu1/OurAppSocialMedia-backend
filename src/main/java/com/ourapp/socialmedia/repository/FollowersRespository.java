package com.ourapp.socialmedia.repository;

import com.ourapp.socialmedia.entity.Followers;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
public interface FollowersRespository extends JpaRepository<Followers, Long> {

	@SuppressWarnings("unchecked")
	Followers save(Followers entity);
	
	@Query(value = "select distinct(following_id) from TBL_FOLLOWERS where follower_id = ?1", nativeQuery = true)
	List<Long> findFollowingById(Long id);
	
	@Query(value = "select distinct(follower_id) from TBL_FOLLOWERS where following_id = ?1", nativeQuery = true)
	List<Long> findFollowersById(Long id);
	
	@Query(value = "select distinct(follower_id) from TBL_FOLLOWERS where following_id != ?1", nativeQuery = true)
	List<Long> findFollowingList(Long id);
	
	@Transactional
	@Modifying
	@Query(value = "delete from TBL_FOLLOWERS where  follower_id = ?1 and following_id = ?2", nativeQuery = true)
	void unFollowUser(Long followingId, Long followerId);
}
