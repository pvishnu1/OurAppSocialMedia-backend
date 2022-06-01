package com.ourapp.socialmedia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TBL_CUSTOMERS")
public class Customer {
	
	@Id
    @Column(name = "customer_id")
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;
    private String name;
    private String email;
    
}
