package com.andy.data.test;

import com.andy.data.DataApplication;
import com.andy.data.entity.User;
import com.andy.data.jpa.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @author: lyon
 * @since: 2018-05-21 21:20
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataApplication.class)
public class UserDaoTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAdd() {
        User user = new User();
        user.setAccount("andy@163.com");
        user.setPassword("andyPassword");
        User result = userRepository.save(user);
        log.info("user:", result);

    }


    @Test
    public void testFind() {
//        User user = userRepository.findOne(1L);
//        User user = userRepository.getOne(2L);
        User user = userRepository.findUserByUserId(4);
        log.info("user:", user);
    }

    @Test
    public void testDelete() {
//        userRepository.delete(3L);
        userRepository.deleteUser(2L);
    }


//    @Test
//    public void testSelect() {
//        int user = userRepository.findUserByUserId(1L);
//        log.info("user:", user);
//    }














}
