package com.app.appchallenge.model;

import com.app.appchallenge.net.RestError;
import com.google.gson.annotations.SerializedName;

/**
 * Simple message container part of the {@link RestError}
 * 
 * @author Heisenbugs
 * */
public class Message {

	@SerializedName("message")
	private String message;

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return message;
	}
}
