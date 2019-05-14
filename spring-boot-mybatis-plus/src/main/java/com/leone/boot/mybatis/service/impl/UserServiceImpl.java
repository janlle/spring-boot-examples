package com.leone.boot.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leone.boot.mybatis.entity.User;
import com.leone.boot.mybatis.mapper.UserMapper;
import com.leone.boot.mybatis.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-13
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


}
