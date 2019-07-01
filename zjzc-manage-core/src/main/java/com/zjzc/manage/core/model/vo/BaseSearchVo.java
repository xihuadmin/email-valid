package com.zjzc.manage.core.model.vo;

public class BaseSearchVo {

	/**
	 * 记录查询开始
	 */
	private int    start  = 0;
	/**
	 * 一页大小
	 */
	private int    size   = 0;
	/**
	 * 当前页
	 */
	private int    page   = 0;
	
	
	public int getStart() {
		if(page<=0) {
			page = 1;
		}
		start = (page-1)*size;
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getSize() {
		if(size<=0) {
		    size = 30;
		}
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
}
