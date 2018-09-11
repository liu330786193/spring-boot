package com.yongjun.stock.util.Excel;

import com.yongjun.stock.util.Excel.annotation.ExcelInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * Created by lyl on 2016/5/12.
 * 导入默认工具实现
 */
public final class DefaultExcelExportUtils extends SimpleExcelExportUtils {

    private DefaultExcelExportUtils(String sheetname, List<ExcelHeader> headers, List dataList) {
        super(sheetname, headers, dataList);
    }

    public static DefaultExcelExportUtils getInstance(String sheetname, List<ExcelHeader> headers, List dataList) {
        return new DefaultExcelExportUtils(sheetname, headers, dataList);
    }

    @Override
    protected void createExcelTitle(Sheet sheet, HSSFCellStyle titleStyle, List<ExcelHeader> headers, int[] columnWidths) {
        Row row = sheet.createRow(sheet.getLastRowNum());
        row.setHeightInPoints(getHeightInPoints());
        for (int i = 0; i < headers.size(); i++) {
            ExcelHeader header = headers.get(i);
            Cell cell = row.createCell(i);
            //数据
            StringBuffer value = new StringBuffer();
            //是否必填
            /*if (null != header.getIsRequired() && header.getIsRequired() == 1) {
                value.append("*");
            }*/
            value.append(header.getHeaderName());
            cell.setCellValue(value.toString());
            //int width = calcCellWidth(header.getHeaderName());
            int width = calcCellWidth(header.getHeaderName(), 12);
            if (width > columnWidths[i]) {
                columnWidths[i] = width;
            }
            cell.setCellStyle(titleStyle);
        }
        for (int i = 0; i < headers.size(); i++) {
            sheet.setColumnWidth(i, columnWidths[i]);
        }
        calcColumWidth(columnWidths);
    }

    @Override
    protected void createData(Sheet sheet, HSSFCellStyle dataStyle, List<?> dataList, List<ExcelHeader> headers, int[] columnWidths) throws Exception {
        if (dataList == null || dataList.isEmpty()) return;
        int rowIndex = sheet.getLastRowNum() + 1;
        for (int i = 0; i < dataList.size(); i++) {
            Row row = sheet.createRow(rowIndex);
            row.setHeightInPoints(getHeightInPoints());
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
                    int width = calcCellWidth(valueString, 8);
                    if (width > columnWidths[j]) {
                        columnWidths[j] = width;
                    }
                }
            }
            rowIndex++;
        }
        // 设置 Column Width
        calcColumWidth(columnWidths);
    }


    /**
     * 下载模板
     */
    public static void exportExcel(Class<?> clazz, HttpServletResponse response) throws Exception {
        exportExcel(clazz, null, response);
    }


    /***
     * 导入Excel
     *
     * @param clazz
     * @param dataList
     * @param response
     */
    public static void exportExcel(Class<?> clazz, List dataList, HttpServletResponse response) throws Exception {
        clazz = Objects.requireNonNull(clazz);
        String filename = "";
        String sheetName = "";
        if (clazz.isAnnotationPresent(ExcelInfo.class)) {
            ExcelInfo annotation = clazz.getAnnotation(ExcelInfo.class);
            filename = annotation.fileName();
            sheetName = annotation.sheetName();
        }
        filename = StringUtils.isNotBlank(filename) ? filename : DEFAULT_FILENAME;
        sheetName = StringUtils.isNotBlank(sheetName) ? sheetName : DEFAULT_SHEETNAME;

        List<ExcelHeader> headers = getExcelHeader(clazz);

        //得到Excel导出实例
        DefaultExcelExportUtils export = new DefaultExcelExportUtils(sheetName, headers, dataList);
        Workbook wb = export.exportWorkbook();
        //设置导出文件名,开始进行导出
        export.writer(filename, wb, response);
    }


}
