package com.codewithsaurabh.crud_master_apis.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
	private Integer user_id;
	
	@NotBlank(message = "Username cannot be blank")
	@Size(min = 3, max = 30, message="Username must be between 3 and 30 characters")
	private String username;
	
	@NotBlank(message="Email cannot be blank")
	@Email(message="Invalid email format")
	private String email;
	
	@NotBlank(message="Password cannot be blank")
	@Size(min = 6, message="Password must be at least 6 characters")
	private String password;
	
	@Size(max = 255, message="About must be less than 255 characters")
	private String about;
}
