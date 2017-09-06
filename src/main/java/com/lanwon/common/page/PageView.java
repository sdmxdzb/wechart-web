package com.lanwon.common.page;

import java.util.List;

/**
 * 
 * Function:分页返回结果. <br/>
 */
public class PageView<T> {

	/**
	 * 当前页码
	 */
	private Integer page;

	/**
	 * 总页数
	 */
	private Integer pages;

	/**
	 * 总项数
	 */
	private Integer total;
	/**
	 * 每页数据
	 */
	private List<T> rows;

	/**
	 * 无参构造
	 */
	public PageView() {
		super();
	}

	/**
	 * 常用分页器构造(推荐使用)
	 * 
	 * @param pagination
	 * @param items
	 */
	public PageView(PageQuery query, List<T> rows) {
		this.page = query.getPage();
		this.pages = query.getPages();
		this.total = query.getItems();
		this.rows = rows;
	}

	/**
	 * 全部参数构造
	 * 
	 * @param currentPage
	 * @param totalPages
	 * @param items
	 */
	public PageView(Integer page, Integer pages, List<T> rows) {
		super();
		this.page = page;
		this.pages = pages;
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
