package com.yongjun.stock.ensure;

import com.yongjun.stock.ensure.extension.*;

import java.util.Collection;

/**
 * Created by lyl on 2016/9/11.
 */
public class Ensure {
    public Ensure() {
    }

    public static EnsureParamObjectExtension that(Object tObject) {
        return new EnsureParamObjectExtension(tObject);
    }

    public static EnsureParamBooleanExtension that(boolean tObject) {
        return new EnsureParamBooleanExtension(Boolean.valueOf(tObject));
    }

    public static <TObject extends Collection> EnsureParamCollectionExtension that(TObject tObject) {
        return new EnsureParamCollectionExtension(tObject);
    }

    public static <TObject extends Boolean> EnsureParamBooleanExtension that(TObject tObject) {
        return new EnsureParamBooleanExtension(tObject);
    }

    public static <TObject extends Number> EnsureParamNumberExtension that(TObject tObject) {
        return new EnsureParamNumberExtension(tObject);
    }

    public static <TObject extends Enum> EnsureParamEnumExtension that(TObject tObject) {
        return new EnsureParamEnumExtension(tObject);
    }

    public static EnsureParamStringExtension that(String tObject) {
        return new EnsureParamStringExtension(tObject);
    }
}
