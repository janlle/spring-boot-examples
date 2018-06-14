package com.andy.jms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

	private long id;

	private String username;
	
	private String email;

	private Double salary;

	private Date birthday;

}
