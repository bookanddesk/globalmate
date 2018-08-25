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
	/**
	 * 租赁
	 */
	rent("租赁"),
	/**
	 * 就医
	 */
	medical("就医"),
	/**
	 * 换汇
	 */
	exchange("换汇"),
	/**
	 * 教材
	 */
	teaching_material("教材"),
	/**
	 * 办手续
	 */
	formality("办手续"),


	other("其他");



	private String showValue;

	NeedTypeEnum(String showValue) {
		this.showValue = showValue;
	}

	public String getShowValue() {
		return showValue;
	}

}
