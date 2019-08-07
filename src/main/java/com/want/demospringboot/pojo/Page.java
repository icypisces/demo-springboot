package com.want.demospringboot.pojo;

public class Page {

	private int pageNum;
	private int pageSize;

	private int offset;

	public Page() {
		super();
	}

	public Page(int pageNum, int pageSize) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.offset = (pageNum - 1) * pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getOffset() {
		return (pageNum - 1) * pageSize;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

}
