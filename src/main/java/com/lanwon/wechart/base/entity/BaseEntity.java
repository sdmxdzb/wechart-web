package com.lanwon.wechart.base.entity;


/**
 * @author www.jeecg.org
 */
public class BaseEntity {
	
	 private Integer startRow;//开始行
	 
	 private Integer endRow;//结束行
	
	 
	 /**
	 * @return the startRow
	 */
	public Integer getStartRow() {
		return startRow;
	}

	/**
	 * @param startRow the startRow to set
	 */
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	/**
	 * @return the endRow
	 */
	public Integer getEndRow() {
		return endRow;
	}

	/**
	 * @param endRow the endRow to set
	 */
	public void setEndRow(Integer endRow) {
		this.endRow = endRow;
	}

	/**
	  * 状态枚举
	  * @author  www.jeecg.org
	  *
	  */
	 public static enum STATE {
		 	ENABLE(0, "可用"), DISABLE(1,"禁用");
			public int key;
			public String value;
			private STATE(int key, String value) {
				this.key = key;
				this.value = value;
			}
			public static STATE get(int key) {
				STATE[] values = STATE.values();
				for (STATE object : values) {
					if (object.key == key) {
						return object;
					}
				}
				return null;
			}
		}
	 	
	 	/**
	 	 * 删除枚举
	 	 * @author  www.jeecg.org
	 	 *
	 	 */
	 	public static enum DELETED {
			NO(0, "未删除"), YES(1,"已删除");
			public int key;
			public String value;
			private DELETED(int key, String value) {
				this.key = key;
				this.value = value;
			}
			public static DELETED get(int key) {
				DELETED[] values = DELETED.values();
				for (DELETED object : values) {
					if (object.key == key) {
						return object;
					}
				}
				return null;
			}
		}
	
}
