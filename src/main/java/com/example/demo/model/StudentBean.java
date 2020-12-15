package com.example.demo.model;

import javax.validation.constraints.NotEmpty;

public class StudentBean {
	@NotEmpty(message="Id must not be null!")
	private String id;
	@NotEmpty (message="Name must not be null!")
	private String name;
	@NotEmpty (message="Day must be selected!")
	private String day;
	@NotEmpty (message="Month must be selected!")
	private String month;
	@NotEmpty (message="Year must be selected!")
	private String year;
	@NotEmpty (message="Statud must be selected!")
	private String status;
	@NotEmpty (message="ClassName must be selected!")
	private String className;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
}
