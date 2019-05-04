package com.leone.jpa.repository;

import com.leone.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author leone
 * @since 2018-05-11
 **/
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Modifying
    @Query(value = "select * from t_user where account = ?1", nativeQuery = true)
    User findUserByAccount(@Param("account") String account);

    @Modifying
    @Query("update User set deleted = 1 where userId in ?1")
    Integer delByIds(List<Integer> ids);

}
