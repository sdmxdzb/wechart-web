package com.lanwon.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;


@SuppressWarnings("serial")
public class MapperException extends BaseException {

	private Throwable rootCause;

	public MapperException() {
		super();
	}

	public MapperException(String message) {
		super(message);
	}

	public MapperException(Throwable cause) {
		super(cause);
		this.rootCause = cause;
	}

	public MapperException(String message, Throwable cause) {
		super(message, cause);
		this.rootCause = cause;
	}

	public String getTraceInfo() {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		printStackTrace(pw);
		pw.flush();
		sw.flush();
		return sw.toString();
	}

	public Throwable getRootCause() {
		return rootCause;
	}

}
