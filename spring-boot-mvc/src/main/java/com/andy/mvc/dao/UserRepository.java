package com.andy.mvc.dao;

import com.andy.mvc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: Leone
 * @since: 2018-04-10 11:06
 **/
public interface UserRepository extends JpaRepository<User, Long> {




}
