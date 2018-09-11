package com.yongjun.stock.util.Excel;

import com.yongjun.stock.util.Excel.annotation.ExcelColumn;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by lyl on 2016/1/14.
 */
@Deprecated
public class ExcelUtils<T> {
    private static final String DEFAULT_SHEETNAME = "sheet1";
    private static final String DEFAULT_FILENAME = "新建 Microsoft Excel 97-2003 工作表";

    protected String filename;

    protected String sheetname;

    protected List<ExcelHeader> headers = new ArrayList<>();

    protected List<T> dataList = new ArrayList<>();

    HSSFCellStyle titleStyle;

    protected HSSFCellStyle dataStyle;

    protected HSSFWorkbook wb;

    protected Sheet sheet;

    protected float heightInPoints;

    protected int rowIndex; // 行号

    protected int[] columnWidths;

    /**
     * 导出Excel
     */
    public ExcelUtils(String sheetname, List<ExcelHeader> headers, List dataList) {
        this.headers = headers;
        this.sheetname = sheetname;
        this.dataList = dataList;
        init();
    }

    /**
     * 只下载模版，没有内容
     */
    public ExcelUtils(String sheetname, List<ExcelHeader> headers) {
        this.sheetname = sheetname;
        this.headers = headers;
    }

    private void init() {
        wb = new HSSFWorkbook();
        sheet = wb.createSheet(sheetname);
        titleStyle = wb.createCellStyle();
        dataStyle = wb.createCellStyle();
        //设置列宽
        columnWidths = new int[headers.size()];
        setTitleStyle();
        setDataStyle();
        setHeightInPoints(20);
    }


    public static List<ExcelHeader> getExcelHeader(Class<?> clazz) {
        List<ExcelHeader> headers = new ArrayList<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
            int columnIndex = excelColumn.sort();
            String headerName = excelColumn.value();
            if (excelColumn != null) {
                ExcelHeader header = new ExcelHeader(headerName, field.getName(), columnIndex);
                headers.add(header);
            }
        }
        Collections.sort(headers);
        return headers;
    }

    /**
     * 导出Excel
     */
    public Workbook exportExcel() throws Exception {
        createExcelTitle(headers);
        createData();
        calcColumWidth();
        return wb;
    }


    private void calcColumWidth() {
        for (int i = 0; i < headers.size(); i++) {
            sheet.setColumnWidth(i, columnWidths[i]);
        }
    }

    protected void createData() throws Exception {
        if (dataList == null || dataList.isEmpty()) return;
        for (int i = 0; i < dataList.size(); i++) {
            Row row = sheet.createRow(rowIndex);
            row.setHeightInPoints(heightInPoints);
            Object o = dataList.get(i);
            for (int j = 0; j < headers.size(); j++) {
                Cell cell = row.createCell(j);
                cell.setCellStyle(dataStyle);
                Object value = null;
                try {
                    value = getValueFromObject(o, headers.get(j).getImportFiled());
                } catch (Exception e) {
                    throw new Exception("获取对象属性值出错！");
                }
                if (value != null) {
                    String valueString = convertDataValueToString(value);
                    cell.setCellValue(valueString);
                    int width = getCellLength(valueString);
                    if (width > columnWidths[j]) {
                        columnWidths[j] = width;
                    }
                }
            }
            rowIndex++;
        }
    }

    /**
     * 转换为字符串
     */
    protected String convertDataValueToString(Object value) {
        if (value instanceof Date) {
            Format format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            return format.format(value);
        }

        if (value instanceof BigDecimal) {
            return "￥" + ((BigDecimal) value).setScale(2).toString();
        }

        return value.toString();
    }

    private void createExcelTitle(List<ExcelHeader> headers) {
        Row row = sheet.createRow(rowIndex++);
        row.setHeightInPoints(heightInPoints);
        for (int i = 0; i < headers.size(); i++) {
            ExcelHeader header = headers.get(i);
            Cell cell = row.createCell(i);
            //数据
            StringBuffer value = new StringBuffer();
            //是否必填
            if (null != header.getIsRequired() && header.getIsRequired() == 1) {
                value.append("*");
            }
            value.append(header.getHeaderName());
            cell.setCellValue(value.toString());
            int width = getCellLength(header.getHeaderName());
            if (width > columnWidths[i]) {
                columnWidths[i] = width;
            }
            cell.setCellStyle(titleStyle);
        }
        for (int i = 0; i < headers.size(); i++) {
            sheet.setColumnWidth(i, columnWidths[i]);
        }
    }

    protected void setTitleStyle() {
        //设置边框
        titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置背景色
        titleStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

        //设置居中
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置字体
        HSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 12); //设置字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        //font.setFontName("IMPACT");
        titleStyle.setFont(font);//选择需要用到的字体格式
        //设置自动换行
        titleStyle.setWrapText(true);
    }

    protected void setDataStyle() {
        HSSFFont dataFont = wb.createFont();
        dataFont.setFontName("宋体");
        dataFont.setFontHeightInPoints((short) 10);
        dataFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
        dataFont.setCharSet(Font.DEFAULT_CHARSET);
        dataFont.setColor(IndexedColors.BLACK.index);
        dataStyle.setAlignment(CellStyle.ALIGN_CENTER);
        dataStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        dataStyle.setFont(dataFont);
        dataStyle.setBorderTop(CellStyle.BORDER_THIN);
        dataStyle.setBorderBottom(CellStyle.BORDER_THIN);
        dataStyle.setBorderLeft(CellStyle.BORDER_THIN);
        dataStyle.setBorderRight(CellStyle.BORDER_THIN);
        dataStyle.setTopBorderColor(IndexedColors.BLACK.index);
        dataStyle.setBottomBorderColor(IndexedColors.BLACK.index);
        dataStyle.setLeftBorderColor(IndexedColors.BLACK.index);
        dataStyle.setRightBorderColor(IndexedColors.BLACK.index);
        dataStyle.setWrapText(false); // 字段换行
    }


    private Object getValueFromObject(Object o, String fieldName) throws Exception {
        String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method method = o.getClass().getMethod(methodName);

        Object value = method.invoke(o);
        return value;
    }

    /**
     * 根据内容设置单元格宽度
     */
    protected int getCellLength(String text) {
        if (text != null && text.trim().length() > 0) {
            int i = text.length() * 256 * 2;
            // int i = text.getBytes("utf-8").length * 256;
            return i > 20000 ? 20000 : i;
        }

        return 0;
    }

    public void setHeightInPoints(float heightInPoints) {
        this.heightInPoints = heightInPoints;
    }

    protected HSSFCellStyle getDataStyle() {
        return dataStyle;
    }
}
