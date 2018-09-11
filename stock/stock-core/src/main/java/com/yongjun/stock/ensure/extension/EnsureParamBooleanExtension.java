package com.yongjun.stock.ensure.extension;

import com.yongjun.stock.ensure.EnsureParam;
import com.yongjun.stock.exception.CExceptionFactory;

/**
 * Created by lyl on 2016/9/11.
 */
public class EnsureParamBooleanExtension extends EnsureParam<Boolean> {
    private Boolean condition;

    public EnsureParamBooleanExtension(Boolean condition) {
        super(condition);
        this.condition = condition;
    }

    public EnsureParamBooleanExtension isFalse(String errorCode) {
        if(this.condition.booleanValue()) {
            throw CExceptionFactory.create(errorCode, new String[0]);
        } else {
            return this;
        }
    }

    public EnsureParamBooleanExtension isTrue(String errorCode) {
        if(!this.condition.booleanValue()) {
            throw CExceptionFactory.create(errorCode, new String[0]);
        } else {
            return this;
        }
    }

    public EnsureParamBooleanExtension isNotNull(String errorCode) {
        if(this.condition == null) {
            throw CExceptionFactory.create(errorCode, new String[0]);
        } else {
            return this;
        }
    }
}
