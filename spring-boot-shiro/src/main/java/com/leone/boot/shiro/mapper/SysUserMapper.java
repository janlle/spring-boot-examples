package com.leone.boot.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leone.boot.shiro.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser findAllPermissionByAccount(@Param("account") String account);

    @Select("select * from sys_user where account = #{account} and status = 1")
    SysUser findUserByAccount(@Param("account") String account);

    @Select("select * from sys_user where account = #{account} and password = #{password} and status = 1")
    SysUser login(@Param("account") String account, @Param("password") String password);

}