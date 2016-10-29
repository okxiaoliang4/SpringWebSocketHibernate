package com.jelf.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;
	private String toUserId;
	private String message;
	private Date date;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
