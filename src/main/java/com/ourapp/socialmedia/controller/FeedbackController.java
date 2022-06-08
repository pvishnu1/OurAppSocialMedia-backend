package com.ourapp.socialmedia.controller;

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

import com.ourapp.socialmedia.entity.FeedBack;
import com.ourapp.socialmedia.repository.FeedBackRepository;
import com.ourapp.socialmedia.view.ResponseObjectService;

@RestController
@CrossOrigin
@RequestMapping("/ourApp/feedback")
public class FeedbackController {

	private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);
	
	@Autowired
	FeedBackRepository feedBackRepo;
	
	 @PostMapping("/register")
    public ResponseEntity<ResponseObjectService> registerFeedBack(@RequestBody FeedBack feedBack) {
		 ResponseObjectService responseObj = new ResponseObjectService();
		 
    	try {
    		logger.debug("FeedbackController registerFeedBack Details {}",feedBack);
            FeedBack feed = feedBackRepo.save(feedBack);
            responseObj.setStatus("success");
            responseObj.setMessage("success");
            responseObj.setPayload(feed);
            
        } catch (Exception e) {
        	logger.error("Exception in register {}",e.getMessage());
        	responseObj.setStatus("fail");
            responseObj.setMessage("cannot store feedBackNow");
        }
    	return new ResponseEntity<ResponseObjectService>(responseObj, HttpStatus.OK);
    }
}
