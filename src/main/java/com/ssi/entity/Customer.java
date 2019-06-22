package com.ssi.entity;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {
	@Id
	private int code;
	private String name;
	private String address;
	private Blob picture;
	private Blob identity;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Blob getPicture() {
		return picture;
	}
	public void setPicture(Blob picture) {
		this.picture = picture;
	}
	@Override
	public String toString() {
		return "Customer [code=" + code + ", name=" + name + ", address="
				+ address + ", picture=" + picture + "]";
	}
	public Blob getIdentity() {
		return identity;
	}
	public void setIdentity(Blob identity) {
		this.identity = identity;
	}
	
	
	
}
