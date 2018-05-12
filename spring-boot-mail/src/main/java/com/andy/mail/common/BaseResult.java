package com.andy.mail.common;


/**
 * 基本返回结果集
 * @param <T>
 */
public class BaseResult<T> {
	
	private int code;

	private String msg;

	private T content;

	private BaseResult() {

	}

	private BaseResult(int code, String msg, T content) {
		this.code = code;
		this.msg = msg;
		this.content =content;
	}

	public static <T> BaseResult<T> build(int code, String msg, T content) {
		return new BaseResult<>(code, msg, content);
	}


	public static <T> BaseResult<T> error(T content) {
		return new BaseResult<>(19999, "error", content);
	}

	public static <T> BaseResult<T> success(T content) {
		return new BaseResult<>(10000, "success", content);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}
}
