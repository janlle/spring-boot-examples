package com.andy.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	@Column(columnDefinition = "varchar(128) NOT NULL COMMENT '账号'")
	private String account;

	@Column(columnDefinition = "varchar(128) NOT NULL COMMENT '密码'")
	private String password;

	@Column(columnDefinition = "datetime NOT NULL COMMENT '生日'")
	private Date birthday;

	@Column(columnDefinition = "double NOT NULL COMMENT '工资'")
	private Double salary;

	@CreatedDate
	@Column(columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
	private Date createTime;

	@Column(columnDefinition = "bit NOT NULL COMMENT '是否删除'")
	private Boolean deleted;

}
