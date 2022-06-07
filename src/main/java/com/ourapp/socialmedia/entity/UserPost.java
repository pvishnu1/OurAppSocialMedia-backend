package com.ourapp.socialmedia.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="TBL_USER_POST")
public class UserPost implements Serializable {

	private static final long serialVersionUID = 4887904943282174032L;
	
	@Id
    @Column(name = "post_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "user_post_id_seq")
	@SequenceGenerator(name = "user_post_id_seq", sequenceName = "USER_POST_ID_SEQ")
    private Long id;
	
    private String postTitle;
    
    private String postDescription;
    
    private String postCreationDate;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
	private User user;
	
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private String userName;
}
