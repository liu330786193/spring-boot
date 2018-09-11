package com.yongjun.stock.exception;

import com.yongjun.stock.context.ApplicationContextHelper;

/**
 * Created by lyl on 2016/9/11.
 */
public class CExceptionFactory {
    private static ExceptionDefinitions exceptionDefinitions;

    public CExceptionFactory() {
    }

    public static CBusinessException create(String errorCode, String... args) {
        String exceptionPattern = getExceptionDefinitions().getExceptionMessage(errorCode);
        if(args.length > 0) {
            String errorMsg = String.format(exceptionPattern, args);
            return new CBusinessException(errorCode, errorMsg);
        } else {
            return new CBusinessException(errorCode, exceptionPattern);
        }
    }

    private static ExceptionDefinitions getExceptionDefinitions() {
        if(exceptionDefinitions == null) {
            exceptionDefinitions = (ExceptionDefinitions) ApplicationContextHelper.getContext().getBean(ExceptionDefinitions.class);
        }

        return exceptionDefinitions;
    }
}
