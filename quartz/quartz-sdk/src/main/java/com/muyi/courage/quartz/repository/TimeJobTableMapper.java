package com.muyi.courage.quartz.repository;

import com.muyi.courage.quartz.domain.TimeJobDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface TimeJobTableMapper {
	int deleteByPrimaryKey(String id);

	int insert(TimeJobDO record);

	int insertSelective(TimeJobDO record);

	TimeJobDO selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(TimeJobDO record);

	int updateByPrimaryKey(TimeJobDO record);

	int qryTotalRow(Map<String, Object> paramMap);

	List<TimeJobDO> qryTimeJob(Map<String, Object> params);

	List<TimeJobDO> selectAutoInit();

	int updateAutoInitByPrimaryKey(TimeJobDO record);


}
