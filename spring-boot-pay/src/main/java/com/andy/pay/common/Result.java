package com.andy.pay.common;


import com.andy.pay.common.enums.ResultEnum;
import lombok.Data;

/**
 * 基本返回结果集
 * @param <T>
 */
@Data
public class Result<T> {
	
	private int retCode;

	private String message;

	private T data;

	private Result() {
	}

	private Result(int retCode, String message, T data) {
		this.retCode = retCode;
		this.message = message;
		this.data =data;
	}

	public static <T> Result<T> build(int code, String message, T data) {
		return new Result<>(code, message, data);
	}

	public static <T> Result<T> build(ResultEnum resultEnum, T data) {
		return new Result<>(resultEnum.getCode(), resultEnum.getMessage(), data);
	}

	public static <T> Result<T> build(ResultEnum resultEnum) {
		return new Result<>(resultEnum.getCode(), resultEnum.getMessage(), null);
	}

	public static <T> Result<T> error(T data) {
		return new Result<>(50000, "error", data);
	}

	public static <T> Result<T> success(T data) {
		return new Result<>(20000, "success", data);
	}

}
