package com.muyi.courage.quartz.mapstruct;

import com.muyi.courage.quartz.dto.TimeJobDTO;
import com.muyi.courage.quartz.domain.TimeJobDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface JobDTOConverter {
	JobDTOConverter INSTANCE = Mappers.getMapper(JobDTOConverter.class);

	TimeJobDTO domain2dto(TimeJobDO timeJobDO);

	List<TimeJobDTO> domain2dto(List<TimeJobDO> timeJobDOS);

	TimeJobDO dto2dmoian(TimeJobDTO timeJobDTO);
}
