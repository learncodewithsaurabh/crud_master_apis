package com.codewithsaurabh.crud_master_apis.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name="users")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer user_id;
	
	@NotBlank(message = "Username cannot be blank")
	@Size(min = 3, max = 30, message="Username must be between 3 and 30 characters")
	@Column(unique = true)
	private String username;
	
	@NotBlank(message="Email cannot be blank")
	@Email(message="Invalid email format")
	@Column(unique = true)
	private String email;
	
	@NotBlank(message="Password cannot be blank")
	@Size(min = 6, message="Password must be at least 6 characters")
	private String password;
	
	@Size(max = 255, message="About must be less than 255 characters")
	private String about;
}
