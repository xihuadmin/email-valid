package com.zjzc.manage.core.mapper.download;

import com.zjzc.manage.core.model.schedule.BusiDate;
import org.springframework.stereotype.Repository;

/**
 * 业务日期
 * 
 * @author caijw
 *
 */
@Repository
public interface BusiDateMapper {
	
	/**
	 * 
	 * @return
	 */
	BusiDate queryBusiDate();
}
