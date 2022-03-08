package com.assignment.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.training.model.User;

public interface UserRepo extends JpaRepository<User,Long>{

}
