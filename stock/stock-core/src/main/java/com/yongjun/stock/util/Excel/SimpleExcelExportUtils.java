package com.yongjun.stock.util.Excel;

import com.yongjun.stock.util.Excel.annotation.ExcelInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Created by lyl on 2016/4/18.
 * 导出工具类
 */
public abstract class SimpleExcelExportUtils extends AbstractExcelExportUtils {
    public static final String CONTENTTYPE = "application/msexcel";

    public static final short DATA_FONT_HEIGHT = 10;

    public static final short TITLE_FONT_HEIGHT = 12;

    public static final String FONT_NAME = "宋体";

    protected SimpleExcelExportUtils(String sheetName, List<ExcelHeader> headers, List dataList) {
        super(sheetName, headers, dataList);
    }

    /**
     * 导出表头
     *
     * @param sheet
     * @param titleStyle   表头excel样式
     * @param headers      表头数据
     * @param columnWidths 列宽
     */
    protected abstract void createExcelTitle(Sheet sheet, HSSFCellStyle titleStyle, List<ExcelHeader> headers, final int[] columnWidths);

    /**
     * 导出数据
     *
     * @param sheet
     * @param dataStyle    内容数据excel样式
     * @param dataList     内容数据
     * @param headers      表头数据
     * @param columnWidths 列宽
     * @throws Exception
     */
    protected abstract void createData(Sheet sheet, HSSFCellStyle dataStyle, List<?> dataList, List<ExcelHeader> headers, final int[] columnWidths) throws Exception;

    /**
     * 数据样式
     * <p>
     * 若需要重新定义样式，请重新此方法
     *
     * @param wb
     * @param dataStyle
     */
    @Override
    protected void initDataStyle(final HSSFWorkbook wb, final HSSFCellStyle dataStyle) {
        HSSFFont dataFont = wb.createFont();
        dataFont.setFontName(FONT_NAME);
        dataFont.setFontHeightInPoints(DATA_FONT_HEIGHT);
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

    /**
     * 表头样式
     * <p>
     * 若需要重新定义样式，请重新此方法
     *
     * @param wb
     * @param titleStyle
     */
    @Override
    protected void initTitleStyle(final HSSFWorkbook wb, final HSSFCellStyle titleStyle) {
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
        font.setFontName(FONT_NAME);
        font.setFontHeightInPoints(TITLE_FONT_HEIGHT); //设置字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        //font.setFontName("IMPACT");
        titleStyle.setFont(font);//选择需要用到的字体格式
        //设置自动换行
        titleStyle.setWrapText(true);
    }

    @Override
    protected float initHeightInPoints() {
        return DEFAULT_HEIGHTINPOINTS;
    }

    @Override
    public void exportExcel(HttpServletResponse response) throws Exception {
        Workbook workbook = exportWorkbook();
        writer(filename, workbook, response);
    }

    @Override
    protected void createExcelTitle() {
        createExcelTitle(sheet, titleStyle, headers, columnWidths);

    }

    @Override
    protected void createData() throws Exception {
        createData(sheet, dataStyle, dataList, headers, columnWidths);
    }

    public static void writer(String filename, Workbook wb, HttpServletResponse response) throws Exception {
        ServletOutputStream os = null;
        try {
            os = response.getOutputStream();
            // 设置response参数，可以打开下载页面
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(filename.getBytes("GBK"), "ISO8859-1")
                    + ".xls");// 设定输出文件头
            response.setContentType(CONTENTTYPE);// 定义输出类型
            wb.write(os);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected String getSheetName(Class<?> clazz) {
        clazz = Objects.requireNonNull(clazz);
        String sheetName = "";
        if (clazz.isAnnotationPresent(ExcelInfo.class)) {
            ExcelInfo annotation = clazz.getAnnotation(ExcelInfo.class);
            sheetName = annotation.sheetName();
        }
        sheetName = StringUtils.isNotBlank(sheetName) ? sheetName : DEFAULT_SHEETNAME;
        return sheetName;
    }

    protected String getFilename(Class<?> clazz) {
        clazz = Objects.requireNonNull(clazz);
        String filename = "";
        if (clazz.isAnnotationPresent(ExcelInfo.class)) {
            ExcelInfo annotation = clazz.getAnnotation(ExcelInfo.class);
            filename = annotation.fileName();
        }
        filename = StringUtils.isNotBlank(filename) ? filename : DEFAULT_FILENAME;
        return filename;
    }

}
