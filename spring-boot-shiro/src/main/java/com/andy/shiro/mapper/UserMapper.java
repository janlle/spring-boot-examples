package com.andy.shiro.mapper;

import com.andy.shiro.entity.rbac.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-04-21 15:58
 **/
public interface UserMapper {

    User findByUsername(@Param("username") String username);

}
