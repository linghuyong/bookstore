package com.linghuyong.bookstore.domain.page.service;

import com.linghuyong.bookstore.infrastructure.ABTest.ABTestImpl;
import com.linghuyong.bookstore.infrastructure.ABTest.IABTest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

// 商店页面的领域服务
// 这里只搭建了一个ABTest的使用场景
// 我们要做一个关于“推荐图书”的相关实验测试。
// 假设在运营编辑推荐的图书池中，用算法根据用户行为特征输出的推荐排序，比默认推荐排序会提高推荐图书池里的图书下单转换率。
// 提前在实验平台编辑好流量分层策略，并在平台用“sort”int变量配置了实验组和对照组用户的排序方案了, 0是默认排序，1是算法排序。
@Service
public class PageDomainService {

    private final IABTest abTest;

    public PageDomainService() {
        // 假设有一个流量分发服务器的配置
        String abTestServerConfig = "";
        // 假设实验id是1
        long experimentId = 1;
        this.abTest = new ABTestImpl(abTestServerConfig, experimentId);
    }


    public List<Long> getRecommend(long userId) {
        int sort = this.abTest.fetchTestVariableAsInt(userId, "sort");
        return switch (sort) {
            case 1 -> sortByAlgorithm(userId);
            default -> getDefaultSort();
        };
    }

    private List<Long> getDefaultSort() {
        return Arrays.asList(1L, 2L, 3L);
    }

    private List<Long> sortByAlgorithm(long userId) {
        // 调用算法服务的接口，获取千人前面的排序
        return Arrays.asList(3L, 2L, 1L);
    }
}
