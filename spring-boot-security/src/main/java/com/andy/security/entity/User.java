package com.andy.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_user")
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;

	private String username;
	
	private String email;

	private Double salary;

	private Date birthday;

}
