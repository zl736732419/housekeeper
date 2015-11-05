package com.hk.common.pager;

import java.util.List;

/**
 * 抽象页面封装类，封装一页的信息
 *
 * @author zhenglian
 * @data 2015年10月19日 下午9:34:22
 * @param <T>
 */
public class Pager<T> {
	/**
	 * 当前页
	 */
	private int pageNo = 1;
	/**
	 * 一页的大小
	 */
	private int pageSize = 10;

	/**
	 * 总页数
	 */
	private int totalPages;

	/**
	 * 总记录数
	 */
	private int totalRows;

	/**
	 * 当前页数据
	 */
	private List<T> rows;

	public Pager(int pageNo, int pageSize, int totalRows) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalRows = totalRows;

		// 容错处理
		this.pageNo = this.pageNo < 1 ? 1 : this.pageNo;
		this.pageSize = this.pageSize < 1 ? 10 : this.pageSize;

		// 总页数
		this.totalPages = (this.totalRows + this.pageSize - 1) / this.pageSize;// 分页算法

		this.pageNo = this.pageNo > this.totalPages ? this.totalPages
				: this.pageNo;
		
		//查询结果为空时，totalPages=0,所以这时pageNo=0,需要设置为1
		this.pageNo = this.pageNo < 1 ? 1 : this.pageNo;
	}

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

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "Pager [pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", totalPages=" + totalPages + ", totalRows=" + totalRows
				+ "]";
	}

}
