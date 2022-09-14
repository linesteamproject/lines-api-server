package com.linesteams.linesapiserver.dto;

import org.springframework.http.HttpStatus;

public class ResponseDto<T> {
    private Integer httpStatus;
    private Boolean success;
    private String error;
    private T responseData;

    public ResponseDto(Integer httpStatus, Boolean success, String error, T responseData) {
        this.httpStatus = httpStatus;
        this.success = success;
        this.error = error;
        this.responseData = responseData;
    }

    public static <E> ResponseDto<E> of(E data) {
        return new ResponseDto(200, true, null, data);
    }

    public static ResponseDto toError(HttpStatus httpStatus, String error) {
        return new ResponseDto(httpStatus.value(), false, error, null);
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }

    public T getResponseData() {
        return responseData;
    }
}
