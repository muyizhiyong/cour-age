package com.muyi.courage.mongoDB.web;

import com.muyi.courage.common.dto.DTO;
import com.muyi.courage.mongoDB.dto.DemoEntityDTO;
import com.muyi.courage.mongoDB.service.MongoDBService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 杨志勇
 * @date 2020-10-10 11:40
 */
@Slf4j
@RestController
public class MongoDBResourceImpl implements MongoDbResource {

    @Resource
    private MongoDBService mongoDBService;

    @Override
    public DemoEntityDTO qryOneById(String id) {
        return mongoDBService.qryOneById(id);
    }

    @Override
    public DTO insert(DemoEntityDTO demoEntityDTO) {
        return mongoDBService.insert(demoEntityDTO);
    }

    @Override
    public DTO delete(String id) {
        return mongoDBService.delete(id);
    }
}
