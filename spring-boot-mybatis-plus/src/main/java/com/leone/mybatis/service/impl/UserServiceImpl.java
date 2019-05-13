package com.leone.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leone.mybatis.entity.User;
import com.leone.mybatis.mapper.UserMapper;
import com.leone.mybatis.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-13
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


}
