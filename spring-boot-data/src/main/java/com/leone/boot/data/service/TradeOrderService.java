package com.leone.boot.data.service;


import cn.hutool.core.date.DateField;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leone.boot.common.response.PageResponse;
import com.leone.boot.data.mybatis.entity.TradeOrder;
import com.leone.boot.data.mybatis.mapper.TradeOrderMapper;
import com.leone.boot.data.mybatis.response.OrderDetailResp;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p> 分页
 *
 * @author leone
 * @since 2024-11-20
 **/
@Slf4j
@Service
public class TradeOrderService {

    @PostConstruct
    public void init() {
        long count = tradeOrderMapper.count();
        if (count < 100) {
            for (int i = 0; i < 100; i++) {
                TradeOrder order = new TradeOrder();
                order.setUserId(IdUtil.nanoId(8));
                order.setTradeOrderId(IdUtil.nanoId(8));
                order.setUserAddressId(IdUtil.nanoId(8));
                order.setItemCount(RandomUtil.randomInt(1, 9));
                order.setPayTime(RandomUtil.randomDate(null, DateField.HOUR, 1, 9999));
                order.setFinishTime(RandomUtil.randomDate(null, DateField.HOUR, 1, 9999));
                order.setPaidAmount(RandomUtil.randomBigDecimal(new BigDecimal("10000")));
                order.setState(1);
                order.setPayChannel(2);
                order.setDeleted(0);
                order.setLockVersion(0);
                order.setGmtCreate(new Date());
                order.setGmtModified(new Date());
                tradeOrderMapper.insert(order);
            }
        }
    }

    @Autowired
    private TradeOrderMapper tradeOrderMapper;

    @Autowired
    private SqlSession sqlSession;


    /**
     * 原始分页,物理分页
     *
     * @param page 页码
     * @param size 大小
     */
    public PageResponse<TradeOrder> nativePage(int page, int size) {
        List<TradeOrder> data = tradeOrderMapper.selectByLimit((page - 1) * size, size);
        int count = tradeOrderMapper.count();
        PageResponse<TradeOrder> result = new PageResponse<>();
        result.setPageSize(size);
        result.setCurrentPage(page);
        result.setData(data);
        result.setTotal(count);
        result.setTotalPage(count / size);
        result.setSuccess(true);
        result.setResponseMessage("success");
        result.setResponseCode("200");
        return result;
    }

    /**
     * rowBounds分页 逻辑分页
     *
     * @param page 页码
     * @param size 大小
     */
    public PageResponse<TradeOrder> rowBoundPage(int page, int size) {
        // 计算从第几行读取记录
        int start = (page - 1) * size;
        RowBounds rowBounds = new RowBounds(start, size);
        List<TradeOrder> data = tradeOrderMapper.selectByRowBound(rowBounds);

        //TradeOrderMapper mapper = sqlSession.getMapper(TradeOrderMapper.class);
        //List<TradeOrder> tradeOrders = mapper.selectByRowBound(rowBounds);

        PageResponse<TradeOrder> result = new PageResponse<>();
        result.setPageSize(size);
        result.setCurrentPage(page);
        result.setData(data);
        result.setTotal(data.size());
        result.setTotalPage(data.size() / size);
        result.setSuccess(true);
        result.setResponseMessage("success");
        result.setResponseCode("200");
        return result;
    }

    /**
     * pageHelp分页 逻辑分页
     *
     * @param page 页码
     * @param size 大小
     */
    public PageResponse<TradeOrder> pageHelp(int page, int size) {
        // 计算从第几行读取记录
        PageHelper.startPage(page, size);
        PageInfo<TradeOrder> pageInfo = new PageInfo<>(tradeOrderMapper.selectList());

        PageResponse<TradeOrder> result = new PageResponse<>();
        result.setPageSize(size);
        result.setCurrentPage(page);
        result.setData(pageInfo.getList());
        result.setTotal((int) pageInfo.getTotal());
        result.setTotalPage(pageInfo.getPages());
        result.setSuccess(true);
        result.setResponseMessage("success");
        result.setResponseCode("200");
        return result;
    }

    public List<OrderDetailResp> lazyLoad() {
        return tradeOrderMapper.selectOrderDetail();
    }


}
