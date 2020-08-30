package com.muyi.courage.quartz.service;

import com.muyi.courage.common.dto.ListDTO;
import com.muyi.courage.quartz.dto.CityInfoDTO;
import com.muyi.courage.quartz.dto.CityWeatherDTO;

public interface WeatherJobService {

	/**
	 * 通过cityNo获取地市信息
	 */
	CityInfoDTO getCityInfo(String cityNo);

	/**
	 * 获取地市信息List
	 */
	ListDTO<CityInfoDTO> getCityInfoList();

	/**
	 * 判断是否存在天气信息
	 */
	boolean exist(String cityId, String weatherDate);

	/**
	 * 新增天气信息
	 */
	int addCityWeatherInfo(CityWeatherDTO cityWeatherDTO);

	/**
	 * 更新天气信息
	 */
	int updateCityWeatherInfo(CityWeatherDTO cityWeatherDTO);

}
