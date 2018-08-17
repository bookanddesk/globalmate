package com.globalmate.service.excel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelFileGenerator {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private final int SPLIT_COUNT = 1000; 

	private ArrayList<String> fieldName;

	private ArrayList<ArrayList<String>> fieldData;

	private ExcelInfo excelData;


	/**
	 * 构造器
	 * @param fieldName 结果集的字段名
	 * @param excelData 整个ExcelInfo
	 */
	public ExcelFileGenerator(ArrayList<String> fieldName, ArrayList<ArrayList<String>> fieldData, ExcelInfo excelData) {
		this.excelData = excelData;
		this.fieldName = fieldName;
		this.fieldData = fieldData;
	}


	/**
	 * 创建XSSFWorkbook，xlsx格式
	 * @param sheetName 页名称
	 * @return
	 */
	public XSSFWorkbook createXSSFWorkbook(String sheetName){

		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFSheet xssfSheet = workBook.createSheet(sheetName);

		//字体
		XSSFFont xssfFont = workBook.createFont();
		xssfFont.setBold(true);
		xssfFont.setFontName("宋体");
		xssfFont.setFontHeightInPoints((short) 14);//字体大小
		//样式
		XSSFCellStyle xssfCellStyle = workBook.createCellStyle();
		xssfCellStyle.setFont(xssfFont);
		xssfCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		xssfCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		xssfCellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);//水平居中
		xssfCellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直居中
		xssfCellStyle.setWrapText(true);//自动换行
		//边框
		xssfCellStyle.setBorderBottom(XSSFCellStyle.BORDER_THICK);
		xssfCellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		xssfCellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		xssfCellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);

		XSSFRow xssfRow = xssfSheet.createRow((short) 0);

		List<String> columnCodes = excelData.getColumCodes();
		XSSFDrawing patriarch = xssfSheet.createDrawingPatriarch();
		XSSFClientAnchor anchor = patriarch.createAnchor(0, 0, 0, 0, 5, 1, 8, 5);
		XSSFCell cell;
		String defaultValue = "-";
		for (int i = 0; i < fieldName.size(); i ++){
			cell = xssfRow.createCell(i);
			cell.setCellType(XSSFCell.CELL_TYPE_STRING);
			cell.setCellStyle(xssfCellStyle);
			cell.setCellValue(fieldName.get(i) != null ? fieldName.get(i) : (defaultValue));
			xssfSheet.setColumnWidth(i, 6000);
			if (CollectionUtils.isNotEmpty(columnCodes)) {
				JSONObject columnInfo = JSON.parseObject(columnCodes.get(i));
				if (0 == i) {
					columnInfo.put("tn", excelData.getTableName());
					if (null != excelData.getExtraInfo()) {
						columnInfo.putAll(excelData.getExtraInfo());
					}
				}
				Comment comment = patriarch.createCellComment(anchor);//创建批注
				comment.setString(new XSSFRichTextString(columnInfo.toJSONString()));
				cell.setCellComment(comment);
			}
		}

		XSSFCellStyle _xssfCellStyle = workBook.createCellStyle();
		_xssfCellStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		_xssfCellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);

		if (CollectionUtils.isNotEmpty(fieldData)){
			for (int i = 0; i < fieldData.size(); i ++){
				xssfRow = xssfSheet.createRow(i+1);
				ArrayList<String> list = fieldData.get(i);
				for (int j = 0; j < list.size(); j ++){
					cell = xssfRow.createCell(j);
					cell.setCellStyle(_xssfCellStyle);
					cell.setCellValue(list.get(j));
				}
			}
		}

		return workBook;
	}


	public String expordExcelOnly(HttpServletRequest request, HttpServletResponse response, String form_name, XSSFWorkbook workBook){
		SimpleDateFormat autoDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String title = (workBook.getSheetName(0) != null ? workBook.getSheetName(0) : "")
				+ autoDate.format(new Date()) + ".xlsx";
		OutputStream out = null;
		try {
			request.setAttribute("filename", title);
			request.setCharacterEncoding("UTF-8");
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			workBook.write(bos);
			byte[] bytes = bos.toByteArray();
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			String agent = request.getHeader("USER-AGENT");
			String downloadFileName;
			if(StringUtils.isNotEmpty(agent) && agent.toLowerCase().indexOf("firefox") > -1){
				downloadFileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(title.getBytes("UTF-8")))) + "?=";
			}else{
				downloadFileName =  java.net.URLEncoder.encode(title, "UTF-8");
			}
			response.setHeader("Content-Disposition", "attachment; filename="
					+ downloadFileName);
			out = response.getOutputStream();
			out.write(bytes, 0, bytes.length);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}  finally {
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return title;
	}


}
