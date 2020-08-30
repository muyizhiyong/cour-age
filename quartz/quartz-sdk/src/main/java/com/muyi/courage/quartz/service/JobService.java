package com.muyi.courage.quartz.service;


import com.muyi.courage.common.dto.PageDTO;
import com.muyi.courage.quartz.dto.TimeJobDTO;
import com.muyi.courage.quartz.dto.TimeJobParamDTO;


public interface JobService {
	PageDTO<TimeJobDTO> qryTimeJob(TimeJobParamDTO timeJobParamDTO);

}
