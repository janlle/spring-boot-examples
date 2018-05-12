package com.andy.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_user")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String username;

	private String password;
	
	private String email;

	private Double salary;

	private Date birthday;

	public User(long id, String username, String email, Double salary, Date birthday) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.salary = salary;
		this.birthday = birthday;
	}
}
