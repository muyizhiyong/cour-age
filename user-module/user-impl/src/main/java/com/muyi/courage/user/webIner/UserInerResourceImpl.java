package com.muyi.courage.user.webIner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 杨志勇
 * @date 2021-03-16 17:00
 */
@Slf4j
@RestController
public class UserInerResourceImpl implements UserInerResource {
    @Override
    public String getEmailByUserName(String userName) {
        return null;
    }

    @Override
    public String getNameByUserName(String userName) {
        return null;
    }
}
