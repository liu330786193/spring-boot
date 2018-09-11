package com.yongjun.stock.util.Excel;

import com.yongjun.stock.util.Excel.annotation.ExcelColumn;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lyl on 2016/4/6.
 */
public abstract class AbstractExcelExportUtils implements ExcelExport {
    private static Logger logger = LoggerFactory.getLogger(AbstractExcelExportUtils.class);

    protected String filename;

    protected String sheetname;

    protected List<ExcelHeader> headers = new ArrayList<>();

    protected List<?> dataList = new ArrayList<>();

    HSSFCellStyle titleStyle;

    protected HSSFCellStyle dataStyle;

    protected HSSFWorkbook wb;

    protected Sheet sheet;

    protected float heightInPoints;

    protected int rowIndex; // 行号

    protected final int[] columnWidths;

    protected AbstractExcelExportUtils(String sheetname, List<ExcelHeader> headers, List dataList) {
        this.headers = headers;
        this.sheetname = sheetname;
        this.dataList = dataList;
        //设置列宽
        this.columnWidths = new int[headers.size()];
        init();
    }

    private void init() {
        wb = new HSSFWorkbook();
        sheet = wb.createSheet(sheetname);
        titleStyle = wb.createCellStyle();
        dataStyle = wb.createCellStyle();
        initTitleStyle(wb, titleStyle);
        initDataStyle(wb, dataStyle);
        this.heightInPoints = initHeightInPoints();
    }

    /**
     * 初始化TitleStyle。
     */
    protected abstract void initTitleStyle(HSSFWorkbook wb, HSSFCellStyle titleStyle);

    /**
     * 初始化DataStyle。
     */
    protected abstract void initDataStyle(HSSFWorkbook wb, HSSFCellStyle dataStyle);

    /**
     * 初始化HeightInPoints。
     */
    protected abstract float initHeightInPoints();

    protected float getHeightInPoints() {
        return this.heightInPoints;
    }

    /**
     * 导入Excel
     *
     * @throws Exception
     */
    @Override
    public Workbook exportWorkbook() throws Exception {
        createExcelTitle();
        createData();
        return wb;
    }

    /**
     * 得到Excel表头
     */
    protected static List<ExcelHeader> getExcelHeader(Class<?> clazz) throws Exception {
        clazz = Objects.requireNonNull(clazz);
        List<ExcelHeader> headers = new ArrayList<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(ExcelColumn.class)) {
                ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
                int columnIndex = excelColumn.sort();
                String headerName = excelColumn.value();
                boolean required = excelColumn.required();
                ExcelHeader header = new ExcelHeader(headerName, field.getName(), columnIndex, required ? 1 : 0);
                header.setImportFiledType(field.getType());
                header.setWriteMethod(getWriteMethod(clazz, field));
                headers.add(header);
            }
        }
        Collections.sort(headers);
        return headers;
    }

    //获取属性的set方法
    private static Method getWriteMethod(Class<?> clazz, Field field) throws Exception {
        Method method;
        StringBuffer sb = new StringBuffer();
        sb.append("set").append(field.getName().substring(0, 1).toUpperCase()).append(field.getName().substring(1));
        try {
            method = clazz.getMethod(sb.toString(), field.getType());
            if (null == method) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(clazz.getName() + "没有找到" + field.getName() + "set方法");
        }
        return method;
    }

    protected abstract void createExcelTitle();

    protected abstract void createData() throws Exception;

    protected Object getValueFromObject(Object o, String fieldName) throws Exception {
        String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method method = o.getClass().getMethod(methodName);

        Object value = method.invoke(o);
        return value;
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

    protected void calcColumWidth(final int[] columnWidths) {
        for (int i = 0; i < headers.size(); i++) {
            sheet.setColumnWidth(i, columnWidths[i]);
        }
    }

    /**
     * double 宽度 = (字符个数 * (字符宽度 - 1) + 5) / (字符宽度 - 1) * 256;
     */
    protected static int calcCellWidth(String text, int fontSize) {
        if (text != null && text.trim().length() > 0) {
            int length = 0;
            try {
                length = text.getBytes("gbk").length;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            double result = (length + 5.0) * fontSize / 8 * 256;
            int i = new Long(Math.round(result)).intValue();
            return i > 20000 ? 20000 : i;
        }
        return 0;
    }

    /*public static void main(String[] args) throws Exception {
        List<ExcelHeader> excelHeader = getExcelHeader(RecruitShopExcelVO.class);
        System.out.println(excelHeader);
        String text = "1啊1a是x d2sdf撒旦法奋斗速度发顺丰s1否";
        System.out.println(calcCellWidth(text, 12));
    }*/
}
