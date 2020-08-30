package com.muyi.courage.quartz.repository;

import com.muyi.courage.quartz.domain.TimeJobLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface TimeJobLogMapper {
	int insert(TimeJobLog record);

    int insertSelective(TimeJobLog record);

    TimeJobLog selectByPrimaryKey(String id);
   
    int qryTotalRow(Map<String, Object> paramMap);
    
    List<TimeJobLog> qryTimeJobLogByPage(Map<String, Object> params);

    int updateByPrimaryKey(TimeJobLog record);
    

}
