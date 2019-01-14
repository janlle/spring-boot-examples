package com.andy.data.repository.jpa;

import com.andy.data.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Leone
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
