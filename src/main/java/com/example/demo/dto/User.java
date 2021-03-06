package com.example.demo.dto;
import javax.persistence.*;


@Entity
@Table
public class User {
  
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="name")
	private String name;

	@Column(name="password")
	private String password;
	
	public User() {
		
	}
	
	public User(String id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
