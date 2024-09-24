package com.linghuyong.bookstore.infrastructure.common.api;

import lombok.Data;

@Data
public class Response<T> {

    Status status;
    String msg;
    int errCode;
    T data;

    public static <T> Response<T> ok() {
        Response<T> response = new Response<>();
        response.status = Status.SUCCESS;
        return response;
    }

    public static <T> Response<T> ok(T data) {
        Response<T> response = new Response<>();
        response.status = Status.SUCCESS;
        response.data = data;
        return response;
    }

    public static <T> Response<T> failed(int errCode, String msg) {
        Response<T> response = new Response<>();
        response.status = Status.FAILED;
        return response;
    }

    public enum Status{
        SUCCESS, FAILED
    }
}
