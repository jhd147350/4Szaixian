package com.fenghuo.jhdwxt.service.entity;

import java.sql.Date;

public class Messages {
	private int id;
	private String uid;
	private String time;
	private String content;

	public Messages() {
		super();
	}

	public Messages(int id, String uid, String time, String content) {
		super();
		this.id = id;
		this.uid = uid;
		this.time = time;
		this.content = content;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", uid=" + uid + ", time=" + time
				+ ", content=" + content + "]";
	}

}
