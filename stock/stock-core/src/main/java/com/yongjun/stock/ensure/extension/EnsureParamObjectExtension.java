package com.yongjun.stock.ensure.extension;

import com.yongjun.stock.ensure.EnsureParam;
import com.yongjun.stock.exception.CExceptionFactory;

/**
 * Created by lyl on 2016/9/11.
 */
public class EnsureParamObjectExtension extends EnsureParam<Object> {
    private boolean isSatisfied;

    public EnsureParamObjectExtension(Object o) {
        super(o);
    }

    public EnsureParamObjectExtension isNotNull(String errorCode) {
        if(this.tObjct == null) {
            throw CExceptionFactory.create(errorCode, new String[0]);
        } else {
            return this;
        }
    }

    public <TObject> EnsureParamObjectExtension isEqualTo(TObject obj, String errorCode) {
        this.isSatisfied = obj == this.tObjct || obj != null && this.tObjct != null && this.tObjct.equals(obj);
        if(!this.isSatisfied) {
            throw CExceptionFactory.create(errorCode, new String[0]);
        } else {
            return this;
        }
    }

    public <TObject> EnsureParamObjectExtension isNotEqualTo(TObject obj, String errorCode) {
        if(obj != this.tObjct) {
            if(obj != null) {
                this.isSatisfied = !obj.equals(this.tObjct);
            } else if(this.tObjct != null) {
                this.isSatisfied = !this.tObjct.equals(obj);
            } else {
                this.isSatisfied = false;
            }
        } else {
            this.isSatisfied = false;
        }

        if(!this.isSatisfied) {
            throw CExceptionFactory.create(errorCode, new String[0]);
        } else {
            return this;
        }
    }
}
