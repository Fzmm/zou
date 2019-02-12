package com.yy.sxmyt.config.data;

import java.io.Serializable;

/**
 * 通过此对象封装业务层返回的结果 便于对业务层返回数据进行统一格式化, 友好性管理
 * 
 * @author xlr
 * */
public class DataResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int SUCCESS = 0;
	public static final int ERROR = -1;
	/** 服务端的响应状态 */
	private int code = 0;
	/**
	 * 具体设定的错误状态码，只在code=-1时有效
	 */
	private int subCode;
	/** 信息(给用户的提示) */
	private String message;
	/** 具体业务数据 */
	private Object data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DataResult() {
		this.code = SUCCESS;
		// this.message="ok";
	}

	public DataResult(Object data) {
		this();
		this.data = data;
	}

	public DataResult(int code, int subCode, String message) {
		this.code = code;
		this.message = message;
		this.subCode = subCode;
	}

	public DataResult(int code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public DataResult(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public DataResult(Throwable e) {
		this.code = ERROR;
		this.message = e.getMessage();
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getState() {
		return code;
	}

	public String getStringData() {
		return (String) data;
	}

	public Integer getIntData() {
		return (Integer) data;
	}

	public Double getDoubleData() {
		return (Double) data;
	}

	public int getSubCode() {
		return subCode;
	}

	public void setSubCode(int subCode) {
		this.subCode = subCode;
	}

}
