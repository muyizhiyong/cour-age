package com.muyi.courage.quartz.domain;

import lombok.Data;


@Data
public class TimeJobDO {
    private String id;

    private String trigName;

    private String cron;

    private String jobName;

    private String objName;

    private Integer concurrent;

    private Integer jobState;
    
    private String desp;
    
    private String arguments;

	private Integer init_flag;
}
