package com.zjzc.manage.utils.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csvreader.CsvReader;

public class ExcelFileOperator {

	private final static Logger logger = LoggerFactory.getLogger(ExcelFileOperator.class);
	
	/**
	 * 读取xls文件内容（未完善）
	 * @param filePath
	 * @return
	 */
	@SuppressWarnings("resource")
	public static final List<List<List<String>>> readXlsFile(String filePath, boolean readHead) {
		HSSFWorkbook hssfWorkbook;
		List<List<List<String>>> list = new ArrayList<List<List<String>>>();
		InputStream in = null;
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				return list;
			}
			in = new FileInputStream(file);
			hssfWorkbook = new HSSFWorkbook(in);
			// 循环工作表Sheet
			for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
				List<List<String>> sheetList = new ArrayList<List<String>>();
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
				if (hssfSheet == null) {
					continue;
				}
				// 循环行Row，开始
				int firstRow = 0;
				if (!readHead) {
					firstRow = 1;
				}
				for (int rowNum = firstRow; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					if (hssfRow == null) {
						continue;
					}
					List<String> listTemp = new ArrayList<String>();
					// 循环列Cell
					for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
						HSSFCell hssfCell = hssfRow.getCell(cellNum);
						if (hssfCell == null) {
							continue;
						}
						String s = getValue(hssfCell);
						if(StringUtils.isNotBlank(s)){
							listTemp.add(getValue(hssfCell));
						}
					}
					if(!listTemp.isEmpty()){
						sheetList.add(listTemp);
					}
				}
				if (!sheetList.isEmpty()) {
					list.add(sheetList);
				}
			}
		} catch (IOException e) {
			logger.error("xls文件读取错误，请检查文件！错误信息：", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public static final List<List<List<String>>> readXlsxFile(String path, boolean readHead) {
		List<List<List<String>>> list = new ArrayList<List<List<String>>>();
		try {
			File xlsxFile = new File(path);
			if(!xlsxFile.exists()){
				return list;
			}
            Workbook wb = WorkbookFactory.create(xlsxFile);
            int sheetNum = wb.getNumberOfSheets();
            Sheet sheet = null;
            // 遍历工作表，遍历sheet(index 0开始)
            for(int sheetIndex = 0; sheetIndex < sheetNum; sheetIndex ++){
            	List<List<String>> sheetList = new ArrayList<List<String>>();
                sheet = wb.getSheetAt(sheetIndex);
                Row row = null;
                int firstRowNum = sheet.getFirstRowNum();
                int lastRowNum = sheet.getLastRowNum();
                // 遍历行，遍历row(行 0开始)
                if (!readHead) {
                	firstRowNum = firstRowNum + 1;
                }
                for (int rowIndex = firstRowNum; rowIndex <= lastRowNum; rowIndex ++) {
                	List<String> rowList = new ArrayList<String>();
                    row = sheet.getRow(rowIndex);
                    if(null != row){
                        int firstCellNum = row.getFirstCellNum();
                        int lastCellNum = row.getLastCellNum();
                        // 遍历列，遍历cell（列 0开始）
                        for (int cellIndex = firstCellNum; cellIndex < lastCellNum; cellIndex++) {
                            Cell cell = row.getCell(cellIndex, Row.RETURN_BLANK_AS_NULL);
                            if (null != cell) {
                                Object cellValue = null;//cellValue的值
                                switch (cell.getCellType()) {
	                                case Cell.CELL_TYPE_STRING:
	                                    cellValue = cell.getRichStringCellValue().getString();
	                                    break;
	                                case Cell.CELL_TYPE_NUMERIC:
	                                    if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
	                                        cellValue = cell.getDateCellValue();
	                                        // 可以按日期格式转换
	                                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	                                        cellValue = formatter.format(cellValue);
	                                    } else {
	                                        cellValue = cell.getNumericCellValue();
	                                    }
	                                    break;
	                                case Cell.CELL_TYPE_BOOLEAN:
	                                    cellValue = cell.getBooleanCellValue();
	                                    break;
	                                case Cell.CELL_TYPE_FORMULA:
	                                    cellValue = cell.getCellFormula();
	                                    break;
	                                default:
	                                    if (cell != null) {
	                                    	cellValue = cell.toString();
	                                    }
	                            }
                                rowList.add(String.valueOf(cellValue));
                            } else {
                                // 列为空
                            	rowList.add(null);
                            }
                        }
                        sheetList.add(rowList);
                    }else{
                        // 行为null，不处理
                    }
                }
                list.add(sheetList);
            }
        } catch (InvalidFormatException e) {
        	logger.error("xlsx文件读取错误，请检查文件！错误信息：", e);
        } catch (Exception e) {
        	logger.error("xlsx文件读取错误，请检查文件！错误信息：", e);
        }
		
		return list;
	}
	
	/**
	 * 读取csv文件内容（未完善）
	 * @param filePath
	 * @param charsetName
	 * @return
	 */
	public static final List<List<String>> readCsvFile(String filePath, String charsetName, boolean readHead) {
		List<List<String>> list = new ArrayList<List<String>>();
		InputStream in = null;
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				return list;
			}
			in = new FileInputStream(file);
			CsvReader csvReader = new CsvReader(in, Charset.forName(charsetName));
			if (!readHead) {
				csvReader.readHeaders();//跳过
			}
			while(csvReader.readRecord()){
				String[] strings = csvReader.getValues();
				List<String> temp = new ArrayList<String>();
				for(int i = 0; i < strings.length; i++){
					if(StringUtils.isNotBlank(strings[i])){
						temp.add(StringUtils.trimToEmpty(strings[i]));
					}else{
						break;
					}
				}
				if(temp.size() > 0){
					list.add(temp);
				}
			}
			return list;
		} catch (IOException e) {
			logger.error("csv文件读取错误，请检查文件！错误信息：", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return new ArrayList<List<String>>();
	}
	
	@SuppressWarnings("static-access")
	private static String getValue(HSSFCell hssfCell){
		if(hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN){  
			return String.valueOf( hssfCell.getBooleanCellValue());  
		}else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC){  
			return String.valueOf( hssfCell.getNumericCellValue());  
		}else{  
			return String.valueOf( hssfCell.getStringCellValue());  
		}  
	}
	
}
