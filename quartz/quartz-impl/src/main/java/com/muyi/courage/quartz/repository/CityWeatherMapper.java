package com.muyi.courage.quartz.repository;

import com.muyi.courage.quartz.po.CityWeatherPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;


@Mapper
public interface CityWeatherMapper {

	int insert(CityWeatherPO cityWeatherPO);

	int updateByPrimaryKey(CityWeatherPO cityWeatherPO);

	@Select("select city_id, weather_date from city_weather where city_id =#{cityId}  and weather_date =#{weatherDate} ")
	Map<String, Object> getWeatherKey(@Param("cityId") String cityId, @Param("weatherDate") String weatherDate);
}
