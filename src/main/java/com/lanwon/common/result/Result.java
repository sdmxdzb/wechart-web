package com.lanwon.common.result;

import org.apache.commons.httpclient.HttpStatus;


public class Result {

	/**
	 * 响应业务状态 200 400 
	 */
	private int code;
	
	/**
	 * 响应消息
	 */
	private String msg;
	/**
	 * 响应中的数据
	 */
	private Object data;
	
	public Result(){}
	
	
	public static Result ok(Object data) {
		return new Result(data);
	}

	public static Result ok() {
		return new Result(null);
	}

	

	public static Result build(Integer status, String msg) {
		return new Result(status, msg, null);
	}
	
	public static Result build(Integer status, String msg, Object data) {
		return new Result(status, msg, data);
	}
	public Result(int status){
		this.code=status;
	}
	public Result(Object data){
		this.code=HttpStatus.SC_OK;
		this.data=data;
	}
	public Result(int status,String msg){
		this.code=status;
		this.msg=msg;
	}
	public Result(int status,String msg,Object data){
		this.code=status;
		this.msg=msg;
		this.data=data;
	}
	public Result(String msg,Object data){
		this.code=HttpStatus.SC_OK;
		this.msg=msg;
		this.data=data;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getmsg() {
		return msg;
	}
	public void setmsg(String msg) {
		msg = msg;
	}
	public Object getdata() {
		return data;
	}
	public void setdata(Object data) {
		this.data = data;
	}

}
