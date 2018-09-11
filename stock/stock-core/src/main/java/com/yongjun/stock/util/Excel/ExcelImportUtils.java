package com.yongjun.stock.util.Excel;

import com.yongjun.stock.util.Excel.exception.ExcelException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lyl on 2016/4/6.
 */
public class ExcelImportUtils {
    private static Logger logger = LoggerFactory.getLogger(AbstractExcelExportUtils.class);

    /**
     * 解析Excel文件，返回解析结果
     *
     * @param clazz
     * @param file
     * @param <E>
     * @return
     * @throws ExcelException
     */
    public static <E> List<E> resolverToList(Class<E> clazz, MultipartFile file) throws Exception {
        //验证文件格式
        Workbook wb = getWorkbook(file);

        //验证表头（默认取第1个sheet的数据）
        List<ExcelHeader> excelHeaderList = validateHeader(wb, AbstractExcelExportUtils.getExcelHeader(clazz), 0);

        //读取Excel数据
        List<E> dataList = new ArrayList<>();
        Sheet sheet = wb.getSheetAt(0);
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            try {
                E e = rowToObject(row, clazz, excelHeaderList);
                if (!isEmpty(e)) {
                    dataList.add(e);
                }
            } catch (Exception e) {
                logger.error("====>Excel 导入第" + (i + 1) + "行时读取数据失败!" + e.getMessage());
                e.printStackTrace();
            }
        }
        return dataList;
    }

    /**
     * 判断对象属性是否全为空
     */
    private static boolean isEmpty(Object obj) throws IllegalAccessException, InvocationTargetException {
        if (null == obj) return true;
        Method[] declaredMethods = obj.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.getName().startsWith("get")) {
                Object invoke = method.invoke(obj);
                if (invoke != null && StringUtils.isNotBlank(invoke.toString())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 验证表头
     */
    private static List<ExcelHeader> validateHeader(Workbook book, List<ExcelHeader> defaultHeaderList, int sheetNum) throws ExcelException {
        if (book == null || defaultHeaderList == null || defaultHeaderList.size() == 0) {
            throw new ExcelException("服务器内部错误，没有找到参数“Excel表头”");
        }
        List<ExcelHeader> result = new ArrayList<>();
        Sheet sheet = book.getSheetAt(sheetNum);

        if (null == sheet) throw new ExcelException("工作簿为空");

        Row row = sheet.getRow(0);
        if (null == row) throw new ExcelException("Excel导入模版不正确");
        Iterator<Cell> iterator = row.cellIterator();
        while (iterator.hasNext()) {
            Cell cell = iterator.next();
            String cellValue = CellValueUtils.cellValueToString(cell);

            if (StringUtils.isNotBlank(cellValue)) {
                for (ExcelHeader temp : defaultHeaderList) {
                    if (temp.getHeaderName().equalsIgnoreCase(cellValue)) {
                        ExcelHeader _temp = new ExcelHeader();
                        _temp.setHeaderName(temp.getHeaderName());
                        _temp.setImportFiled(temp.getImportFiled());
                        _temp.setColumnIndex(cell.getColumnIndex());
                        _temp.setIsRequired(temp.getIsRequired());
                        _temp.setImportFiledType(temp.getImportFiledType());
                        _temp.setWriteMethod(temp.getWriteMethod());
                        result.add(_temp);
                        break;
                    }
                }
            }

        }
        if (CollectionUtils.isEmpty(result)) {
            throw new ExcelException("Excel导入模版不正确");
        }

        for (ExcelHeader oheader : defaultHeaderList) {
            if (oheader.getIsRequired() > 0) {
                if (!containsHeaderName(result, oheader.getHeaderName())) {
                    throw new ExcelException("Excel导入模版不正确");
                }
            }
        }

        return result;
    }

    private static boolean containsHeaderName(List<ExcelHeader> headerList, String headerName) {
        if (null == headerList) return false;

        for (ExcelHeader temp : headerList) {
            if (temp.getHeaderName().equals(headerName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 验证文件格式
     */
    private static Workbook getWorkbook(MultipartFile file) throws ExcelException {
        if (null == file || file.isEmpty()) {
            throw new ExcelException("文件空");
        }
        Workbook book = null;
        try {
            book = new XSSFWorkbook(file.getInputStream());
        } catch (Exception ex) {
            try {
                book = new HSSFWorkbook(file.getInputStream());
            } catch (Exception e) {
                throw new ExcelException("文件格式不正确");
            }
        }
        return book;
    }

    private static <E> E rowToObject(Row row, Class<E> clazz, List<ExcelHeader> headers) throws Exception {
        if (null == row) return null;
        E obj = clazz.newInstance();
        if (headers != null && headers.size() > 0) {
            for (ExcelHeader temp : headers) {
                if (temp != null && temp.getHeaderName() != null && !(temp.getHeaderName().trim().equals(""))) {
                    setProperty(obj, row.getCell(temp.getColumnIndex()), temp);
                }
            }
        }
        return obj;
    }

    private static <E> void setProperty(E bean, Cell cell, ExcelHeader header) throws Exception {
        //单元格解析成bean的属性值
        Object cellValue = null;
        try {
            cellValue = CellValueUtils.cellValueParseObject(cell, header.getImportFiledType());
        } catch (Exception e) {
            logger.error("cell转为object 时出错了！" + e.getMessage());
            //e.printStackTrace();
        }
        if (1 == header.getIsRequired()) {
            Assert.notNull(cellValue, header.getImportFiled() + " 不能为空");
        }
        header.getWriteMethod().invoke(bean, cellValue);
    }

}
