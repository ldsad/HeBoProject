package com.hebo.heboproject.entity;

import java.util.List;

public class ExpressEntity {
	private String messsage;
	private String nu;
	private String ischeck;
	private String com;
	private String status;
	private String condition;
	private List<Express> data;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMesssage() {
		return messsage;
	}
	public void setMesssage(String messsage) {
		this.messsage = messsage;
	}
	public String getNu() {
		return nu;
	}
	public void setNu(String nu) {
		this.nu = nu;
	}
	public String getIscheck() {
		return ischeck;
	}
	public void setIscheck(String ischeck) {
		this.ischeck = ischeck;
	}
	public String getCom() {
		return com;
	}
	public void setCom(String com) {
		this.com = com;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public List<Express> getData() {
		return data;
	}
	public void setData(List<Express> data) {
		this.data = data;
	}
	
}

	
