package com.linghuyong.bookstore.infrastructure.ABTest;

// 空的IABTest的实现
public class ABTestImpl implements IABTest{

    // config是分流服务器的配置
    // experimentId表示在AB实验后台配置的实验id
    public ABTestImpl(String config, long experimentId) {

    }

    // 获取变量名name的值，返回int
    @Override
    public int fetchTestVariableAsInt(long userId, String name) {
        return 0;
    }

    // 获取变量名name的值，返回String
    @Override
    public String fetchTestVariableAsString(long userId, String name) {
        return null;
    }

    // 获取变量名name的值，返回double
    @Override
    public double fetchTestVariableAsDouble(long userId, String name) {
        return 0;
    }
}
