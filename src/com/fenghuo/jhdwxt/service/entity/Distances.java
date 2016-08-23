package com.fenghuo.jhdwxt.service.entity;

public class Distances {
	private int id;
	private String uid;
	private String type;
	private double distancess;

	public Distances() {
		super();
	}

	public Distances(int id, String uid, String type, double distancess) {
		super();
		this.id = id;
		this.uid = uid;
		this.type = type;
		this.distancess = distancess;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getDistancess() {
		return distancess;
	}

	public void setDistancess(double distancess) {
		this.distancess = distancess;
	}

	@Override
	public String toString() {
		return "Distances [id=" + id + ", uid=" + uid + ", type=" + type
				+ ", distancess=" + distancess + "]";
	}

}
