package com.leone.data.test;

import com.leone.boot.data.DataApplication;
import com.leone.boot.data.jpa.entity.UserAddress;
import com.leone.boot.data.jpa.repository.UserAddressRepository;
import lombok.extern.slf4j.Slf4j;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author leone
 * @since 2018-05-21
 **/
@Slf4j
@SpringBootTest(classes = DataApplication.class)
public class UserDaoTest {

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Test
    public void save() {
        UserAddress userAddress = new UserAddress();
        userAddress.setTelephone("andy@163.com");
        userAddress.setSpecificsAddress("andyPassword");
        UserAddress result = userAddressRepository.save(userAddress);
        log.info("save userAddress: {}", result);
    }


    
    public void testFind() {
//        User user = userRepository.findOne(1L);
//        User user = userRepository.getOne(2L);
//        User user = userRepository.findUserByUserId(4);
//        log.info("user:", user);
    }

    
    public void testDelete() {
//        userRepository.delete(3L);
//        userRepository.deleteUser(2L);
    }


//    
//    public void testSelect() {
//        int user = userRepository.findUserByUserId(1L);
//        log.info("user:", user);
//    }


}
