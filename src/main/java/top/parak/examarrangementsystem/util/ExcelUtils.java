package top.parak.examarrangementsystem.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> Project: Excel </p>
 * <p> Package: top.parak.poi </p>
 * <p> FileName: ExcelUtil <p>
 * <p> Description: 表格工具类 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/30
 */
public class ExcelUtils {
    /** office2003 */
    private final static String EXCEL_TYPE_XLS = "xls";
    /** office2007 */
    private final static String EXCEL_TYPE_XLSX = "xlsx";
    /** 空字符串 */
    private final static String CHAR_TYPE_BLANK = "";
    /** 错误字符 */
    private final static String CHAR_TYPE_ERROR = "error";
    /** 未知字符 */
    private final static String CHAR_TYPE_UNKNOWN = "unknown";
    /** 列的长度单位 */
    private final static Integer COLUMN_CELL_WIDTH = 1 << 10;
    /** 时间字符串格式 */
    private final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    /** 时间字符串格式化器 */
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    /**
     * 读取表格文件并解析成字符串数组
     * @param file 表格文件
     * @return 数据集合
     */
    public static List<List<Object>> readExcel(File file) {
        try {
            checkFile(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Workbook workBook = getWorkBook(file);
        return readProcess(workBook);
    }

    /**
     * 检验文件是否存在，并且为表格文件
     * @param file 文件
     * @throws NullPointerException 未找到文件
     * @throws FileNotFoundException 非表格文件
     * @throws IllegalArgumentException 后缀名错误
     */
    private static void checkFile(File file) throws FileNotFoundException {
        if (file == null) {
            throw new NullPointerException("File is null");
        }
        if(!file.exists()){
            throw new FileNotFoundException("File " + file + " does not exist");
        }
        String fileName = file.getName();
        if(!fileName.endsWith(EXCEL_TYPE_XLS) && !fileName.endsWith(EXCEL_TYPE_XLSX)){
            throw new IllegalArgumentException(fileName + " is not a kind of excel file");
        }
    }

    /**
     * 获取文件对应的工作簿，分为XLS和XLSX类型
     * @param file 表格文件
     * @return 表格文件的工作簿
     */
    private static Workbook getWorkBook(File file) {
        String fileName = file.getName();
        Workbook workbook = null;
        try {
            InputStream in = new FileInputStream(file);
            workbook = fileName.endsWith(EXCEL_TYPE_XLS) ? new HSSFWorkbook(in) : new XSSFWorkbook(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }

    /**
     * 获取单元格的值
     * @param cell 单元格
     * @return 单元格的值
     */
    private static Object getCellValue(Cell cell){
        Object cellValue = "";
        if(cell == null){
            return cellValue;
        }
        // 判断数据的类型
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC: // 数字类型
                cellValue = cell.getNumericCellValue(); break;
            case Cell.CELL_TYPE_STRING: //字符串类型
                cellValue = cell.getStringCellValue(); break;
            case Cell.CELL_TYPE_BOOLEAN: // 布尔类型
                cellValue = cell.getBooleanCellValue(); break;
            case Cell.CELL_TYPE_FORMULA: // 公式
                cellValue = String.valueOf(cell.getCellFormula()); break;
            case Cell.CELL_TYPE_BLANK: // 空值
                cellValue = CHAR_TYPE_BLANK; break;
            case Cell.CELL_TYPE_ERROR: // 错误
                cellValue = CHAR_TYPE_ERROR; break;
            default: cellValue = CHAR_TYPE_UNKNOWN; break;
        }
        return cellValue;
    }

    /**
     * 读取工作簿，一行数据读作一个数组，所有行作为一个集合返回
     * @param workbook 工作簿
     * @return 数据集合
     */
    private static List<List<Object>> readProcess(Workbook workbook) {
        List<List<Object>> res = new ArrayList<>();
        if(workbook != null) {
            for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if (sheet == null)
                    continue;
                int firstRowNum = sheet.getFirstRowNum();
                int lastRowNum = sheet.getLastRowNum();
                for (int rowNum = firstRowNum; rowNum <= lastRowNum; rowNum++) {
                    Row row = sheet.getRow(rowNum);
                    if (row == null)
                        continue;
                    int firstCellNum = row.getFirstCellNum();
                    int lastCellNum = row.getPhysicalNumberOfCells();
                    List<Object> curr = new ArrayList<>(lastCellNum - firstCellNum + 1);
                    for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                        Cell cell = row.getCell(cellNum);
                        curr.add(getCellValue(cell));
                    }
                    res.add(curr);
                }
            }
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    /**
     * 将标题和内容写入新文件
     * @param file        文件
     * @param sheetName   表格名
     * @param titleList   标题列
     * @param contentList 内容列
     */
    public static void writeExcel(File file, String sheetName, List<String> titleList, List<List<Object>> contentList) {
        String fileName = checkParam(file, sheetName, titleList, contentList);
        Workbook workbook = fileName.endsWith(EXCEL_TYPE_XLS) ? new HSSFWorkbook() : new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);
        initHead(workbook, sheet, titleList);
        writeContent(workbook, sheet, contentList);
        try {
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查参数是否合法，并返回文件名
     * @param file        文件
     * @param sheetName   表格名
     * @param titleList   标题列
     * @param contentList 内容列
     * @return 文件名
     */
    private static String checkParam(File file, String sheetName, List<String> titleList, List<List<Object>> contentList) {
        if (file == null)
            throw new NullPointerException("File is null");
        String fileName = file.getName();
        if (fileName.equals("") || !(fileName.endsWith(EXCEL_TYPE_XLS) || fileName.endsWith(EXCEL_TYPE_XLSX)))
            throw new IllegalArgumentException("File name is illegal");
        if (sheetName == null || sheetName.equals(""))
            throw new IllegalArgumentException("Sheet name is blank");
        if (titleList == null)
            throw new IllegalArgumentException("Title list is null");
        if (contentList == null)
            throw new IllegalArgumentException("Content list is null");
        return fileName;
    }

    /**
     * 初始化标题行
     * @param book      工作簿
     * @param sheet     当前表
     * @param titleList 标题列
     */
    private static void initHead(Workbook book, Sheet sheet, List<String> titleList) {
        // 标题样式
        CellStyle style = book.createCellStyle();
        // 居中对齐
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        // 背景填充
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        // 字体样式
        Font font = book.createFont();
        font.setFontName("华文楷体");
        font.setFontHeightInPoints((short) 13);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        // 创建第一行
        Row head = sheet.createRow(0);
        head.setHeightInPoints(20);
        // 列的宽度
        int columnWidth = 0;
        for (int i = 0; i < titleList.size(); i++) {
            Cell cell = head.createCell(i);
            cell.setCellStyle(style);
            String s = titleList.get(i);
            cell.setCellValue(s);
            columnWidth = s.length() * 1024;
            sheet.setColumnWidth(i, columnWidth);
        }
    }

    /**
     * 填充内容行
     * @param book        工作簿
     * @param sheet       当前表
     * @param contentList 内容列
     */
    private static void writeContent(Workbook book, Sheet sheet, List<List<Object>> contentList) {
        for (int i = 0; i < contentList.size(); i++) {
            Row curr = sheet.createRow(i + 1);
            List<Object> content = contentList.get(i);
            for (int c = 0; c < content.size(); c++) {
                writeProcess(book, curr, c, content.get(c));
            }
        }
    }

    /**
     * 将值填入单元格
     * @param book 工作簿
     * @param row 当前行
     * @param col 当前列
     * @param val 单元值
     */
    private static void writeProcess(Workbook book, Row row, int col, Object val) {
        // 内容样式
        CellStyle style = book.createCellStyle();
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        // 居中对齐
        style.setAlignment(CellStyle.ALIGN_CENTER);
        // 字体样式
        Font font = book.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 10);
        style.setFont(font);
        Cell cell = row.createCell(col);
        cell.setCellStyle(style);
        try {
            if (val == null) {
                cell.setCellValue(CHAR_TYPE_BLANK);
            } else {
                if (val instanceof String) {
                    cell.setCellValue((String) val);
                } else if (val instanceof Integer) {
                    cell.setCellValue((int) val);
                } else if (val instanceof Long) {
                    cell.setCellValue((Long) val);
                } else if (val instanceof Double) {
                    cell.setCellValue((Double) val);
                } else if (val instanceof Float) {
                    cell.setCellValue((Float) val);
                } else if (val instanceof Boolean) {
                    cell.setCellValue((Boolean) val);
                } else if (val instanceof TemporalAccessor) {
                    cell.setCellValue(DATE_TIME_FORMATTER.format((TemporalAccessor) val));
                } else {
                    cell.setCellValue(val.toString());
                }
            }
        } catch (Exception e) {
            cell.setCellValue(CHAR_TYPE_ERROR);
            e.printStackTrace();
        }
    }
}
