package com.ourapp.socialmedia.view;

import lombok.Data;


@Data
public class JwtResponse {
	
    private String token;
    private String type = "Bearer";
    private String account;
    private String name;

    public JwtResponse(String token, String account, String name) {
        this.account = account;
        this.name = name;
        this.token = token;
    }
}
