package com.transaction.producer.response.wrapper;

public class APIResponse<T> {
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public APIResponse(T data) {
		super();
		this.data = data;
	}
}
