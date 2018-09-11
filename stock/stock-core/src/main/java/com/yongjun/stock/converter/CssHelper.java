package com.yongjun.stock.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by lyl on 2016/9/11.
 */
public class CssHelper {
    public CssHelper() {
    }

    public static String cleanXss(String jsonBody) {
        if(jsonBody == null) {
            return null;
        } else {
            StringBuilder stringBuilder = new StringBuilder();

            for(int i = 0; i < jsonBody.length(); ++i) {
                stringBuilder.append(convertChar(jsonBody.charAt(i)));
            }

            return stringBuilder.toString();
        }
    }

    private static String cleanRichTextXss(String jsonBody) {
        if(jsonBody == null) {
            return null;
        } else {
            String s1 = StringEscapeUtils.unescapeHtml4(jsonBody);
            s1 = s1.replaceAll("\u0000", "").replaceAll("&NewLine;", " ").replaceAll("&colon;", " ");
            Pattern pattern = Pattern.compile("<script>(.*?)</script>", 2);
            s1 = pattern.matcher(s1).replaceAll(" ");
            pattern = Pattern.compile("</script>", 2);
            s1 = pattern.matcher(s1).replaceAll(" ");
            pattern = Pattern.compile("<script(.*?)>", 42);
            s1 = pattern.matcher(s1).replaceAll(" ");
            pattern = Pattern.compile("<iframe", 2);
            s1 = pattern.matcher(s1).replaceAll(" ");
            pattern = Pattern.compile("<meta", 2);
            s1 = pattern.matcher(s1).replaceAll(" ");
            pattern = Pattern.compile("eval\\((.*?)\\)", 42);
            s1 = pattern.matcher(s1).replaceAll(" ");
            pattern = Pattern.compile("expression\\((.*?)\\)", 42);
            s1 = pattern.matcher(s1).replaceAll(" ");
            pattern = Pattern.compile("background(.*?)=", 42);
            s1 = pattern.matcher(s1).replaceAll("");
            pattern = Pattern.compile("alert\\((.*?)\\)", 42);
            s1 = pattern.matcher(s1).replaceAll(" ");
            pattern = Pattern.compile("javascript:", 2);
            s1 = pattern.matcher(s1).replaceAll(" ");
            pattern = Pattern.compile("vbscript:", 2);
            s1 = pattern.matcher(s1).replaceAll(" ");
            pattern = Pattern.compile("[\\s]on(.*?)=", 42);
            s1 = pattern.matcher(s1).replaceAll(" ");
            s1 = s1.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
            s1 = s1.replaceAll("\'", "&#39;");
            s1 = s1.replaceAll("base64", " ");
            return s1;
        }
    }

    public static String cleanXssForRichTextField(String jsonBody, String richTextField) {
        if(jsonBody == null) {
            return null;
        } else if(richTextField == null) {
            return cleanXss(jsonBody);
        } else {
            Map map = (Map) JSONObject.parse(jsonBody);
            List skipList = Arrays.asList(richTextField.split(","));
            Iterator var4 = map.keySet().iterator();

            while(var4.hasNext()) {
                String key = (String)var4.next();
                if(skipList.contains(key)) {
                    map.put(key, cleanRichTextXss((String)map.get(key)));
                } else {
                    map.put(key, cleanXss((String)map.get(key)));
                }
            }

            return JSON.toJSONString(map);
        }
    }

    private static String convertChar(char c) {
        switch(c) {
            case '<':
                return "&lt;";
            case '>':
                return "&gt;";
            default:
                return c + "";
        }
    }
}
