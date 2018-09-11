package com.yongjun.stock.Filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by lyl on 2017/4/26.
 */
public class HttpServletRequestReplacedFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getMethod().equals("POST") && (request.getContentType().contains("application/json") || request.getContentType().contains("text/json"))){
            filterChain.doFilter(new CacheHttpServletRequestWrapper((HttpServletRequest) servletRequest), servletResponse);
        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }

}
