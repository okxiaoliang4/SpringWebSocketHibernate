package com.jelf.entity;

import java.io.Serializable;

public class DataResult implements Serializable {

	private static final long serialVersionUID = 1L;
	private String message;
	private Object data;
	private Integer state;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
