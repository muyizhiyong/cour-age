package com.muyi.courage.quartz.service.impl;

import com.muyi.courage.common.dto.PageDTO;
import com.muyi.courage.common.util.RetCodeEnum;
import com.muyi.courage.quartz.dto.TimeJobDTO;
import com.muyi.courage.quartz.dto.TimeJobParamDTO;
import com.muyi.courage.quartz.mapstruct.JobDTOConverter;
import com.muyi.courage.quartz.repository.TimeJobTableMapper;
import com.muyi.courage.quartz.service.JobManagerService;
import com.muyi.courage.quartz.service.JobService;
import com.muyi.courage.quartz.domain.TimeJobDO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class JobServiceImpl implements JobService {
	private static Log log = LogFactory.getLog(JobServiceImpl.class);

	private static final String GROUP = "default";
	private final TimeJobTableMapper timeJobTableMapper;
	private final JobManagerService jobManagerService;

	@Autowired
	public JobServiceImpl(TimeJobTableMapper timeJobTableMapper, JobManagerService jobManagerService) {
		this.timeJobTableMapper = timeJobTableMapper;
		this.jobManagerService = jobManagerService;
	}

	@Override
	public PageDTO<TimeJobDTO> qryTimeJob(TimeJobParamDTO timeJobParamDTO) {
		PageDTO<TimeJobDTO> result = new PageDTO<>();
		result.setResult(RetCodeEnum.FAIL);

		try {
			int pageSize = timeJobParamDTO.getPageSize();
			int curPage = timeJobParamDTO.getCurPage();
			String jobName_Query = timeJobParamDTO.getJobNameQuery();

			if (curPage < 1) {
				curPage = 1;
			}
			if (pageSize < 1) {
				pageSize = 20;
			}

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("startRow", pageSize * (curPage - 1));
			paramMap.put("endRow", pageSize * curPage);
			paramMap.put("pageSize", pageSize);
			paramMap.put("jobName_Query", jobName_Query);

			int totalRow = timeJobTableMapper.qryTotalRow(paramMap);
			int totalPage = (int) Math.ceil((double) totalRow / (double) pageSize);

			List<TimeJobDO> list = timeJobTableMapper.qryTimeJob(paramMap);
			List<TimeJobDTO> timeJobDTOS = JobDTOConverter.INSTANCE.domain2dto(list);

			result.setRetList(timeJobDTOS);
			result.setCurPage(curPage);
			result.setPageSize(pageSize);
			result.setTotalPage(totalPage);
			result.setTotalRow(totalRow);
			result.setResult(RetCodeEnum.SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(RetCodeEnum.FAIL);
		}
		return result;
	}

}
