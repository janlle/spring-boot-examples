package com.leone.boot.shiro;

import com.leone.boot.shiro.entity.SysUser;
import com.leone.boot.shiro.mapper.SysUserMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-18
 **/
@SpringBootTest(classes = {ShiroApplication.class})

public class UserMapperTest {

    @Autowired
    private SysUserMapper userMapper;

    
    public void login() {
        SysUser user = userMapper.login("jack", "jack");
        System.out.println(user);
    }

}
