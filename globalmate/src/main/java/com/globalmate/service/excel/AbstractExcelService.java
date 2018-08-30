package com.globalmate.service.excel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.globalmate.data.dao.mapper.DDLMapper;
import com.globalmate.uitl.IdGenerator;
import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

//@Service
public abstract class AbstractExcelService {

    @Autowired
    private DDLMapper ddlMapper;

    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        ExcelInfo excelInfo = generateExcelInfo();
        ExcelFileGenerator generator = new ExcelFileGenerator(excelInfo.getField(), excelInfo.getData(), excelInfo);
        XSSFWorkbook xssfWorkbook = generator.createXSSFWorkbook(excelInfo.getTableName());
        generator.expordExcelOnly(request, response, excelInfo.getTableName(), xssfWorkbook);
    }

    public int importExcel(Workbook wookbook) {
        if (wookbook == null) {
            throw new IllegalArgumentException();
        }
        //得到一个工作表
        Sheet sheet = wookbook.getSheetAt(0);
        //获得表头
        Row rowHead = sheet.getRow(0);
        //判断表头是否正确
        if (null == rowHead.getCell(0)
                || org.apache.commons.lang3.StringUtils.isEmpty(rowHead.getCell(0).getStringCellValue())
                || null == rowHead.getCell(0).getCellComment()) {
            throw new IllegalArgumentException();
        }
        //获得数据的总行数
        int totalRowNum = sheet.getLastRowNum();
        int columnLen = 0;
        while (true) {
            if (null == rowHead.getCell(columnLen)
                    || org.apache.commons.lang3.StringUtils.isEmpty(rowHead.getCell(columnLen).getStringCellValue())){
                break;
            }
            columnLen++;
        }

        int result = 0;
        //获得所有数据
        for (int i = 1; i <= totalRowNum; i++) {
            String pk = IdGenerator.generateId();

            //获得第i行对象
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List fields = new ArrayList();
            Map<String, Object> map = new HashMap<>();
            int emptyValCount = 0;
            for (int j = 0; j < columnLen; j++) {
                String strComment = rowHead.getCell(j).getCellComment().getString().getString();
                com.alibaba.fastjson.JSONObject objComment = JSON.parseObject(strComment);
                String columCode = objComment.getString("columnCode");
                String val = null;
                Cell rowCell = row.getCell(j);
                if (null != rowCell) {//excel的cell如果没设置过，POI可能获得null
                    DataFormatter formatter = new DataFormatter();
                    val = formatter.formatCellValue(rowCell);
                }
                if (org.apache.commons.lang3.StringUtils.isEmpty(val)) {
                    val = null;
                    emptyValCount++;
                } else {
                    val = val.trim();
                }
                Map<String, String> field = new HashMap<>();
                field.put("fieldName", columCode);
                field.put("fieldValue", val);
                fields.add(field);
            }
            if (emptyValCount == columnLen) {//本行未录入任何值
                continue;
            }

            Map<String, String> field = new HashMap<>();
            field.put("fieldName", "ID");
            field.put("fieldValue", pk);
            fields.add(field);

            map.put("fields", fields);
            map.put("tableName", getTableName());

            result += ddlMapper.insertData(map);
            System.out.println(result);
        }
        return result;
    }

    public Workbook generateWorkbook(MultipartFile file) {

        if (file ==null ) {
            throw new IllegalArgumentException();
        }

        String filename = file.getOriginalFilename();
        InputStream inputStream = null;
        Workbook wookbook = null;
        try {
            inputStream = file.getInputStream();
            if (StringUtils.endsWithIgnoreCase(filename, "xlsx")) {
                wookbook = new XSSFWorkbook(inputStream);
            }else {
                wookbook = new HSSFWorkbook(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        return wookbook;
    }

    protected ExcelInfo generateExcelInfo() {
        ExcelInfo excelInfo = new ExcelInfo();
        excelInfo.setField(getFieldsNameList());
        excelInfo.setExtraInfo(getFieldCodeNameMap());
        excelInfo.setTableName(getExcelName());
        excelInfo.setColumCodes(getColumnCodes());
        return excelInfo;
    };

    private String getExcelName() {
        return getTableName();
    }

    private ArrayList<String> getFieldsNameList(){
        Map<String, String> fieldCodeNameMap = getFieldCodeNameMap();
        Collection<String> strings = fieldCodeNameMap.values();
        return Lists.newArrayList(strings);
    }

    private List<String> getColumnCodes() {
        List<String> list = Lists.newArrayList();
        Collection<String> strings = getFieldCodeNameMap().keySet();
        for (String code : strings) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("columnCode", code);
            list.add(jsonObject.toJSONString());
        }
        return list;
    }

    public abstract Map<String, String> getFieldCodeNameMap();

    protected abstract String getTableName();
}
