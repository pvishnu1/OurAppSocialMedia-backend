package com.ourapp.socialmedia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ourapp.socialmedia.entity.User;
import com.ourapp.socialmedia.interfaces.IUserRegistartionService;
import com.ourapp.socialmedia.jwt.JwtUtil;
import com.ourapp.socialmedia.view.JwtResponse;
import com.ourapp.socialmedia.view.LoginRequestView;


@RestController
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
    private JwtUtil jwtUtil;
	
	@Autowired
	private IUserRegistartionService userRegistartionService;
	
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @PostMapping("/ourApp/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
    	try {
    		logger.debug("UserController register user Details {}",user);
            return ResponseEntity.ok(userRegistartionService.registerUser(user));
        } catch (Exception e) {
        	logger.error("Exception in register {}",e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/ourApp/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequestView loginRequestView) {
        try {
        	logger.debug("UserController login user Details {}",loginRequestView);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestView.getUserName(), loginRequestView.getPassword()));
            logger.debug("UserController login authentication {}",authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtil.generateToken(loginRequestView.getUserName());
            User user = userRegistartionService.findUser(loginRequestView.getUserName());
            return ResponseEntity.ok(new JwtResponse(jwt, user.getEmailId(), user.getUserName()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
    

    @PostMapping("/ourApp/authenticate")
    public String generateToken(@RequestBody LoginRequestView loginRequestView) throws Exception {
    	logger.debug("loginRequestView {}",loginRequestView);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestView.getUserName(), loginRequestView.getPassword())
            );
        } catch (Exception ex) {
        	logger.error("Exception in authenticate {}",ex.getMessage());
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(loginRequestView.getUserName());
    }
    
    @GetMapping("/ourApp/getAllUsers/{userName}")
    public User getUsers(@PathVariable String userName) throws Exception {
    	logger.debug("getUsers is: {}",userName);
    	User user = userRegistartionService.findUser(userName);
        return user; 
    }
	
}
