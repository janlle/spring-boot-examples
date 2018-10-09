package com.andy.batch.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-08
 **/
public class User implements Serializable {

    private Long userId;

    private String account;

    private String password;

    private Integer age;

    private Date createTime;

    private boolean deleted;

}
