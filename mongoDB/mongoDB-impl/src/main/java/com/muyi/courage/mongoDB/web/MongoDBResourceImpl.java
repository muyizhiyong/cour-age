package com.muyi.courage.mongoDB.web;

import com.muyi.courage.common.dto.DTO;
import com.muyi.courage.common.util.RetCodeEnum;
import com.muyi.courage.mongoDB.dto.DemoEntityDTO;
import com.muyi.courage.mongoDB.service.MongoDBService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
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
    public List<DemoEntityDTO> qryListByName(String name) {
        return mongoDBService.qryListByName(name);
    }

    @Override
    public DTO insert(DemoEntityDTO demoEntityDTO) {
        DTO dto = new DTO(RetCodeEnum.FAIL);
        try {
            dto =  mongoDBService.insert(demoEntityDTO);
        }catch (Exception e){
            log.error("error! msg：{}",e.getMessage());
        }
        return dto;
    }

    @Override
    public DTO delete(String id) {
        DTO dto = new DTO(RetCodeEnum.FAIL);
        try {
            dto =  mongoDBService.delete(id);
        }catch (Exception e){
            log.error("error! msg：{}",e.getMessage());
        }
        return dto;
    }
}
