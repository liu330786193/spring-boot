package com.yongjun.stock.interceptor;


import com.alibaba.fastjson.JSON;
import com.yongjun.stock.util.StringUtils;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lyl on 2017/10/25.
 */
public class MethodTimeInterceptor implements MethodInterceptor {

    private final static Logger LOGGER = LoggerFactory.getLogger(MethodTimeInterceptor.class);
    private long warnWhenOverTime = 5 * 1000L;//5秒

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        StopWatch clock = new StopWatch();
        clock.start(); //计时开始
        Object result = invocation.proceed();
        clock.stop();  //计时结束
        try {
            //方法参数类型，转换成简单类型
            Class[] params = invocation.getMethod().getParameterTypes();
            //参数值
            Object[] parmasValue = invocation.getArguments();
            String[] simpleParams = new String[params.length];
            for (int i = 0; i < params.length; i++) {
                simpleParams[i] = params[i].getSimpleName();
            }
            if (clock.getTime() > warnWhenOverTime) {
                LOGGER.error("[methodExecutionTime]超出{}ms,总耗时:{}",warnWhenOverTime , clock.getTime() + " ms ["
                        + invocation.getThis().getClass().getName() + "."
                        + invocation.getMethod().getName() + "("+ StringUtils.join(simpleParams,",")+")] 参数:"+arrayToString(parmasValue)
                        + " 结果:"+arrayToString(new Object[]{result}));
            }else{
                LOGGER.info("[methodExecutionTime]总耗时:{}", clock.getTime() + " ms ["
                        + invocation.getThis().getClass().getName() + "."
                        + invocation.getMethod().getName() + "("+ StringUtils.join(simpleParams,",")+")] 参数:"+arrayToString(parmasValue)
                        + " 结果:"+arrayToString(new Object[]{result}));
            }
        }catch (Exception ex){}
        return result;
    }

    /**
     * 解析参数
     * @param a
     * @return
     */
    private static String arrayToString(Object[] a) {
        if(a == null){
            return "null";
        }
        int iMax = a.length - 1;
        if(iMax == -1){
            return "[]";
        }
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            if (a[i] instanceof Object[]) {
                b.append(arrayToString((Object[]) a[i]));
            } else {
                Object obj = a[i];
                if(isWapper(obj)){
                    b.append(String.valueOf(a[i]));
                }else{
                    b.append(JSON.toJSONString(obj));
                }
            }
            if (i == iMax){
                return b.append(']').toString();
            }
            b.append(", ");
        }
    }

    /**
     * 判断是否是包装类
     * @param obj
     * @return
     */
    public static boolean isWapper(Object obj){
        boolean bol = false;
        try {
            bol = ((Class) obj.getClass().getField("TYPE").get(null)).isPrimitive();
        }catch (Exception ex){}
        return bol;
    }

}
