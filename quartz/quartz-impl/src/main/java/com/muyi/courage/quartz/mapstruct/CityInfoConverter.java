package com.muyi.courage.quartz.mapstruct;

import com.muyi.courage.quartz.dto.CityInfoDTO;
import com.muyi.courage.quartz.po.CityInfoPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityInfoConverter {

	CityInfoConverter INSTANCE = Mappers.getMapper(CityInfoConverter.class);

	@Mappings({

	})
	CityInfoDTO domain2dto(CityInfoPO cityInfoPO);

	List<CityInfoDTO> domain2dto(List<CityInfoPO> cityInfoPOList);
}
