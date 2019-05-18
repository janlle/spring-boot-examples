package com.leone.boot.shiro;

import com.leone.boot.shiro.entity.User;
import com.leone.boot.shiro.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-18
 **/
@SpringBootTest(classes = {ShiroApplication.class})
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void login() {
        User user = userMapper.login("jack", "jack");
        System.out.println(user);
    }

}
