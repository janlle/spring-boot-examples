package com.andy.shiro.mapper;

import com.andy.shiro.entity.rbac.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Leone
 * @since: 2018-04-21 15:58
 **/
@Mapper
public interface UserMapper {

    User getByAccount(@Param("account") String account);

}
