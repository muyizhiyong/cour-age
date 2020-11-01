package com.muyi.courage.mongoDB.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

/**
 * @author 杨志勇
 * @date 2020-10-20 09:08
 */
@Configuration
public class MongoDBConfig{

    @Bean(name = "mongoTransactionManager")
    MongoTransactionManager transactionManager(MongoDatabaseFactory factory){
        return new MongoTransactionManager(factory);
    }


}
