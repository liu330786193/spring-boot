package com.yongjun.stock.ensure.extension;

import com.yongjun.stock.ensure.EnsureParam;
import com.yongjun.stock.exception.CExceptionFactory;
import com.yongjun.stock.util.StringUtils;

/**
 * Created by lyl on 2016/9/11.
 */
public class EnsureParamStringExtension extends EnsureParam<Object> {
    private String string;

    public EnsureParamStringExtension(String string) {
        super(string);
        this.string = string;
    }

    public EnsureParamStringExtension isNotNull(String errorCode) {
        if(this.string == null) {
            throw CExceptionFactory.create(errorCode, new String[0]);
        } else {
            return this;
        }
    }

    public EnsureParamStringExtension isNotEmpty(String errorCode) {
        if(StringUtils.isEmpty(this.string)) {
            throw CExceptionFactory.create(errorCode, new String[0]);
        } else {
            return this;
        }
    }

    public EnsureParamStringExtension isNotBlank(String errorCode) {
        if(StringUtils.isBlank(this.string)) {
            throw CExceptionFactory.create(errorCode, new String[0]);
        } else {
            return this;
        }
    }

    public EnsureParamStringExtension isEqualTo(String comparedString, String errorCode) {
        if(!StringUtils.equals(this.string, comparedString)) {
            throw CExceptionFactory.create(errorCode, new String[0]);
        } else {
            return this;
        }
    }

    public EnsureParamStringExtension isNotEqualTo(String comparedString, String errorCode) {
        if(StringUtils.equals(this.string, comparedString)) {
            throw CExceptionFactory.create(errorCode, new String[0]);
        } else {
            return this;
        }
    }
}
