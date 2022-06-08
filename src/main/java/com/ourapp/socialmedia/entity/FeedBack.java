package com.ourapp.socialmedia.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="TBL_FeedBack")
public class FeedBack implements Serializable {

	private static final long serialVersionUID = 4887904943282174032L;
	
	@Id
    @Column(name = "feedBack_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "feedBack_id_seq")
	@SequenceGenerator(name = "feedBack_id_seq", sequenceName = "feedBack_id_SEQ")
    private Long id;
	
	@Column
    private String emailId;
   
    @Column
    private String subject;
    
    @Column
    private String description;
}
