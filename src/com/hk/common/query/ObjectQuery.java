package com.hk.common.query;

import java.util.HashMap;

public abstract class ObjectQuery {

	private int pageNo = 1;
	private int pageSize = 10;

	private boolean flag = false;//是否已经拼接过查询条件，在获取查询条件时调用
	
	private int first = 0;// 当前页开始记录位置
	protected HashMap<String, Object> params = new HashMap<String, Object>();

	protected void addWhere(String where, Object param) {
		params.put(where, param);
	}

	protected abstract void addWhere();

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public HashMap<String, Object> getParams() {
		if(!flag) {
			addWhere();
			flag = true;
		}
		///添加分页信息
		addPageParams();
		return params;
	}

	public void setParams(HashMap<String, Object> params) {
		this.params = params;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	/**
	 * 添加分页查询条件
	 */
	private void addPageParams() {
		//计算开始位置
		this.first = (this.pageNo - 1) * this.pageSize;
		this.params.put("first", this.first);
		this.params.put("pageSize", this.pageSize);
	}
}
