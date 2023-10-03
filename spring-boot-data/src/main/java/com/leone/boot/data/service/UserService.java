package com.leone.boot.data.service;

import com.leone.boot.common.entity.User;
import com.leone.boot.data.repository.jpa.UserRepository;
import com.leone.boot.data.repository.mybatis.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author leone
 * @since 2018-07-08
 **/
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private UserMapper userMapper;


    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 查询所用
     *
     * @return
     */
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * 根据主键查找
     *
     * @param userId
     * @return
     */
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    /**
     * 修改
     *
     * @param user
     * @return
     */
    public User update(User user) {
        return userRepository.save(user);
    }

    /**
     * 删除
     *
     * @param userId
     */
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * 分页
     *
     * @param pageable
     * @param description
     * @return
     */
    public Page<User> page(Pageable pageable, String description, Integer account) {
        Specification<User> specification = (Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();

            list.add(criteriaBuilder.equal(root.get("deleted").as(Integer.class), 0));

            if (!StringUtils.isEmpty(description)) {
                list.add(criteriaBuilder.like(root.get("description"), "%" + description + "%"));
            }

            if (!StringUtils.isEmpty(account)) {
                list.add(criteriaBuilder.equal(root.get("account").as(String.class), account));
            }

            Predicate[] predicates = new Predicate[list.size()];
            criteriaQuery.where(list.toArray(predicates));
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get("userId")));
            return criteriaQuery.getRestriction();
        };
        return userRepository.findAll(specification, pageable);
    }

}
