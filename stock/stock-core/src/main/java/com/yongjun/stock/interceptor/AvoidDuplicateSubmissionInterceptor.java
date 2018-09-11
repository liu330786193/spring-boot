package com.yongjun.stock.interceptor;

import com.alibaba.fastjson.JSON;
import com.yongjun.stock.annotation.AvoidDuplicateSubmission;
import com.yongjun.stock.exception.CBusinessException;
import com.yongjun.stock.exception.CExceptionFactory;
import com.yongjun.stock.output.Result;
import com.yongjun.stock.util.TokenGenerator;
import com.yongjun.stock.util.ResponseUtils;
import com.yongjun.stock.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Method;

/**
 * 防止重复提交过滤器
 * Created by lyl on 2016/9/20.
 */
public class AvoidDuplicateSubmissionInterceptor extends CInterceptor {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(AvoidDuplicateSubmissionInterceptor.class);

    private Object clock = new Object();

    /**
     * 拦截方法，添加or验证token
     */
    @Override
    public boolean internalPreHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return super.preHandle(request, response, handler);
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        /**获取防重复提交注解 */
        AvoidDuplicateSubmission annotation = method.getAnnotation(AvoidDuplicateSubmission.class);
        if (annotation == null) {
            return true;
        }

        /**校验TOKEN */
        boolean needRemoveToken = annotation.needRemoveToken();
        String pageUrl = annotation.pageUrl();
        if (needRemoveToken) {
            return handleToken(request, response, pageUrl);
        }
        /**生成TOKEN */
        boolean needSaveSession = annotation.needSaveToken();
        if (needSaveSession) {
            TokenGenerator.generate(request);
        }
        return true;
    }

    protected boolean handleToken(HttpServletRequest request, HttpServletResponse response, String pageUrl) throws Exception {
        synchronized (clock) {
            if (!TokenGenerator.validToken(request)) {
                logger.debug("未通过验证...");
                String requestType = request.getHeader("X-Requested-With");
                if (StringUtils.isNotBlank(requestType)) {
                    return handleInvalidToken(request, response);
                }
                if (StringUtils.isBlank(pageUrl)) {
                    throw CExceptionFactory.create("F_WEBKITS_SIGN_1004");
                }
                response.sendRedirect(pageUrl);
                return false;
            }
        }
        logger.debug("通过验证...");
        return true;
    }

    /**
     * 当出现一个非法令牌时调用
     */
    protected boolean handleInvalidToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /**设置返回数据头 */
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        OutputStream os = response.getOutputStream();
        try {
            /**创建指定异常 */
            CBusinessException ex = CExceptionFactory.create("F_WEBKITS_SIGN_1004");
            Result result = ResponseUtils.getXBusinessResult(ex);

            /**内容写入输出流 */
            os.write(JSON.toJSONBytes(result));
        } finally {
            if (os != null) {
                os.close();
            }
        }
        return false;
    }
}