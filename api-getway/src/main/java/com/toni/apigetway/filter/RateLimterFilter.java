package com.toni.apigetway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import com.toni.apigetway.exception.RateLimiterException;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * demo: 限流 过滤器，使用到 token桶 算法
 *
 * @author ：qinhy
 * @date ：Created in 2019/3/29 0029 17:10
 */
@Component
public class RateLimterFilter extends ZuulFilter {
    // 谷歌封装的 token桶
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER - 1; // 在所有的过滤之前
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 尝试获取token，获取到才可以进行下面的过滤
        if(!RATE_LIMITER.tryAcquire()){
            // 这里使用的是抛出异常，还可以使用设置响应头的方式
            throw new RateLimiterException();
        }

        return null;
    }
}
