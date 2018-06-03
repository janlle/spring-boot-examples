package com.andy.pay.web.controller;

import com.andy.pay.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@Controller
@Api(tags ="订单操作接口")
@RequestMapping(value = "/product")
public class ProductController {


//    @GetMapping("/list")
//    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
//                             @RequestParam(value = "size", defaultValue = "10") Integer size,
//                             Map<String, Object> map){
//        PageRequest pageRequest = new PageRequest(page - 1, size);
//        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
//        map.put("productInfoPage", productInfoPage);
//        map.put("currentPage", page);
//        map.put("size", size);
//        return new ModelAndView("product/list", map);
//    }
//
//    @GetMapping("/off_sale")
//    public ModelAndView offSale(@RequestParam("productId") String productId,
//                                Map<String, Object> map) {
//        try {
//            productService.offSale(productId);
//        } catch (AppException e) {
//            map.put("msg", e.getMessage());
//            map.put("url", "/sell/seller/product/list");
//            return new ModelAndView("common/error", map);
//        }
//        return new ModelAndView("product/list", map);
//    }
//
//    @GetMapping("/index")
//    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
//                              Map<String, Object> map) {
//        if (!StringUtils.isEmpty(productId)) {
//            ProductInfo productInfo = productService.findOne(productId);
//            map.put("productInfo", productInfo);
//        }
//        //查询所有的类目
//        List<ProductCategory> categoryList = categoryService.findAll();
//        map.put("categoryList", categoryList);
//        return new ModelAndView("product/index", map);
//    }
//
//    /*保存和更新*/
//    @PostMapping("/save")
//    public ModelAndView save(Map<String, Object> map,
//                             @Valid ProductForm productForm,
//                             BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
//            map.put("url", "/seller/product/index");
//            return new ModelAndView("common/error", map);
//        }
//        ProductInfo productInfo = new ProductInfo();
//        try {
//            if (!StringUtils.isEmpty(productForm.getProductId())) {
//                productInfo = productService.findOne(productForm.getProductId());
//            } else {
//                productInfo.setProductId(KeyUtil.genUniqueKey());
//            }
//            BeanUtils.copyProperties(productForm, productInfo);
//            productService.save(productInfo);
//        } catch (AppException e){
//            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
//            map.put("url", "/seller/product/index");
//            return new ModelAndView("common/error", map);
//        }
//        map.put("msg", "保存成功");
//        map.put("url", "/seller/product/list");
//        return new ModelAndView("common/success", map);
//    }

}
