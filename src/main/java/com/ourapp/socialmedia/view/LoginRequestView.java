package com.ourapp.socialmedia.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginRequestView {

	@JsonProperty("userName")
    private String userName;
	
	@JsonProperty("password")
    private String password;
}
