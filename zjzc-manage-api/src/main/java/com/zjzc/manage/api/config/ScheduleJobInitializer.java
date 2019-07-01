package com.zjzc.manage.api.config;

import com.zjzc.manage.core.job.ScheduledJob;
import com.zjzc.manage.core.mapper.download.JobMapper;
import com.zjzc.manage.core.model.schedule.ScheduleJob;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ClassUtils;

import java.util.Base64;
import java.util.Map;

/**
 * 初始化定时任务
 */
public final class ScheduleJobInitializer {
	
	private ScheduleJobInitializer() {
	    throw new IllegalAccessError("Utility class");
	  }
	
	/**
	 * 初始化
	 * @param context
	 */
	public static void doInitialization(ApplicationContext context) {
		JobMapper jobMapper = context.getBean(JobMapper.class);

		Map<String, Object> jobMaps = context.getBeansWithAnnotation(ScheduledJob.class);

		Object[] jobs = jobMaps.values().toArray();

		initScheduleJob(jobs, jobMapper);
	}

	private static void initScheduleJob(Object[] jobs, JobMapper jobMapper) {
		for (int i = 0; i < jobs.length; i++) {
			Object obj = jobs[i];

			ScheduledJob job = ClassUtils.getUserClass(obj).getAnnotation(ScheduledJob.class);

			ScheduleJob batchJob = new ScheduleJob();
			batchJob.setName(job.name());
			batchJob.setStatus(0);
			batchJob.setCron(job.cron());
			batchJob.setUuid(getJobUUID(job.name()));

			if (jobMapper.queryScheduleJob(batchJob.getUuid()) == null) {
				jobMapper.addJob(batchJob);
			}
		}
	}

	private static String getJobUUID(String name) {
		return Base64.getEncoder().encodeToString(name.getBytes());
	}
}