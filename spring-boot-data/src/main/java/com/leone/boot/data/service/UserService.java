package com.leone.boot.data.service;

import com.leone.boot.common.entity.User;
import com.leone.boot.common.util.EntityFactory;
import com.leone.boot.data.repository.jpa.UserRepository;
import com.leone.boot.data.repository.mybatis.UserMapper;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leone
 * @since 2018-07-08
 **/
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private UserMapper userMapper;


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    public Page<User> page(Pageable pageable, String description, Integer account) {
        Specification<User> specification = (Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();

            list.add(criteriaBuilder.equal(root.get("deleted").as(Integer.class), 0));

            if (!ObjectUtils.isEmpty(description)) {
                list.add(criteriaBuilder.like(root.get("description"), "%" + description + "%"));
            }

            if (!ObjectUtils.isEmpty(account)) {
                list.add(criteriaBuilder.equal(root.get("account").as(String.class), account));
            }

            Predicate[] predicates = new Predicate[list.size()];
            criteriaQuery.where(list.toArray(predicates));
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get("userId")));
            return criteriaQuery.getRestriction();
        };
        return userRepository.findAll(specification, pageable);
    }

    public long jpaInsertBatch(Integer count) {
        List<User> list = EntityFactory.getUsers(count);
        userRepository.saveAll(list);
        return 0;
    }

    public long jpaInsertForeach(Integer count) {
        for (long i = 0; i < count; i++) {
            userRepository.save(EntityFactory.getUser());
        }
        return 0;
    }

    @Transactional
    public int jpaUpdate(User user) {
        User entity = userRepository.findById(user.getUserId()).orElse(null);
        BeanUtils.copyProperties(user, entity);
        userRepository.save(user);
        int i = 100 / 0;
        return 0;
    }


    public long mybatisInsertBatch(Integer count) {
        if (count < 1000) {
            return userMapper.insertBatch(EntityFactory.getUsers(count));
        } else {
            int time = 0;
            EntityFactory.getUsers(100);
            int a = count % 1000;
            int b = count / 1000;
            for (int i = 0; i < b; i++) {
                time += userMapper.insertBatch(EntityFactory.getUsers(1000));
            }

            if (a == 0) {
                return time;
            }
            return time + userMapper.insertBatch(EntityFactory.getUsers(a));
        }
    }

    public long mybatisInsertForeach(Integer count) {
        for (long i = 0; i < count; i++) {
            userMapper.insert(EntityFactory.getUser());
        }
        return count;
    }

    @Transactional
    public long mybatisUpdate(User user) {
        Integer result = userMapper.updateById(user);
        int i = 100 / 0;
        return 0;
    }

}
