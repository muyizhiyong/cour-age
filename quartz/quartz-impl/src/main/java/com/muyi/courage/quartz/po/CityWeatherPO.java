package com.muyi.courage.quartz.po;

import lombok.Data;


@Data
public class CityWeatherPO {
	private String cityId;

	private String cityName;

	private String weatherDate;

	private String dayWeatherId;

	private String dayWeather;

	private float dayTemp;

	private String dayWind;

	private String dayWindComp;
}
