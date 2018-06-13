package com.globalmate.data.entity.po;

import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

public class JsonResult {
	private boolean success=true;//是否成功
	private String msg;//消息文本。如果失败则必需有值记录失败描述文字
	private Object data;//返回数据。如果是查询请求结果设置在此变量里
	private long costTime=-1l;//操作消耗时间(毫秒)
	private Map<String,Object> otherData;//其他附加数据,可有可无

	public Map<String, Object> getOtherData() {
		return otherData;
	}

	public void setOtherData(Map<String, Object> otherData) {
		this.otherData = otherData;
	}

	public static JsonResult success(){
		JsonResult jr = new JsonResult();
		return jr;
	}
	
	public static JsonResult success(Object data){
		JsonResult jr = new JsonResult();
		jr.setData(data);
		return jr;
	}
	
	public static JsonResult fail(String msg, Object data){
		JsonResult jr = new JsonResult();
		jr.setSuccess(false);
		jr.setMsg(msg);
		jr.setData(data);
		return jr;
	}
	
	public static JsonResult fail(String msg){
		JsonResult jr = new JsonResult();
		jr.setSuccess(false);
		jr.setMsg(msg);
		return jr;
	}
	

	public static ModelAndView mv500(String errMsg){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("500");
		mv.addObject("err",errMsg);
		return mv;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public long getCostTime() {
		return costTime;
	}

	public void setCostTime(long costTime) {
		this.costTime = costTime;
	}
	
}
