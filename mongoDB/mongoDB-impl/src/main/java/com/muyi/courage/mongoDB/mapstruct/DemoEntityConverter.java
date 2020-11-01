package com.muyi.courage.mongoDB.mapstruct;

import com.muyi.courage.mongoDB.domain.DemoEntityDO;
import com.muyi.courage.mongoDB.dto.DemoEntityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface DemoEntityConverter {

	DemoEntityConverter INSTANCE = Mappers.getMapper(DemoEntityConverter.class);

	@Mappings({
	})
	DemoEntityDTO domain2dto(DemoEntityDO demoEntityDO);

	DemoEntityDO dto2do(DemoEntityDTO demoEntityDTO);

	List<DemoEntityDTO> domian2dto(List<DemoEntityDO> demoEntityDOS);

	List<DemoEntityDO> dto2do(List<DemoEntityDTO> demoEntityDTOS);
}

