package com.assignment.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.assignment.training.model.User;
import com.assignment.training.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	public User validate(User user) {
		try {
		User u=userRepo.findById(user.getUser_id()).get();
		if((user.getUser_id()==u.getUser_id()) && user.getPassword().equals(u.getPassword())) {
		return u;
		}else {
			return null;
		}
		}catch(Exception e)
		{
			return null;
		}
	}

}
