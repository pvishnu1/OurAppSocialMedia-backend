package com.ourapp.socialmedia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ourapp.socialmedia.entity.User;
import com.ourapp.socialmedia.entity.UserPost;
import com.ourapp.socialmedia.interfaces.IUserRegistartionService;
import com.ourapp.socialmedia.repository.UserPostRepository;
import com.ourapp.socialmedia.view.PostRequestView;
import com.ourapp.socialmedia.view.ResponseObjectService;

@RestController
@CrossOrigin
@RequestMapping("/ourApp/post")
public class PostController {

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	
	@Autowired
	UserPostRepository userPostRepository;
	
	@Autowired
	private IUserRegistartionService userRegistartionService;

	@PostMapping("/createPost")
    public ResponseEntity<UserPost> registerUser(@RequestBody PostRequestView postRequestView) {
		logger.debug("PostController create post Details {}",postRequestView);
		try {
    		User user = userRegistartionService.findUser(postRequestView.getUserName());
    		//logger.debug("PostController user Details {}",user);
    		if(user != null) {
    			UserPost userPost = new UserPost();
    			userPost.setPostTitle(postRequestView.getPostTitle());
    			userPost.setPostDescription(postRequestView.getPostDescription());
    			userPost.setUser(user);
    			//logger.debug("PostController UserPost Details {}",userPost);
    			return ResponseEntity.ok(userPostRepository.save(userPost));
    		}else {
    			throw new Exception("User Not Found");
    		}
        } catch (Exception e) {
        	logger.error("Exception in creating post {}",e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
	
	
	@PostMapping("/myPosts")
    public ResponseEntity<ResponseObjectService> getPostsByUser(@RequestBody PostRequestView postRequestView) throws Exception {
		logger.debug("PostController getPostsByUser Details {}",postRequestView.getUserName());
		ResponseObjectService responseObj = new ResponseObjectService();
    		User user = userRegistartionService.findUser(postRequestView.getUserName());
    		logger.debug("PostController getPostsByUser user Details {}",user);
    		if(user != null) {
    			List<UserPost> userPostsOpt = userPostRepository.findByUser(user);
	            responseObj.setStatus("success");
	            responseObj.setMessage("success");
	            responseObj.setPayload(userPostsOpt);
    		}else {
    			responseObj.setStatus("fail");
                responseObj.setMessage("cannot find any post from user : " + postRequestView.getUserName());
                responseObj.setPayload(null);
    		}
    		return new ResponseEntity<ResponseObjectService>(responseObj, HttpStatus.OK);
    }
	
}
