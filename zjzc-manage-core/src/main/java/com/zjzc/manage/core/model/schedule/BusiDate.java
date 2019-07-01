package com.zjzc.manage.core.model.schedule;

import java.time.LocalDate;

/**
 * 
 */
public class BusiDate {
	
	private LocalDate busiDate;
	private boolean isActive;
	public LocalDate getBusiDate() {
		return busiDate;
	}
	public void setBusiDate(LocalDate busiDate) {
		this.busiDate = busiDate;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


}
