package com.leone.boot.mvc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * @author leone
 * @since 2024-11-13
 **/
@RestController
@RequestMapping("/example")
@Tag(name = "示例接口", description = "提供示例内容展示SpringDoc集成效果")
public class ExampleController {

    @GetMapping("/test01")
    @Operation(summary = "无参查询接口", description = "hello")
    public String test01() {
        return "Hello Spring doc";
    }

    @GetMapping("/test02")
    @Parameter(name = "test02", description = "url参数")
    @Operation(summary = "查询参数示例", description = "原样返回入参")
    public String test02(String test02) {
        return test02;
    }

    @GetMapping("/test03/{test03}")
    @Parameter(name = "test03", description = "url参数")
    @Operation(summary = "url参数示例", description = "原样返回入参")
    public String test03(@PathVariable String test03) {
        return test03;
    }
    
}

