package com.toni.apigetway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * demo: 在访问任何资源时，需要携带 token 访问限制
 * @author ：qinhy
 * @date ：Created in 2019/3/29 0029 16:48
 */
@Component
public class TokenFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return PRE_TYPE; // 前置过滤
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1; // 值越小，越靠前
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 获取request
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();
        String token = httpServletRequest.getAttribute("token").toString();

        if(StringUtils.isEmpty(token)){
            requestContext.setSendZuulResponse(false); // 没有token，检验失败，不予响应
            requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED); // 401 没有权限
        }

        return null;
    }
}
