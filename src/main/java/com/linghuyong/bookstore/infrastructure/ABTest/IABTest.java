package com.linghuyong.bookstore.infrastructure.ABTest;

// 简化的ABTest变量获取的接口
public interface IABTest {
    // 获取变量名name的值，返回int
    int fetchTestVariableAsInt(long userId, String name);

    // 获取变量名name的值，返回String
    String fetchTestVariableAsString(long userId, String name);

    // 获取变量名name的值，返回double
    double fetchTestVariableAsDouble(long userId, String name);
}
