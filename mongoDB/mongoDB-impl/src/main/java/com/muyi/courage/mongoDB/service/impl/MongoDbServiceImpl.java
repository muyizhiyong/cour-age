package com.muyi.courage.mongoDB.service.impl;

import com.muyi.courage.common.dto.DTO;
import com.muyi.courage.common.util.RetCodeEnum;
import com.muyi.courage.mongoDB.domain.DemoEntityDO;
import com.muyi.courage.mongoDB.dto.DemoEntityDTO;
import com.muyi.courage.mongoDB.mapstruct.DemoEntityConverter;
import com.muyi.courage.mongoDB.service.MongoDBService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 杨志勇
 * @date 2020-10-10 11:48
 */
@Slf4j
@Service
public class MongoDbServiceImpl implements MongoDBService {

    @Resource
    private MongoTemplate mongoTemplate;


    @Override
    public DemoEntityDTO qryOneById(String id) {
        log.debug("[qryOneById] id:{}",id);
        DemoEntityDTO demoEntityDTO = new DemoEntityDTO(RetCodeEnum.FAIL);
        try {
            Query query=new Query(Criteria.where("id").is(id));
            DemoEntityDO demoEntityDO =  mongoTemplate.findOne(query , DemoEntityDO.class);

            if(demoEntityDO==null){
                demoEntityDTO.setResult(RetCodeEnum.AUDIT_OBJECT_DELETED);
            }else{
                demoEntityDTO = DemoEntityConverter.INSTANCE.domain2dto(demoEntityDO);
            }

        }catch (Exception e){
            log.error("[qryById] error! e:{}",e.getMessage());
        }
        return demoEntityDTO;
    }

    @Override
    public DTO insert(DemoEntityDTO demoEntityDTO) { ;
        DTO dto = new DTO(RetCodeEnum.FAIL);
        try {
            DemoEntityDO demoEntityDO = new DemoEntityDO();
            demoEntityDO = DemoEntityConverter.INSTANCE.dto2do(demoEntityDTO);
            mongoTemplate.save(demoEntityDO);
            dto.setResult(RetCodeEnum.SUCCEED);
        }catch (Exception e){
            log.error("[insert] error! e:{}",e.getMessage());
        }
        return dto;
    }

    @Override
    public DTO delete(String id) {
        DTO dto = new DTO(RetCodeEnum.FAIL);
        try {
            Query query=new Query(Criteria.where("id").is(id));
            DemoEntityDO demoEntityDO =  mongoTemplate.findOne(query , DemoEntityDO.class);
            if(demoEntityDO !=null){
                mongoTemplate.remove(demoEntityDO);
                dto.setResult(RetCodeEnum.SUCCEED);
            }else {
                dto.setResult(RetCodeEnum.AUDIT_OBJECT_DELETED);
            }
        }catch (Exception e){
            log.error("[delete] error! e:{}",e.getMessage());
        }
        return dto;
    }

}
