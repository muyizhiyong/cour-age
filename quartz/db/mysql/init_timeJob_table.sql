-- 获取天气
INSERT INTO `time_job` VALUES ('100001', 'TestWeatherJob_1', '*/30 * * * * ?', 'weatherJob', 'com.muyi.courage.timejob.jobs.WeatherJob', 0, 0, '天气定时任务', '{\"cityNo\":\"22\"}', 0);
