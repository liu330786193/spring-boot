package com.yongjun.stock.resolver;

import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yongjun.stock.context.CContext;
import com.yongjun.stock.exception.CBusinessException;
import com.yongjun.stock.exception.CEmptyRequestBodyException;
import com.yongjun.stock.exception.CExceptionFactory;
import com.yongjun.stock.exception.CRuntimeException;
import com.yongjun.stock.output.Result;
import com.yongjun.stock.util.ResponseUtils;
import com.yongjun.stock.util.StringUtils;
import com.yongjun.stock.util.RegexUtils;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.Set;

/**
 * Created by lyl on 2016/9/13.
 */
public class CExceptionResolver extends SimpleMappingExceptionResolver {

    private final String WEB_ERROR_LOG_PATTERN = ">>>web请求[%s]发生异常:%s";

    private final String JSON_ERROR_LOG_PATTERN = ">>>[HashCode=%s]请求[%s]发生异常:%s";

    private final SerializerFeature[] serializerFeatures = new SerializerFeature[]{
            SerializerFeature.QuoteFieldNames,
    };

    private String defaultPath;
    private Properties exceptionMappings;
    private Charset charset = Charset.forName("UTF-8");

    public void setDefaultPath(String defaultPath) {
        this.defaultPath = defaultPath;
    }

    public void setExceptionMappings(Properties exceptionMappings) {
        this.exceptionMappings = exceptionMappings;
    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        boolean isJsonResponse = isJsonRequest(request,handler);
        if (isJsonResponse) {
            return jsonExceptionHandler(response, ex);
        }
        return generalExceptionHandler(request, response, ex);
    }

    /**
     * 处理非ajax异常
     */
    private ModelAndView generalExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        String errorLog = String.format(WEB_ERROR_LOG_PATTERN, request.getServletPath(), ex.getMessage());
        logger.error(errorLog, ex);
        Result result = getExceptionResult(response, ex);
        ModelAndView mav = new ModelAndView();
        mav.addObject("result", result);
        String name = ex.getClass().getName();
        String path = exceptionMappings.getProperty(name);
        String wrapperPath = StringUtils.isEmpty(path) ? defaultPath : path;
        mav.setViewName(wrapperPath);
        return mav;
    }

    /**
     * 处理ajax异常
     */
    public ModelAndView jsonExceptionHandler(HttpServletResponse response, Exception e) {
        ServletOutputStream stream = null;
        try {
            CContext context = CContext.getCurrentContext();
            if (!context.isInited()) {
                logger.error(e);
            } else {
                String errorLog = String.format(JSON_ERROR_LOG_PATTERN, context.getRequestHashCode(), context.getServiceUrl(), e.getMessage());
                logger.error(errorLog, e);
            }

            Result result = getExceptionResult(response, e);

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Cache-Control", "no-cache, must-revalidate");
            stream = response.getOutputStream();

            byte[] jsonBytes = JSON.toJSONBytes(result, serializerFeatures);

            if (context.isInited() && context.isOpenApiRequest()) {
                setSignToHeader(response, jsonBytes, context);
            }

            stream.write(jsonBytes);
        } catch (Exception e1) {
            //do nothing
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            CContext.clearCurrentContext();
        }
        return new ModelAndView();
    }

    /**
     * 将部分异常处理成BusinessException
     */
    private Result getExceptionResult(HttpServletResponse response, Exception e) {
        Exception ex = e;
        if (e instanceof MethodArgumentNotValidException) {
            ObjectError objectError = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().get(0);
            String validationMsg = objectError.getDefaultMessage();
            ex = handleErrorMessage(validationMsg);
        } else if (e instanceof BindException) {
            ObjectError objectError = ((BindException) e).getBindingResult().getAllErrors().get(0);
            String validationMsg = objectError.getDefaultMessage();
            ex = handleErrorMessage(validationMsg);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            HttpRequestMethodNotSupportedException exception = (HttpRequestMethodNotSupportedException) e;
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            ex = CExceptionFactory.create("F_WEBKITS_COMMON_1004", exception.getMethod());
        } else if (e instanceof CEmptyRequestBodyException) {
            ex = CExceptionFactory.create("F_WEBKITS_COMMON_1001");
        } else if (e instanceof RpcException && !(e instanceof CRuntimeException)) {
            Throwable causeEx = e.getCause();
            if (causeEx instanceof ConstraintViolationException) {
                ConstraintViolationException ve = (ConstraintViolationException) e.getCause();
                Set<ConstraintViolation<?>> violations = ve.getConstraintViolations();
                String validationMsg = "";
                for (ConstraintViolation c : violations) {
                    validationMsg = c.getMessage();
                }
                ex = handleErrorMessage(validationMsg);
            }
        } else if (e instanceof JSONException) {
            ex = CExceptionFactory.create("F_WEBKITS_COMMON_1002", e.getMessage().replace("\"", ""));
        } else if (e instanceof MissingServletRequestParameterException) {
            ex = CExceptionFactory.create("F_WEBKITS_COMMON_1003", e.getMessage().replace("\"", ""));
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            ex = CExceptionFactory.create("F_WEBKITS_COMMON_1002", e.getMessage().replace("\"", ""));
        }
        return getFaultResponse(ex);
    }

    /**
     * 判断是否标有responseBody注解请求
     */
    private boolean isJsonRequest(HttpServletRequest request,Object handler) {
        if(String.valueOf(request.getHeader("Accept")).indexOf("application/json") > -1 || String.valueOf(request.getHeader("Content-Type")).indexOf("application/json") > -1 ){
            return true;
        }
        if (handler == null) {
            return handler != null;
        }
        HandlerMethod method = (HandlerMethod) handler;
        ResponseBody body = method.getMethodAnnotation(ResponseBody.class);
        return body != null;
    }

    private CBusinessException handleErrorMessage(String validationMsg) {
        if (RegexUtils.isErrorCode(validationMsg)) {
            return CExceptionFactory.create(validationMsg);
        }

        String code = "F_WEBKITS_COMMON_1003";
        String errMsg = validationMsg;
        return CExceptionFactory.create(code, errMsg);
    }

    private Result getFaultResponse(Exception e) {
        if (e instanceof CBusinessException) {
            return ResponseUtils.getXBusinessResult((CBusinessException) e);
        } else if (e instanceof ValidationException) {
            if (e.getCause() instanceof CBusinessException) {
                return ResponseUtils.getXBusinessResult(((CBusinessException) e.getCause()));
            } else {
                return ResponseUtils.getFaultResult();
            }
        } else if (e instanceof Exception) {
            return ResponseUtils.getFaultResult();
        }
        return ResponseUtils.getUnknownResult();
    }

    /**
     * 设置签名相关的http头
     */
    private void setSignToHeader(HttpServletResponse response, byte[] jsonBytes, CContext context) {
        if (!context.isSkipCheckSignature()) {
            try {
                String sign = getResponseSign(jsonBytes, context);
                response.addHeader("sign", sign);
                response.addHeader("signType", context.getSignType().getValue());
            } catch (Exception e) {
                // Do nothing
            }
        }
    }

    /**
     * response返回的签名
     */
    private String getResponseSign(byte[] jsonBytes, CContext context) {
        if (jsonBytes == null) {
            throw CExceptionFactory.create("F_WEBKITS_RETURN_1001");
        }

        return null;
    }
}
