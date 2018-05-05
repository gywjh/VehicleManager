package com.ruixing.vehicle.manager.core.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class QueryAlarmTimerTask {
	private Logger logger = LoggerFactory.getLogger(QueryAlarmTimerTask.class);

	int count = 0;

	@Scheduled(cron = "*/6 * * * * ?")
	private void process() {
		logger.debug("每隔6秒采集一次数据：" + count++);
	}
}
