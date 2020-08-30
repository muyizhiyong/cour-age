package com.muyi.courage.quartz.repository;

import com.muyi.courage.quartz.po.CityInfoPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CityInfoMapper {

	CityInfoPO getCityInfo(String cityNo);

	List<CityInfoPO> getCityInfoList();

}
