package com.muyi.courage.mongoDB.domain;

import com.muyi.courage.common.util.RetCodeEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author 杨志勇
 * @date 2020-10-11 17:12
 */
@Data
@Document("demo_entity")
public class DemoEntityDO {

    @Id
    String id;

    String name;
}
