package com.yongjun.stock.util;

/**
 * Created by lyl on 2016/9/11.
 */
public enum SignType {
    MD5("MD5"),
    RSA("RSA"),
    DSA("DSA");

    private String value;

    private SignType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static boolean isSignType(String name) {
        SignType[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            SignType signType = var1[var3];
            if(signType.getValue().equals(name)) {
                return true;
            }
        }

        return false;
    }
}
