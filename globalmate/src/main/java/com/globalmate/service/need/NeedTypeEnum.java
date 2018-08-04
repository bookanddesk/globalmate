package com.globalmate.service.need;

public enum NeedTypeEnum {
	/**
	 * 代买
	 */
	buy("代购"),
	/**
	 * 帮带
	 */
	carry("帮带"),
	/**
	 * 陪同
	 */
	accompany("陪玩"),
	/**
	 * 清关
	 */
	clearance("清关"),
	/**
	 * 协作学习
	 */
	learn_cooperation("学习互助"),
	
	other("其他");

	private String showValue;

	NeedTypeEnum(String showValue) {
		this.showValue = showValue;
	}

	public String getShowValue() {
		return showValue;
	}

}
