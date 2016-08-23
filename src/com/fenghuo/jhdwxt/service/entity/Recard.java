package com.fenghuo.jhdwxt.service.entity;

public class Recard {
	private int id;
	private String uid;
	private String xcType;
	private double xcMoney;
	private String xcTime;
	private String xcMonth;
	private String xcYear;

	public Recard() {
		super();
	}

	public Recard(int id, String uid, String xcType, double xcMoney,
			String xcTime, String xcMonth, String xcYear) {
		super();
		this.id = id;
		this.uid = uid;
		this.xcType = xcType;
		this.xcMoney = xcMoney;
		this.xcTime = xcTime;
		this.xcMonth = xcMonth;
		this.xcYear = xcYear;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getXcType() {
		return xcType;
	}

	public void setXcType(String xcType) {
		this.xcType = xcType;
	}

	public double getXcMoney() {
		return xcMoney;
	}

	public void setXcMoney(double xcMoney) {
		this.xcMoney = xcMoney;
	}

	public String getXcTime() {
		return xcTime;
	}

	public void setXcTime(String xcTime) {
		this.xcTime = xcTime;
	}

	public String getXcMonth() {
		return xcMonth;
	}

	public void setXcMonth(String xcMonth) {
		this.xcMonth = xcMonth;
	}

	public String getXcYear() {
		return xcYear;
	}

	public void setXcYear(String xcYear) {
		this.xcYear = xcYear;
	}

	@Override
	public String toString() {
		return "Recard [id=" + id + ", uid=" + uid + ", xcType=" + xcType
				+ ", xcMoney=" + xcMoney + ", xcTime=" + xcTime + ", xcMonth="
				+ xcMonth + ", xcYear=" + xcYear + "]";
	}

}
