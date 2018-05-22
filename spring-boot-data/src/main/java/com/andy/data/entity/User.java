package com.andy.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_user")
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", birthday=" + birthday +
				", email='" + email + '\'' +
				", salary=" + salary +
				", token='" + token + '\'' +
				'}';
	}
}
