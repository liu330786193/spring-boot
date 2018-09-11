package com.yongjun.stock.util.Excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by lyl on 2016/3/28.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {

    String value() default "";

    boolean required() default false;//Excel导入时，表头是否必填参数

    int sort() default 0;//表头顺序
}
