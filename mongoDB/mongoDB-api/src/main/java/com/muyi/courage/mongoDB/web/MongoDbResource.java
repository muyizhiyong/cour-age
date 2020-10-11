package com.muyi.courage.mongoDB.web;

import com.muyi.courage.common.dto.DTO;
import com.muyi.courage.mongoDB.dto.DemoEntityDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author 杨志勇
 * @date 2020-10-10 11:33
 */
@Api(value = "mongoDB：mongoDB",tags = {"mongoDB：mongoDB"})
public interface MongoDbResource {

    String PREFIX = "mongoDB/api";

    @GetMapping(PREFIX + "/qry")
    @ApiOperation(value = "根据ID查询(一条)", notes = "根据ID查询(一条)")
    DemoEntityDTO qryOneById(@RequestParam String id);


    @PostMapping(PREFIX+"/insert")
    @ApiOperation(value = "保存", notes = "保存")
    DTO insert(@RequestBody DemoEntityDTO demoEntityDTO);
}
