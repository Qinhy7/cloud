package com.toni.apigetway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.toni.apigetway.constant.CookieConstant;
import com.toni.apigetway.constant.RedisConstant;
import com.toni.apigetway.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * 卖家端鉴权器
 *
 * @author ：qinhy
 * @date ：Created in 2019/3/29 0029 17:10
 */
@Component
public class AuthSellerFilter extends ZuulFilter {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        if("/order/order/finish".equals(request.getRequestURI())){
            return true;
            // 拦截指定请求的uri
        }

        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        /**
         * /order/create    买家端可以访问     cookie中有openid
         * /order/finish      卖家段访问          cookie中有token，redis中有值
         * /product/list    所有都可访问
         */

        Cookie cookie = CookieUtil.get(request, CookieConstant.token);
        if(cookie != null || StringUtils.isEmpty(cookie.getValue())
            || StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.token, cookie.getValue())))){
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }

        return null;
    }
}
