package com.leone.boot.mybatis.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author leone
 * @since 2024-12-02
 */
@Data
@Table("user2")
public class User2 implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id(value = "id", keyType = KeyType.Auto)
    private Long userId;

    private String username;

    private String password;

    private Integer gender;

    private String phone;

    private LocalDateTime createTime;

    private Integer deleted;

}
