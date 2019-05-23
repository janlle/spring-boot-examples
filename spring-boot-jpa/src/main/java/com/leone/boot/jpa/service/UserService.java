package com.leone.boot.jpa.service;

import com.leone.boot.jpa.entity.User;
import com.leone.boot.jpa.pojo.UserQueryVO;
import com.leone.boot.jpa.pojo.UserVO;
import com.leone.boot.jpa.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 *
 * @author leone
 * @since 2018-05-24
 **/
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * @param user
     * @return
     */
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * @param user
     */
    public User update(User user) {
        return userRepository.save(user);
    }

    /**
     * @param userId
     */
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * @return
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * 分页
     *
     * @param pageable
     * @param query
     * @return
     */
    public Page<UserVO> page(Pageable pageable, UserQueryVO query) {
        Specification<User> specification = (Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();

            if (!ObjectUtils.isEmpty(query.getUserId())) {
                list.add(criteriaBuilder.equal(root.get("userId").as(Integer.class), query.getUserId()));
            }

            if (!ObjectUtils.isEmpty(query.getAccount())) {
                list.add(criteriaBuilder.equal(root.get("account").as(String.class), query.getAccount()));

            }

            if (!StringUtils.isEmpty(query.getDescription())) {
                list.add(criteriaBuilder.like(root.get("description").as(String.class), "%" + query.getDescription() + "%"));
            }

            if (!StringUtils.isEmpty(query.getAge())) {
                list.add(criteriaBuilder.equal(root.get("age").as(Integer.class), query.getAge()));
            }

            Predicate[] predicates = new Predicate[list.size()];
            criteriaQuery.where(list.toArray(predicates));
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get("goodsId").as(Integer.class)));
            return criteriaQuery.getRestriction();
        };
        return userRepository.findAll(specification, pageable).map(this::toUserVO);
    }

    /**
     * converter
     *
     * @param user
     * @return
     */
    private UserVO toUserVO(User user) {
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(user, vo);
        return vo;
    }

    /**
     *
     * @param userId
     * @return
     */
    public User findOne(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
