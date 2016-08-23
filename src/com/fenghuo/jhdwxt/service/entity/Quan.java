package com.fenghuo.jhdwxt.service.entity;

import java.sql.Date;

public class Quan {

	private int id;
	private String uid;
	private int state;
	private String money;
	private String condition;
	private Date deadline;

	public Quan() {
		super();
	}

	public Quan(int id, String uid, int state, String money,
			String condition, Date deadline) {
		super();
		this.id = id;
		this.uid = uid;
		this.state = state;
		this.money = money;
		this.condition = condition;
		this.deadline = deadline;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@Override
	public String toString() {
		return "Quan [id=" + id + ", uid=" + uid + ", state=" + state
				+ ", money=" + money + ", condition=" + condition
				+ ", deadline=" + deadline + "]";
	}

}
