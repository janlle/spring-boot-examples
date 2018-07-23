package com.andy.flux.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@Document(collection = "user")
public class User implements Serializable {

    @Id
    private String id;

    private String email;

    private String password;

    private Integer age;


    public User(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

}
