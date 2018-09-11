package com.yongjun.stock.util;

import java.io.UnsupportedEncodingException;

/**
 * Created by lyl on 2016/9/11.
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils{
    public static final String EMPTY = "";

    public StringUtils() {
    }

    public static String replaceWithBlank(String sourceString, String replacedSymbol) {
        return sourceString.replace(replacedSymbol, "");
    }

    public static boolean isRangeLength(String s, int maxLength) {
        return getStringLength(s) > maxLength;
    }

    public static int getStringLength(String s) {
        int valueLength = 0;
        if(s == null) {
            return valueLength;
        } else {
            String chinese = "[一-龥]";

            for(int i = 0; i < s.length(); ++i) {
                String temp = s.substring(i, i + 1);
                if(temp.matches(chinese)) {
                    valueLength += 2;
                } else {
                    ++valueLength;
                }
            }

            return valueLength;
        }
    }

    public static String full2HalfChange(String QJstr) {
        if(QJstr == null) {
            return null;
        } else {
            StringBuffer outStrBuf = new StringBuffer("");
            String Tstr = "";
            Object b = null;

            for(int i = 0; i < QJstr.length(); ++i) {
                Tstr = QJstr.substring(i, i + 1);

                try {
                    if(Tstr.equals("　")) {
                        outStrBuf.append(" ");
                    } else {
                        byte[] var7 = Tstr.getBytes("unicode");
                        if(var7[2] == -1) {
                            var7[3] = (byte)(var7[3] + 32);
                            var7[2] = 0;
                            outStrBuf.append(new String(var7, "unicode"));
                        } else {
                            outStrBuf.append(Tstr);
                        }
                    }
                } catch (UnsupportedEncodingException var6) {
                    outStrBuf.append(Tstr);
                }
            }

            return outStrBuf.toString();
        }
    }
}
