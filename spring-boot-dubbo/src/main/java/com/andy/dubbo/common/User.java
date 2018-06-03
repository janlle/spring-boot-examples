package com.andy.dubbo.common;

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

    private String password;

    private String email;

    private double salary;

    private Date birthday;

    private String token;

}