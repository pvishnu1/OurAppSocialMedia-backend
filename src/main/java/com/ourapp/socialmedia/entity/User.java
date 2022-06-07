package com.ourapp.socialmedia.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="TBL_USER")
public class User implements Serializable {

	private static final long serialVersionUID = 4887904943282174032L;
	
	@Id
    @Column(name = "user_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "user_id_seq")
	@SequenceGenerator(name = "user_id_seq", sequenceName = "USERID_SEQ")
    private Long id;
	
	@Column(unique=true)
    private String userName;
   
    @Column(unique=true)
    private String emailId;
    
    private String password;
    
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	@JsonIgnore
	private List<UserPost> userPosts;
	
}
