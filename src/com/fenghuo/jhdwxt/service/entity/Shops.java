package com.fenghuo.jhdwxt.service.entity;

public class Shops {
	private int id;
//	private String uid;
	private String brand;
	private String series;
	private String model;
	private String type;
	private String shopName;
	private String shopYuyue;
	private String shop4S;
	private String shopDistance;
	private String shopAddress;
	private String shopImg;



	public Shops() {
		super();
	}

	public Shops(int id, String brand, String series, String model,
			String type, String shopName, String shopYuyue, String shop4s,
			String shopDistance, String shopAddress, String shopImg) {
		super();
		this.id = id;
//		this.uid = uid;
		this.brand = brand;
		this.series = series;
		this.model = model;
		this.type = type;
		this.shopName = shopName;
		this.shopYuyue = shopYuyue;
		shop4S = shop4s;
		this.shopDistance = shopDistance;
		this.shopAddress = shopAddress;
		this.shopImg = shopImg;
	}

//	public void setUid(String uid) {
//		this.uid = uid;
//	}
//	public String getUid() {
//		return uid;
//	}
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopYuyue() {
		return shopYuyue;
	}

	public void setShopYuyue(String shopYuyue) {
		this.shopYuyue = shopYuyue;
	}

	public String getShop4S() {
		return shop4S;
	}

	public void setShop4S(String shop4s) {
		shop4S = shop4s;
	}

	public String getShopDistance() {
		return shopDistance;
	}

	public void setShopDistance(String shopDistance) {
		this.shopDistance = shopDistance;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public String getShopImg() {
		return shopImg;
	}

	public void setShopImg(String shopImg) {
		this.shopImg = shopImg;
	}

	@Override
	public String toString() {
		return "Shops [id=" + id + ",  brand=" + brand
				+ ", series=" + series + ", model=" + model + ", type=" + type
				+ ", shopName=" + shopName + ", shopYuyue=" + shopYuyue
				+ ", shop4S=" + shop4S + ", shopDistance=" + shopDistance
				+ ", shopAssress=" + shopAddress + ", shopImg=" + shopImg + "]";
	}

}
