package com.globalmate.service.need;

public enum NeedTypeEnum {
	/**
	 * 代买
	 */
	buy("代买"),
	/**
	 * 帮带
	 */
	carry("帮带"),
	/**
	 * 陪同
	 */
	accompany("陪同"),
	/**
	 * 清关
	 */
	clearance("清关"),
	/**
	 * 协作学习
	 */
	learn_cooperation("协作学习"),
	
	other("其他");

	private String showValue;

	NeedTypeEnum(String showValue) {
		this.showValue = showValue;
	}

	public String getShowValue() {
		return showValue;
	}

}
