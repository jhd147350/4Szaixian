package com.fenghuo.jhdwxt.service.entity;

public class Zjyitem {

	private int id;
	private String zjyTitle;
	private String zjyContent;
	private String zjyCfd;
	private String zjyTime;
	private String zjyAddress;
	private String zjyImg;
	private String zjyHttp;

	public Zjyitem() {
		super();
	}

	public Zjyitem(int id, String zjyTitle, String zjyContent, String zjyCfd,
			String zjyTime, String zjyAddress, String zjyImg, String zjyHttp) {
		super();
		this.id = id;
		this.zjyTitle = zjyTitle;
		this.zjyContent = zjyContent;
		this.zjyCfd = zjyCfd;
		this.zjyTime = zjyTime;
		this.zjyAddress = zjyAddress;
		this.zjyImg = zjyImg;
		this.zjyHttp = zjyHttp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getZjyTitle() {
		return zjyTitle;
	}

	public void setZjyTitle(String zjyTitle) {
		this.zjyTitle = zjyTitle;
	}

	public String getZjyContent() {
		return zjyContent;
	}

	public void setZjyContent(String zjyContent) {
		this.zjyContent = zjyContent;
	}

	public String getZjyCfd() {
		return zjyCfd;
	}

	public void setZjyCfd(String zjyCfd) {
		this.zjyCfd = zjyCfd;
	}

	public String getZjyTime() {
		return zjyTime;
	}

	public void setZjyTime(String zjyTime) {
		this.zjyTime = zjyTime;
	}

	public String getZjyAddress() {
		return zjyAddress;
	}

	public void setZjyAddress(String zjyAddress) {
		this.zjyAddress = zjyAddress;
	}

	public String getZjyImg() {
		return zjyImg;
	}

	public void setZjyImg(String zjyImg) {
		this.zjyImg = zjyImg;
	}

	public String getZjyHttp() {
		return zjyHttp;
	}

	public void setZjyHttp(String zjyHttp) {
		this.zjyHttp = zjyHttp;
	}

	@Override
	public String toString() {
		return "Zjyitem [id=" + id + ", zjyTitle=" + zjyTitle + ", zjyContent="
				+ zjyContent + ", zjyCfd=" + zjyCfd + ", zjyTime=" + zjyTime
				+ ", zjyAddress=" + zjyAddress + ", zjyImg=" + zjyImg
				+ ", zjyHttp=" + zjyHttp + "]";
	}

}
