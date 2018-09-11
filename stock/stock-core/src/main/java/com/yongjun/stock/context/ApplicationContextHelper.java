package com.yongjun.stock.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by lyl on 2016/9/11.
 */
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext context;

    public ApplicationContextHelper() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
