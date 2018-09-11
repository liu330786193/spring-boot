package com.yongjun.stock.util.Excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lyl on 2016/4/11.
 */
public class CellValueUtils {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    /**
     * 单元格内容解析成bean对应的属性值
     */
    public static Object cellValueParseObject(Cell cell, Class targetClass) throws Exception {
        Assert.notNull(targetClass, "Target class must not be null");
        if (cell == null) {
            return null;
        }

        String trimmed = cellValueToString(cell);

        if (StringUtils.isNotBlank(trimmed)) {
            trimmed = trimmed.trim();
        } else {
            return null;
        }

        if (targetClass.equals(String.class)) {
            return trimmed;
        } else if (targetClass.equals(Byte.class)) {
            return (isHexNumber(trimmed) ? Byte.decode(trimmed) : Byte.valueOf(trimmed));
        } else if (targetClass.equals(Short.class)) {
            return (isHexNumber(trimmed) ? Short.decode(trimmed) : Short.valueOf(trimmed));
        } else if (targetClass.equals(Integer.class)) {
            return (isHexNumber(trimmed) ? Integer.decode(trimmed) : Integer.valueOf(trimmed));
        } else if (targetClass.equals(Long.class)) {
            return (isHexNumber(trimmed) ? Long.decode(trimmed) : Long.valueOf(trimmed));
        } else if (targetClass.equals(BigInteger.class)) {
            return (isHexNumber(trimmed) ? decodeBigInteger(trimmed) : new BigInteger(trimmed));
        } else if (targetClass.equals(Float.class)) {
            return Float.valueOf(trimmed);
        } else if (targetClass.equals(Double.class)) {
            return Double.valueOf(trimmed);
        } else if (targetClass.equals(BigDecimal.class) || targetClass.equals(Number.class)) {
            return new BigDecimal(trimmed);
        } else if (targetClass.equals(Date.class)) {
            //todo
            return sdf.parse(trimmed);
        } else {//其他类型不解析
            return null;
        }
    }

    /**
     * 单元格内容用字符串表示
     *
     * @param cell
     * @return
     */
    public static String cellValueToString(Cell cell) {
        if (cell == null) {
            return null;
        }
        String value = null;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date theDate = cell.getDateCellValue();
                    value = sdf.format(theDate);
                } else {
                    value = NumberToTextConverter.toText(cell.getNumericCellValue());
                }
                break;
            default:
                value = null;
        }

        if (StringUtils.isNotBlank(value)) {
            value.trim();
        }
        return value;
    }

    /**
     * Determine whether the given value String indicates a hex number, i.e. needs to be
     * passed into {@code Integer.decode} instead of {@code Integer.valueOf} (etc).
     */
    private static boolean isHexNumber(String value) {
        int index = (value.startsWith("-") ? 1 : 0);
        return (value.startsWith("0x", index) || value.startsWith("0X", index) || value.startsWith("#", index));
    }

    /**
     * Decode a {@link BigInteger} from a {@link String} value.
     * Supports decimal, hex and octal notation.
     *
     * @see BigInteger#BigInteger(String, int)
     */
    private static BigInteger decodeBigInteger(String value) {
        int radix = 10;
        int index = 0;
        boolean negative = false;

        // Handle minus sign, if present.
        if (value.startsWith("-")) {
            negative = true;
            index++;
        }

        // Handle radix specifier, if present.
        if (value.startsWith("0x", index) || value.startsWith("0X", index)) {
            index += 2;
            radix = 16;
        } else if (value.startsWith("#", index)) {
            index++;
            radix = 16;
        } else if (value.startsWith("0", index) && value.length() > 1 + index) {
            index++;
            radix = 8;
        }

        BigInteger result = new BigInteger(value.substring(index), radix);
        return (negative ? result.negate() : result);
    }

    /*public static void main(String[] args) throws Exception {
        AnnotationFormatterFactory annotationFormatterFactory = new DateTimeFormatAnnotationFormatterFactory();
        Class<RecruitShopExcelVO> clazz = RecruitShopExcelVO.class;
        Field field = clazz.getDeclaredField("time");
        Class<?> fieldType = field.getType();

        DateTimeFormat annotation = field.getAnnotation(DateTimeFormat.class);
        Parser<?> parser = annotationFormatterFactory.getParser(annotation, fieldType);
        *//**yyyy/MM/dd*//*
        Object parse = parser.parse("2023/12/23", Locale.CHINESE);
        System.out.println(parse);

    }*/
}
