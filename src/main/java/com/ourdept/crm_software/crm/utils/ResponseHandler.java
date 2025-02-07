package com.ourdept.crm_software.crm.utils;

public interface ResponseHandler<T> {
	ApiResponse<T> success(T data, String message, int status);

	ApiResponse<T> success(String message, int status);

	ApiResponse<T> error(String message, int status);
}
