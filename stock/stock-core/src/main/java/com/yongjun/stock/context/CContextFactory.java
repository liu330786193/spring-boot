package com.yongjun.stock.context;

/**
 * Created by lyl on 2016/9/13.
 */
public class CContextFactory {

    private static final String XPOS = "xpos";
    private static final String XGENERAL = "general";


    public static CContext createContext(String str) {
        switch (str) {
            case XPOS:
                return CContext.createContext();
            case XGENERAL:
                return CGeneralContext.createXGeneralContext();
            default:
                return CWebContext.createXWebContext();
        }

    }
}
