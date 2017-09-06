package com.lanwon.common.exception;

/**
 * 
 * Function:Service层公用的Exception. 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚. <br/>
 * Date:     2016年6月24日 上午10:54:19 <br/>
 * @author weiguo21
 * @version  
 * @since    JDK 1.7
 * @see
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 3583566093089790852L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}