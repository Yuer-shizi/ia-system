package com.ia.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RetMessage {
	private int code = 200;

	private Object data;

	private String message = "请求成功";

	public RetMessage(Object data) {
		this.data = data;
	}

	public RetMessage(Object data, String message) {
		this.data = data;
		this.message = message;
	}

	public RetMessage(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
