package com.codewithsaurabh.crud_master_apis.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithsaurabh.crud_master_apis.exception.ResourceNotFoundException;
import com.codewithsaurabh.crud_master_apis.payload.ApiResponse;
import com.codewithsaurabh.crud_master_apis.payload.UserDto;
import com.codewithsaurabh.crud_master_apis.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService userService;

	// Create User
	@PostMapping("/")
	public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errors.add(error.getField() + ": " + error.getDefaultMessage());
			}
			return new ResponseEntity<>(new ApiResponse("Validation failed", false, errors), HttpStatus.BAD_REQUEST);
		}
		UserDto savedUser = this.userService.createUser(userDto);
		return new ResponseEntity<>(new ApiResponse("User Created Successfully", true, savedUser), HttpStatus.CREATED);
	}

	// Get All User
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser() {
		return ResponseEntity.ok(this.userService.getAllUser());
	}

	// Get Single User
	@GetMapping("/{userId}")
	public ResponseEntity<ApiResponse> getUser(@PathVariable @NotNull Integer userId) {

		try {
			UserDto user = this.userService.getUserById(userId);
			return new ResponseEntity<>(new ApiResponse("User retrieved successfully", true, user), HttpStatus.OK);
		} catch (ResourceNotFoundException ex) {
			return new ResponseEntity<>(new ApiResponse("User not found", false, null), HttpStatus.OK);
		}
	}

	
	// Update User
	@PutMapping("/{userId}")
	public ResponseEntity<ApiResponse> updateUser(@RequestBody @NotNull UserDto userDto, @NotNull @PathVariable Integer userId) {
		try {
			UserDto updatedUser = this.userService.updateUser(userDto, userId);
			return new ResponseEntity<>(new ApiResponse("User updated successfully", true, updatedUser)
					                        , HttpStatus.OK);
		} catch (ResourceNotFoundException ex) {
			return new ResponseEntity<>(new ApiResponse(ex.getMessage(), false, null), HttpStatus.NOT_FOUND);

		}
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable @NotNull Integer userId) {
	 try {
		 this.userService.deleteUser(userId);
		 return new ResponseEntity<>(new ApiResponse("User Deleted Successfully..", true, userId), HttpStatus.OK);
	 }catch(ResourceNotFoundException ex) {
		 return new ResponseEntity<>(new ApiResponse(ex.getMessage(),false,null),HttpStatus.NOT_FOUND);
	 }
	}

}
