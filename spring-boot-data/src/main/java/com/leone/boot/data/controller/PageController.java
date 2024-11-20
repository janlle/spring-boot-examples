package com.leone.boot.data.controller;

import com.leone.boot.common.response.PageResponse;
import com.leone.boot.data.mybatis.entity.TradeOrder;
import com.leone.boot.data.mybatis.response.OrderDetailResp;
import com.leone.boot.data.service.TradeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class PageController {

    @Autowired
    private TradeOrderService tradeOrderService;

    @GetMapping("/native-page")
    public PageResponse<TradeOrder> nativePage(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                               @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        return tradeOrderService.nativePage(page, size);
    }

    @GetMapping("/bound-page")
    public PageResponse<TradeOrder> boundPage(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                              @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        return tradeOrderService.rowBoundPage(page, size);
    }

    @GetMapping("/page-help")
    public PageResponse<TradeOrder> pageHelp(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                             @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        return tradeOrderService.pageHelp(page, size);
    }

    @GetMapping("/lazy-load")
    public List<OrderDetailResp> lazyLoad() {
        tradeOrderService.lazyLoad();
        return null;
    }

}
