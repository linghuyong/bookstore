package com.linghuyong.bookstore.infrastructure.common.exception;

public class ServerRuntimeException extends RuntimeException{
    private final int errorCode;

    public int getErrorCode() {
        return this.errorCode;
    }

    public ServerRuntimeException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
