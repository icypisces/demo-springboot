package com.want.demospringboot.utils;

import org.apache.ibatis.session.RowBounds;

import com.want.demospringboot.pojo.Page;

public class PageMethod {

	public static RowBounds getRowBounds(Page page) {
		return new RowBounds(page.getOffset(), page.getPageSize());
	}
	
}
