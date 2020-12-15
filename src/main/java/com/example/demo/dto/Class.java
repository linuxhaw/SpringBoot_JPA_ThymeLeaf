package com.example.demo.dto;
import javax.persistence.*;

@Entity
@Table
public class Class {
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="name")
	private String name;
	

	public Class() {
		super();
	}

	public Class(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

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
	
	
}
