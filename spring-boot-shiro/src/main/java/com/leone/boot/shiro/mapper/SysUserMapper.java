package com.leone.boot.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leone.boot.shiro.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser findAllPermissionByAccount(@Param("userId") String userId);

    @Select("select * from sys_user where user_id = #{userId} and user_status = 1 limit 1")
    SysUser findUserByAccount(@Param("userId") String userId);

    @Select("select * from sys_user where user_id = #{userId} and password = #{password} and status = 1 limit 1")
    SysUser login(@Param("userId") String account, @Param("password") String password);

}