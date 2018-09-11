package com.yongjun.stock.util;

import com.yongjun.stock.output.Result;
import com.yongjun.stock.exception.CBusinessException;

/**
 * Created by lyl on 2016/9/11.
 */
public class ResponseUtils {
    private static final String FAULT_CODE = "-1";
    private static final String SUCCESS_CODE = "0";
    private static final String SUCCESS_MSG = "OK";
    private static final String FAULT_MSG = "服务器内部错误";
    private static final String UNKNOWN_MSG = "服务器内部未知错误";

    public ResponseUtils() {
    }

    public static Result getXBusinessResult(CBusinessException ex) {
        return new Result(ex.getXCode(), ex.getMessageWithoutCode());
    }

    public static Result getFaultResult() {
        return new Result("-1", "服务器内部错误");
    }

    public static Result getSuccessResult(Object obj) {
        return new Result("0", "OK", obj);
    }

    public static Result getUnknownResult() {
        return new Result("-1", "服务器内部未知错误");
    }
}
