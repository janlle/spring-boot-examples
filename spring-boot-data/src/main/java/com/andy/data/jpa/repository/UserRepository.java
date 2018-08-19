package com.andy.data.jpa.repository;

import com.andy.data.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Leone
 * @since: 2018-05-11
 **/
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Modifying
    @Query(value = "select * from t_user where id = ?1", nativeQuery = true)
    User findUserByUserId(@Param("userId") long userId);

//    @Modifying
//    @Query("update User set username = :user.username")
//    int updateUser(@Param("user") User user);

    @Modifying
//    @Query("delete from User where id = ?1")
    @Query("delete from User where id = :userId")
    int deleteUser(@Param("userId") Long userId);

}
