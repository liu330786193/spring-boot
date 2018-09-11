package com.yongjun.stock.util;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by lyl on 2016/9/11.
 */
public class MapUtils {
    private static final String HTTP_DELIMITER = "&";

    public MapUtils() {
    }

    public static Map<String, String> convertValueToString(Map params) {
        HashMap tempMap = new HashMap();
        Iterator var2 = params.keySet().iterator();

        while(true) {
            while(var2.hasNext()) {
                Object key = var2.next();
                Object value = params.get(key);
                if(value != null && value.getClass().isArray()) {
                    tempMap.put(key.toString(), (String) Array.get(value, 0));
                } else if(value != null) {
                    tempMap.put(key.toString(), value.toString());
                } else {
                    tempMap.put(key.toString(), "");
                }
            }

            return tempMap;
        }
    }

    public static String convertMapToHttpString(Map<String, String> map) {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator var2 = map.keySet().iterator();

        while(var2.hasNext()) {
            String key = (String)var2.next();
            String delimiter = stringBuffer.length() > 0?"&":"";
            stringBuffer.append(delimiter).append(key).append("=").append((String)map.get(key));
        }

        return stringBuffer.toString();
    }
}
