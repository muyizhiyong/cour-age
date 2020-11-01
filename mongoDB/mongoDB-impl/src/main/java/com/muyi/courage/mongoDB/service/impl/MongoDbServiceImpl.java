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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨志勇
 * @date 2020-10-10 11:48
 */
@Slf4j
@Service
public class MongoDbServiceImpl implements MongoDBService {

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private DataSourceTransactionManager dataSourceTransactionManager;

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
    @Transactional(transactionManager = "mongoTransactionManager",rollbackFor = Exception.class)
    public DTO insert(DemoEntityDTO demoEntityDTO) {
        DTO dto = new DTO(RetCodeEnum.FAIL);

        DemoEntityDO demoEntityDO = new DemoEntityDO();
        demoEntityDO = DemoEntityConverter.INSTANCE.dto2do(demoEntityDTO);
        mongoTemplate.save(demoEntityDO);
        dto.setResult(RetCodeEnum.SUCCEED);
        int a = 5/0;  //异常抛出点这是个错误！

        return dto;
    }

    @Override
    @Transactional(transactionManager = "mongoTransactionManager",rollbackFor = Exception.class)
    public DTO delete(String id) {
        DTO dto = new DTO(RetCodeEnum.FAIL);

        Query query=new Query(Criteria.where("id").is(id));
        DemoEntityDO demoEntityDO =  mongoTemplate.findOne(query , DemoEntityDO.class);
        if(demoEntityDO !=null){
            mongoTemplate.remove(demoEntityDO);
            dto.setResult(RetCodeEnum.SUCCEED);
        }else {
            dto.setResult(RetCodeEnum.AUDIT_OBJECT_DELETED);
        }

        return dto;
    }

    @Override
    public List<DemoEntityDTO> qryListByName(String name) {
        List<DemoEntityDTO> retList = new ArrayList<>();
        try {
            Query query=new Query(Criteria.where("name").is(name));
            List<DemoEntityDO> demoEntityDOS =  mongoTemplate.find(query , DemoEntityDO.class);
            retList = DemoEntityConverter.INSTANCE.domian2dto(demoEntityDOS);

        }catch (Exception e){
            log.error("[qryListByName] error! e:{}",e.getMessage());
        }

        return retList;
    }

}
