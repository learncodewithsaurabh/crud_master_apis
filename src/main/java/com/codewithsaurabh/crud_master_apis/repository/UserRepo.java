package com.codewithsaurabh.crud_master_apis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithsaurabh.crud_master_apis.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
