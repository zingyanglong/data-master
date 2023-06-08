package com.swagger.controller;

/**
 * @Author: YL
 * @Date: 2023/06/08/15:00
 * @Description:
 */

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/wasgger")
@Api(tags = "仓储管理-库存管理")
public class TestController {
    @GetMapping(value = "/getDetail")
    @ApiOperation(value = "根据id获取仓储管理-库存管理数据详情", httpMethod = "GET", notes = "根据id获取仓储管理-库存管理数据详情")
    public String getDetail(@ApiParam(name = "id", value = "业务对象主键", required = true) @RequestParam(required = true) String id) throws Exception {
        return id;
    }
}
