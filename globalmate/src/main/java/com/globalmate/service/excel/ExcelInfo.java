package com.globalmate.service.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelInfo {
	
	private ArrayList<ArrayList<String>> data;
	
	private ArrayList<String> field;
	
	
	private ArrayList<String> pk_boins;

	private List<String> columCodes;//每个元素是JsonString

	private String tableName;

	private Map<String, String> extraInfo;

	public ArrayList<ArrayList<String>> getData() {
		return data;
	}

	public void setData(ArrayList<ArrayList<String>> data) {
		this.data = data;
	}


	public ArrayList<String> getField() {
		return field;
	}


	public void setField(ArrayList<String> field) {
		this.field = field;
	}

	public ArrayList<String> getPk_boins() {
		return pk_boins;
	}

	public void setPk_boins(ArrayList<String> pk_boins) {
		this.pk_boins = pk_boins;
	}

	public List<String> getColumCodes() {
		return columCodes;
	}

	public void setColumCodes(List<String> columCodes) {
		this.columCodes = columCodes;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Map<String, String> getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(Map<String, String> extraInfo) {
		this.extraInfo = extraInfo;
	}
}
