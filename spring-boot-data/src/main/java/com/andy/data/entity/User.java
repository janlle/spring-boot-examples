package com.andy.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_user")
public class User implements Serializable {

	private static final long serialVersionUID = 7818773956573496180L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String username;

	private String password;

	private Date birthday;
	
	private String email;

	private Double salary;

	private String token;

	public User() {
	}

	public User(Long id, String username, String password, Date birthday, String email, Double salary, String token) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.birthday = birthday;
		this.email = email;
		this.salary = salary;
		this.token = token;
	}

}
