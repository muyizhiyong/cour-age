package com.muyi.courage.quartz.jobs;

import com.muyi.courage.common.util.RetCodeEnum;
import com.muyi.courage.quartz.dto.CityInfoDTO;
import com.muyi.courage.quartz.dto.CityWeatherDTO;
import com.muyi.courage.quartz.service.WeatherJobService;
import com.muyi.courage.quartz.util.HttpUtil;
import com.muyi.courage.quartz.util.WeatherParseUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class WeatherJob extends BaseTimeJob {

	@Value("${weather.weatherlUrl}")
	private String weatherlUrl ;

	@Value("${weather.etouchUrl}")
	private String etouchUrl;

	@Resource
	private WeatherJobService weatherJobService;

	@Override
	protected RetCodeEnum taskPerform(JobExecutionContext jobExecutionContext) throws Exception {

		//获取参数
		String cityNo =jobExecutionContext.getMergedJobDataMap().getString("cityNo");

		CityInfoDTO city = weatherJobService.getCityInfo(cityNo);
		boolean flag = false;
		List<CityWeatherDTO> weatherList = new ArrayList<>();

		if (weatherlUrl != null) {
			try {
				String html = HttpUtil.sendHttpGet(String.format(weatherlUrl, city.getWeatherId()));
				log.info("page crawling start.....");
				weatherList = WeatherParseUtil.getWeatherByHtml(html, city);
				log.debug(weatherList.toString());
				flag = true;
			} catch (Exception e) {
				log.error("weather page crawling failed：" + e);
				return RetCodeEnum.FAIL;
			}
		}
		if (!flag && etouchUrl != null) {
			String url = String.format(etouchUrl, city.getWeatherId());
			try {
				log.info("weather api crawling start......");
				weatherList = WeatherParseUtil.getWeatherByApi(url, city);
				log.debug(weatherList.toString());
			} catch (Exception e) {
				log.error("weather api crawling failed：" + e);
				return RetCodeEnum.FAIL;
			}
		}

		try {
			if (weatherList.size() == 0) {
				log.error("获取天气信息失败");
				return RetCodeEnum.FAIL;
			}
			for (CityWeatherDTO wet : weatherList) {
				String cityId = wet.getCityId();
				String weatherDate = wet.getWeatherDate();
				int insertFlag = 0;
				int updateFlag = 0;
				//判断是否存在当天天气信息
				boolean x = weatherJobService.exist(cityId, weatherDate);
				if (x) {
					//update
					updateFlag = weatherJobService.updateCityWeatherInfo(wet);
				} else {
					//insert
					insertFlag = weatherJobService.addCityWeatherInfo(wet);
				}
				if (insertFlag == 0 && updateFlag == 0) {
					return RetCodeEnum.FAIL;
				}
			}
		} catch (Exception e) {
			log.error("天气更新异常：", e);
			return RetCodeEnum.EXCEPTION;
		}
		return RetCodeEnum.SUCCEED;
	}
}
