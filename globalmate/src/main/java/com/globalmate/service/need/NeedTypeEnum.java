package com.globalmate.service.need;

import com.globalmate.uitl.StringUtils;

public enum NeedTypeEnum {
	/**
	 * 代买
	 */
	buy("buy", "代购"),
	/**
	 * 帮带
	 */
	carry("carry", "帮带"),
	/**
	 * 陪同
	 */
	accompany("accompany", "陪玩"),
	/**
	 * 清关
	 */
	clearance("clearance", "清关"),
	/**
	 * 协作学习
	 */
	learn_cooperation("learn_cooperation", "学习互助"),
	/**
	 * 租赁
	 */
	rent("rent", "租赁"),
	/**
	 * 就医
	 */
	medical("medical", "就医"),
	/**
	 * 换汇
	 */
	exchange("exchange", "换汇"),
	/**
	 * 教材
	 */
	teaching_material("teaching_material", "教材"),
	/**
	 * 办手续
	 */
	formality("formality", "办手续"),


	other("other", "其他");


	private String value;
	private String showValue;

	NeedTypeEnum(String value, String showValue) {
		this.value = value;
		this.showValue = showValue;
	}

	public String getShowValue() {
		return this.showValue;
	}

	public String getValue() {
		return this.value;
	}

	public static NeedTypeEnum convert(String value) {
		NeedTypeEnum typeEnum = null;
		for(NeedTypeEnum needTypeEnum : NeedTypeEnum.values()) {
			if (StringUtils.equalsIgnoreCase(needTypeEnum.getValue(), value) ||
					StringUtils.equals(needTypeEnum.getShowValue(), value)) {
				typeEnum = needTypeEnum;
				break;
			}
		}
		return typeEnum;
	}

}
