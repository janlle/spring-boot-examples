package com.leone.boot.mybatisplus.service.impl;

import com.leone.boot.mybatisplus.entity.Goods;
import com.leone.boot.mybatisplus.mapper.GoodsMapper;
import com.leone.boot.mybatisplus.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author leone
 * @since 2024-12-02
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

}
