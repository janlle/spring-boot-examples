package com.andy.data.service;

import com.andy.data.entity.User;
import com.andy.data.jpa.repository.UserRepository;
import com.andy.data.util.EntityFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class JpaService {

    @Autowired
    private UserRepository userRepository;

    public long insertList(Integer count) {
        List<User> list = new ArrayList<>();
        for (long i = 0; i < count; i++) {
            list.add(EntityFactory.getUsers(1).get(0));
        }
        long start = System.currentTimeMillis();
        userRepository.saveAll(list);
        long end = System.currentTimeMillis();
        return (end - start);
    }

    public long insertForeach(Integer count) {
        long start = System.currentTimeMillis();
        for (long i = 0; i < count; i++) {
            userRepository.save(EntityFactory.getUsers(1).get(0));
        }
        long end = System.currentTimeMillis();
        return (end - start);
    }


    @Transactional
    public int update(User user) {
        User entity = userRepository.findById(user.getUserId()).orElse(null);
        BeanUtils.copyProperties(user, entity);
        userRepository.save(user);
        throw new RuntimeException("发生异常");
    }
}
