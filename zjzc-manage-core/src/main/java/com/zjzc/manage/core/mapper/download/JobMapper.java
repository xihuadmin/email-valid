package com.zjzc.manage.core.mapper.download;

import com.zjzc.manage.core.model.schedule.ScheduleJob;
import com.zjzc.manage.core.model.schedule.ScheduleLog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface JobMapper {
	/**
	 * 
	 * @param job
	 */
	void addJob(ScheduleJob job);
	
	/**
	 * 
	 * @param job
	 */
	void updateJobStatus(ScheduleJob job);
	
	/**
	 * 
	 * @param log
	 */
	void addJobLog(ScheduleLog log);
	
	/**
	 * 
	 * @param log
	 */
	void updateJobLog(ScheduleLog log);
	
	/**
	 * 
	 * @param uuid
	 * @return
	 */
	ScheduleJob queryScheduleJob(String uuid);
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	Long queryLogCount(Map<String, Object> params);
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> queryLogList(Map<String, Object> params);
}
