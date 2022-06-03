package com.ourapp.socialmedia.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PostRequestView {

	@JsonProperty("postTitle")
    private String postTitle;
	
	@JsonProperty("postDescription")
    private String postDescription;
	
	@JsonProperty("userName")
    private String userName;
	
}
