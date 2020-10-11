package com.muyi.courage.mongoDB.dto;

import com.muyi.courage.common.dto.DTO;
import com.muyi.courage.common.util.RetCodeEnum;
import lombok.Data;

/**
 * @author 杨志勇
 * @date 2020-10-11 16:53
 */
@Data
public class DemoEntityDTO extends DTO {
    private static final long serialVersionUID = 1L;

    String id;

    String name;

    public DemoEntityDTO(RetCodeEnum e) {
        super();
    }

    public DemoEntityDTO(){
    }
}

