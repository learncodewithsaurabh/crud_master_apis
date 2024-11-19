package com.codewithsaurabh.crud_master_apis.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithsaurabh.crud_master_apis.entity.User;
import com.codewithsaurabh.crud_master_apis.exception.ResourceNotFoundException;
import com.codewithsaurabh.crud_master_apis.payload.UserDto;
import com.codewithsaurabh.crud_master_apis.repository.UserRepo;
import com.codewithsaurabh.crud_master_apis.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
//		user.setUser_id(userDto.getUser_id());
//		user.setUsername(userDto.getUsername());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;
	}
	
	
	public UserDto userToUserDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
//		userDto.setUser_id(user.getUser_id());
//		userDto.setUsername(user.getUsername());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User updateUser =  this.userRepo.findById(userId)
											.orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		updateUser.setUsername(userDto.getUsername());
		updateUser.setEmail(userDto.getEmail());
		updateUser.setPassword(userDto.getPassword());
		updateUser.setAbout(userDto.getAbout());
	 User updatedUser = this.userRepo.save(updateUser);
		
		return this.userToUserDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
	User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id",userId));
		return this.userToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users = this.userRepo.findAll();
		return users.stream().map(user-> this.userToUserDto(user)).collect(Collectors.toList());
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
									.orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		this.userRepo.delete(user);
	}

}
