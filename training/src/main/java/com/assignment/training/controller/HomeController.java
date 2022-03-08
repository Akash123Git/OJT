package com.assignment.training.controller;

import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.training.model.User;
import com.assignment.training.service.UserService;

@RestController
@RequestMapping("/auth")
public class HomeController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/validate")
	public ResponseEntity doLogin(@RequestBody User user) {
		User u=userService.validate(user);
		if(u!=null) {
			return ResponseEntity.ok(u);
		}
		return ResponseEntity.badRequest().body("Enter valid user credentials");
	}
}
