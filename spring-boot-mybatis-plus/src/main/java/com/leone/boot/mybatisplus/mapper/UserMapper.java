package com.leone.boot.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leone.boot.mybatisplus.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-13
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
