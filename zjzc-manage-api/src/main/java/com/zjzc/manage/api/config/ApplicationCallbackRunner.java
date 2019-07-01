package com.zjzc.manage.api.config;

import com.zjzc.manage.core.mapper.download.BusiDateMapper;
import com.zjzc.manage.core.dao.impl.DBDateSupplier;
import com.zjzc.manage.utils.others.DateUtilJob;
import com.zjzc.manage.utils.others.LocalDateSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * springboot初始化之后执行，
 * 1：初始化DateSupplier
 * 2：初始化Validation
 * 3：初始化调度
 */
@Component
public class ApplicationCallbackRunner implements CommandLineRunner {
	
	public static final String APPLICATION_MODE_DEV = "dev";

	@Value("${application.mode:prod}")
	private String mode;

	@Autowired
	private ApplicationContext applicationContext;
	
	/**
	 * 执行
	 */
	@Override
	public void run(String... args) throws Exception {
		if(APPLICATION_MODE_DEV.equals(mode)) {
			DateUtilJob.setDateSupplier(new DBDateSupplier(applicationContext.getBean(BusiDateMapper.class))) ;
		} else {
			DateUtilJob.setDateSupplier(new LocalDateSupplier());
		}

		//初始化调度
		ScheduleJobInitializer.doInitialization(applicationContext);
	}
}
