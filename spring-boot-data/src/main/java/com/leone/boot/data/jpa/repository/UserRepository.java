package com.leone.boot.data.jpa.repository;

import com.leone.boot.data.jpa.entity.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author leone
 * @since 2018-05-11
 **/
public interface UserRepository extends JpaRepository<JpaUser, Long>, JpaSpecificationExecutor<JpaUser> {

    @Modifying
    @Query(value = "select * from t_user where account = ?1", nativeQuery = true)
    JpaUser findUserByAccount(String account);

    //@Modifying
    //@Query("update JpaUser set deleted = 1 where userId in ?1")
    //Integer delByIds(List<Long> ids);

}
