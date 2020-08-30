package com.muyi.courage.quartz.mapstruct;

import com.muyi.courage.common.dto.ListDTO;
import com.muyi.courage.quartz.dto.CityWeatherDTO;
import com.muyi.courage.quartz.po.CityWeatherPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityWeatherConverter {

	CityWeatherConverter INSTANCE = Mappers.getMapper(CityWeatherConverter.class);

	CityWeatherDTO domain2dto(CityWeatherPO cityWeatherPO);

	CityWeatherPO dto2domain(CityWeatherDTO cityWeatherDTO);

	}
