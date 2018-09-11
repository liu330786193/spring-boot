package com.yongjun.stock.util.Excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by lyl on 2016/3/28.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelInfo {
    //文件导出名称
    String fileName() default "";

    //sheet 名称
    String sheetName() default "";


}
