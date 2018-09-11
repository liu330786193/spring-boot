package com.yongjun.stock.util.Excel;

import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by lyl on 2016/5/12.
 */
public interface ExcelExport {
    String DEFAULT_SHEETNAME = "sheet1";
    String DEFAULT_FILENAME = "新建 Microsoft Excel 97-2003 工作表";
    float DEFAULT_HEIGHTINPOINTS = 20;//默认行高

    Workbook exportWorkbook() throws Exception;

    void exportExcel(HttpServletResponse response) throws Exception;

}
