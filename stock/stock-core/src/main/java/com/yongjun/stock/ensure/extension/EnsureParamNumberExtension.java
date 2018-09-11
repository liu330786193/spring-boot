package com.yongjun.stock.ensure.extension;

import com.yongjun.stock.ensure.EnsureParam;
import com.yongjun.stock.exception.CExceptionFactory;

/**
 * Created by lyl on 2016/9/11.
 */
public class EnsureParamNumberExtension extends EnsureParam<Number> {
    private Double number;

    public EnsureParamNumberExtension(Number number) {
        super(number);
        if(number != null) {
            this.number = Double.valueOf(number.doubleValue());
        }

    }

    public EnsureParamNumberExtension isGt(Number comparedNumber, String errorCode) {
        if(this.number.doubleValue() <= comparedNumber.doubleValue()) {
            throw CExceptionFactory.create(errorCode, new String[0]);
        } else {
            return this;
        }
    }

    public EnsureParamNumberExtension isNotGt(Number comparedNumber, String errorCode) {
        if(this.number.doubleValue() > comparedNumber.doubleValue()) {
            throw CExceptionFactory.create(errorCode, new String[0]);
        } else {
            return this;
        }
    }

    public EnsureParamNumberExtension isLt(Number comparedNumber, String errorCode) {
        if(this.number.doubleValue() >= comparedNumber.doubleValue()) {
            throw CExceptionFactory.create(errorCode, new String[0]);
        } else {
            return this;
        }
    }

    public EnsureParamNumberExtension isNotLt(Number comparedNumber, String errorCode) {
        if(this.number.doubleValue() < comparedNumber.doubleValue()) {
            throw CExceptionFactory.create(errorCode, new String[0]);
        } else {
            return this;
        }
    }

    public EnsureParamNumberExtension isEqual(Number comparedNumber, String errorCode) {
        if(this.number.doubleValue() != comparedNumber.doubleValue()) {
            throw CExceptionFactory.create(errorCode, new String[0]);
        } else {
            return this;
        }
    }

    public EnsureParamNumberExtension isNotEqual(Number comparedNumber, String errorCode) {
        if(this.number.doubleValue() == comparedNumber.doubleValue()) {
            throw CExceptionFactory.create(errorCode, new String[0]);
        } else {
            return this;
        }
    }

    public EnsureParamNumberExtension isNotNull(String errorCode) {
        if(this.number == null) {
            throw CExceptionFactory.create(errorCode, new String[0]);
        } else {
            return this;
        }
    }

    public EnsureParamNumberExtension isEqualTo(Number obj, String errorCode) {
        return this.isEqual(obj, errorCode);
    }

    public EnsureParamNumberExtension isNotEqualTo(Number obj, String errorCode) {
        return this.isNotEqual(obj, errorCode);
    }
}

