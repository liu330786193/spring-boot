package com.yongjun.stock.ensure;

/**
 * Created by lyl on 2016/9/11.
 */
public class EnsureParam<TObjct> {
    protected TObjct tObjct;

    public EnsureParam(TObjct tObjct) {
        this.tObjct = tObjct;
    }
}
