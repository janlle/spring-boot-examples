package com.leone.boot.data.jpa.repository;

import com.leone.boot.data.jpa.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author leone
 * @since 2018-05-11
 **/
public interface UserAddressRepository extends JpaRepository<UserAddress, Long>, JpaSpecificationExecutor<UserAddress> {

    @Modifying
    @Query(value = "select * from t_user where account = ?1", nativeQuery = true)
    UserAddress findUserByAccount(String account);

    @Modifying
    @Query("update UserAddress set deleted = 1 where id in ?1")
    Integer delByIds(List<Long> ids);

}
