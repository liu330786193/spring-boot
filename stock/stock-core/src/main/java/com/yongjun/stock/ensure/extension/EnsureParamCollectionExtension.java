package com.yongjun.stock.ensure.extension;

import com.yongjun.stock.exception.CExceptionFactory;

import java.util.Collection;

/**
 * Created by lyl on 2016/9/11.
 */
public class EnsureParamCollectionExtension extends EnsureParamObjectExtension {

    private Collection collection;

    public EnsureParamCollectionExtension(Collection collection) {
        super(collection);
        this.collection = collection;
    }

    public EnsureParamCollectionExtension isNotEmpty(String errorCode) {
        if(this.collection != null && this.collection.size() > 0) {
            return this;
        } else {
            throw CExceptionFactory.create(errorCode, new String[0]);
        }
    }

    public EnsureParamCollectionExtension isNotNull(String errorCode) {
        if(this.collection == null) {
            throw CExceptionFactory.create(errorCode, new String[0]);
        } else {
            return this;
        }
    }

    public EnsureParamCollectionExtension isEqualTo(Collection obj, String errorCode) {
        if(this.collection == null) {
            if(obj != null) {
                throw CExceptionFactory.create(errorCode, new String[0]);
            }
        } else if(!this.collection.equals(obj)) {
            throw CExceptionFactory.create(errorCode, new String[0]);
        }

        return this;
    }

    public EnsureParamCollectionExtension isNotEqualTo(Collection obj, String errorCode) {
        if(this.collection == null) {
            if(obj == null) {
                throw CExceptionFactory.create(errorCode, new String[0]);
            }
        } else if(this.collection.equals(obj)) {
            throw CExceptionFactory.create(errorCode, new String[0]);
        }

        return this;
    }
}
