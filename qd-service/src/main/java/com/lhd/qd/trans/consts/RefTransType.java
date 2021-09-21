package com.lhd.qd.trans.consts;

/**
 * 参照翻译类型
 * @author lhd
 */
public enum RefTransType {

	/**
	 * 参照翻译类型
	 */
	ROLE("Role"),
	DEPT("Dept"),
	COMPANY("Company"),
	USER("User");

	private final String type;

	public String getType() {
		return type;
	}

	RefTransType(String type) {
		this.type = type;
	}

	public String append(String suffix) {
		return this.type + ":" + suffix;
	}
}
