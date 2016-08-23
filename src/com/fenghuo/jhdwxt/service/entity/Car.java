package com.fenghuo.jhdwxt.service.entity;

public class Car {

	private int id;
	private String uid;
	private String brand;
	private String series;
	private String model;
	private String img;
	
	
	public Car() {
		super();
	}


	public Car(int id, String uid, String brand, String series, String model,
			String img) {
		super();
		this.id = id;
		this.uid = uid;
		this.brand = brand;
		this.series = series;
		this.model = model;
		this.img = img;		
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


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getSeries() {
		return series;
	}


	public void setSeries(String series) {
		this.series = series;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	@Override
	public String toString() {
		return "Car [id=" + id + ", uid=" + uid + ", brand=" + brand
				+ ", series=" + series + ", model=" + model + ", img=" + img
				+ "]";
	}
	
	
}
