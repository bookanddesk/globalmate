package com.globalmate.service.user;

import com.alibaba.fastjson.JSONObject;
import com.globalmate.data.dao.mapper.SysSchoolMapper;
import com.globalmate.data.entity.SysSchool;
import com.globalmate.service.excel.AbstractExcelService;
import com.globalmate.service.excel.ExcelInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author XingJiajun
 * @Date 2018/8/17 16:32
 * @Description
 */
@Service
public class SchoolService extends AbstractExcelService implements ISchoolService {

    @Autowired
    private SysSchoolMapper schoolMapper;

    @Override
    public int addSchool(SysSchool sysSchool) {
        if (sysSchool != null) {
            return schoolMapper.insert(sysSchool);
        }
        return 0;
    }

    @Override
    public List<SysSchool> getSchools(SysSchool school) {
        return schoolMapper.queryLike(Optional.ofNullable(school).orElse(new SysSchool()));
    }


    @Override
    public ExcelInfo generateExcelInfo() {
        ExcelInfo excelInfo = new ExcelInfo();
        excelInfo.setField(getFieldsNameList());
        excelInfo.setExtraInfo(getFieldCodeNameMap());
        excelInfo.setTableName(getExcelName());
        excelInfo.setColumCodes(getColumnCodes());
        return excelInfo;
    }

    private String getExcelName() {
        return getTableName();
    }

    private ArrayList<String> getFieldsNameList(){
        Map<String, String> fieldCodeNameMap = getFieldCodeNameMap();
        Collection<String> strings = fieldCodeNameMap.values();
        return Lists.newArrayList(strings);
    }

    private Map<String, String> getFieldCodeNameMap() {
        Map<String, String> map = Maps.newLinkedHashMap();
        map.put("name", "学校名称");
        map.put("code", "学校标识码");
        map.put("supervisor", "主管部门");
        map.put("location","所在地");
        map.put("level", "办学层次");
        return map;
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

    @Override
    protected String getTableName() {
        return "sys_school";
    }
}
