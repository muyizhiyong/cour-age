package com.muyi.courage.mongoDB.service;

import com.muyi.courage.common.dto.DTO;
import com.muyi.courage.mongoDB.dto.DemoEntityDTO;

/**
 * @author 杨志勇
 * @date 2020-10-10 11:48
 */
public interface MongoDBService {
   DemoEntityDTO qryOneById(String id);

   DTO insert(DemoEntityDTO demoEntityDTO);

   DTO delete(String id);
}
