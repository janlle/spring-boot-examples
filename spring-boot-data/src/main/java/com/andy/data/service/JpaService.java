package com.andy.data.service;

import com.andy.data.entity.User;
import com.andy.data.repository.jpa.repository.UserRepository;
import com.andy.data.util.EntityFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 *
 * @author Leone
 * @since 2018-05-24
 **/
@Slf4j
@Service
public class JpaService {

    @Autowired
    private UserRepository userRepository;

    /**
     * @param count
     * @return
     */
    public long insertBatch(Integer count) {
        List<User> list = EntityFactory.getUsers(count);
        userRepository.saveAll(list);
        return 0;
    }

    /**
     * @param count
     * @return
     */
    public long insertForeach(Integer count) {
        for (long i = 0; i < count; i++) {
            userRepository.save(EntityFactory.getUser());
        }
        return 0;
    }


    @Transactional
    public int update(User user) {
        User entity = userRepository.findById(user.getUserId()).orElse(null);
        BeanUtils.copyProperties(user, entity);
        userRepository.save(user);
        int i = 100 / 0;
        return 0;
    }
}
