package com.leone.boot.data.service;

import com.leone.boot.common.entity.User;
import com.leone.boot.common.util.EntityFactory;
import com.leone.boot.data.repository.jpa.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 *
 * @author leone
 * @since 2018-05-24
 **/
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
