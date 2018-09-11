package com.yongjun.stock.interceptor;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.yongjun.stock.context.CContext;
import com.yongjun.stock.context.CContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;

/**
 * 防止重复提交过滤器
 * Created by lyl on 2016/9/20.
 */
public class CInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(CInterceptor.class);
    private List<CInterceptor> CInterceptorList;
    private List<String> ignoreList;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public CInterceptor() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(this.isIgnored(request.getRequestURI())) {
            return true;
        } else if(this.isAccessDenied(request, handler)) {
            return false;
        } else {
            CContext cContext = CContextFactory.createContext(request.getServletPath());
            cContext.init(request, handler);
            if (CollectionUtils.isNotEmpty(this.CInterceptorList)) {
                Iterator var5 = this.CInterceptorList.iterator();

                while (var5.hasNext()) {
                    CInterceptor interceptor = (CInterceptor) var5.next();
                    if (!interceptor.internalPreHandle(request, response, handler)) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    protected boolean internalPreHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    protected void internalAfterCompletion(HttpServletResponse response, Exception e) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
        CContext.clearCurrentContext();
    }

    public void setCInterceptorList(List<CInterceptor> CInterceptorList) {
        this.CInterceptorList = CInterceptorList;
    }

    public void setIgnoreList(List<String> ignoreList) {
        this.ignoreList = ignoreList;
    }

    private boolean isAccessDenied(HttpServletRequest request, Object handler) {
        if(!(handler instanceof HandlerMethod)) {
            return false;
        } else {
            ResponseBody responseBody = (ResponseBody)((HandlerMethod)handler).getMethodAnnotation(ResponseBody.class);
            if(responseBody != null && String.valueOf(request.getHeader("Accept")).indexOf("text/html") != -1) {
                if(logger.isInfoEnabled()) {
                    logger.info(" http Accept error  Access Denied. url:{}", request.getRequestURL());
                }
                return true;
            } else {
                return false;
            }
        }
    }

    private boolean isIgnored(String uri) {
        if(this.ignoreList != null) {
            Iterator var2 = this.ignoreList.iterator();

            while(var2.hasNext()) {
                String ignoreUriPattern = (String)var2.next();
                if(this.antPathMatcher.match(ignoreUriPattern, uri)) {
                    return true;
                }
            }
        }
        return false;
    }
}
