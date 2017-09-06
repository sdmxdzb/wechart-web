package com.lanwon.wechart.base.entity;

import java.util.List;

/**
 * Function:DataGridResult. <br/>
 * Date: 2016年6月28日 下午7:19:21 <br/>
 * 
 * @author weiguo21
 * @version
 * @since JDK 1.7
 * @see
 */
public class DataGrid {
	public DataGrid() {
		super();
	}

	public DataGrid(List<?> rows, Long total) {
		super();
		this.rows = rows;
		this.total = total;
	}

	/**
	 * 当前页记录数
	 */
	private List<?> rows;
	/**
	 * 总数
	 */
	private Long total;

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

}
