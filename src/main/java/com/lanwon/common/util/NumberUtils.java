package com.lanwon.common.util;

/**
 * 
 * @see 数值类型处理. <br/>
 */
public class NumberUtils {
	/**
	 * 验证Integer是否为空.
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isEmptyInteger(Integer obj) {
		if (obj == null || obj.equals(0)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isEmptyLong(Long obj) {
		if (obj == null || obj.equals(0)) {
			return true;
		}
		return false;
	}

}
