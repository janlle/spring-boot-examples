package com.andy.data.jpa.repository;

import com.andy.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-05-11 18:47
 **/
public interface UserRepository extends JpaRepository<User, Long> {

}
