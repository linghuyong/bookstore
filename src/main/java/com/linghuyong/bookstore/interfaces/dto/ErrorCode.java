package com.linghuyong.bookstore.interfaces.dto;

// Api的错误码
public class ErrorCode {
    // 未知错误
    public static int Unknown = 0;
    // 认证失败
    public static int AuthFailed = 1;

    // 参数错误
    public static int ParameterError = 2;

    // 资源不存在
    public static int ResourceNotFound = 3;

    // 库存不足
    public static int OutOfStock = 4;

    // 稍后重试
    public static int RetryLater = 5;
}
