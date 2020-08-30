package com.muyi.courage.quartz.domain;

public class TimeJobLog {
	private String logicId;

    private String jobName;

    private Integer jobResult;

    private Integer jobType;

    private String jobCreator;

	private String startTime;

    private String endTime;

    private String resultDesc;
    
    private Integer errorLevel;

	public String getLogicId() {
		return logicId;
	}

	public void setLogicId(String logicId) {
		this.logicId = logicId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Integer getJobResult() {
		return jobResult;
	}

	public void setJobResult(Integer jobResult) {
		this.jobResult = jobResult;
	}

	public Integer getJobType() {
		return jobType;
	}

	public void setJobType(Integer jobType) {
		this.jobType = jobType;
	}

	public String getJobCreator() {
		return jobCreator;
	}

	public void setJobCreator(String jobCreator) {
		this.jobCreator = jobCreator;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public Integer getErrorLevel() {
		return errorLevel;
	}

	public void setErrorLevel(Integer errorLevel) {
		this.errorLevel = errorLevel;
	}
   
}
