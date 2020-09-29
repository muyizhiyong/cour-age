package com.muyi.courage.aop.repository;

import com.muyi.courage.aop.po.WebLogPO;
import org.apache.ibatis.annotations.Insert;

/**
 * @author 杨志勇
 * @date 2020-09-29 15:07
 */
public interface SysLogMapper {

    @Insert({
            "insert into  ",
            "SYS_WEB_LOG (USER_NO, METHOD, URL, RESULT_CODE, RESULT_MSG, START_TIME, COST_TIME, " +
                    "CLIENT_IP, SERVER_IP, DESCRIPTION)",
            "values (#{username,jdbcType=VARCHAR},#{method,jdbcType=VARCHAR},#{url,jdbcType=VARCHAR}, " +
                    "#{resultCode,jdbcType=VARCHAR},#{resultMsg,jdbcType=VARCHAR}, " +
                    "#{startTime,jdbcType=VARCHAR},#{costTime,jdbcType=VARCHAR},  " +
                    "#{clientIp,jdbcType=VARCHAR},#{serverIp,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}" +
                    ")"
    })
    void insert(WebLogPO webLogPO);
}
