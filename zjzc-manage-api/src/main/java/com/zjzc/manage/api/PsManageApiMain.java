package com.zjzc.manage.api;

import com.zjzc.manage.utils.others.DataUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 
 * 功能描述：
 * @作者 lijun	创建时间： 2016年11月23日 上午1:27:05
 *
 */
@SpringBootApplication
@ComponentScan("com.zjzc.manage")
@EnableAutoConfiguration
@EnableScheduling
@Configuration
public class PsManageApiMain {

	public static void main(String[] args) {
		SpringApplication.run(PsManageApiMain.class, args);
	}

}