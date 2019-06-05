package com.leone.boot.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leone.boot.mybatisplus.entity.User;
import com.leone.boot.mybatisplus.mapper.UserMapper;
import com.leone.boot.mybatisplus.service.UserService;
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
