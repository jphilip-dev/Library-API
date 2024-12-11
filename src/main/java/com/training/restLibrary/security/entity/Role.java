package com.training.restLibrary.security.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "api_roles", uniqueConstraints=
@UniqueConstraint(columnNames={"user_id", "role"})
)
public class Role {
	@Id
	@Column(name = "user_id")
	private String user;
	@Id
	@Column(name = "role")
	private String role;
	
	public Role() {
		
	}

	public Role(String user, String role) {
		super();
		this.user = user;
		this.role = role;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
