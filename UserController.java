package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.User;
import com.project.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService service;

	@PostMapping("/saveUserData")
	public String insertData(@RequestBody User user) {
		String msg = service.insertData(user);
		return msg;
	}

	@DeleteMapping("/deleteUserData/{id}")
	public String deleteData(@PathVariable("id") int user_id) {
		String msg = service.deleteData(user_id);
		return msg;
	}

	@PutMapping("/updateUserData/{user_id}")
	public String updateData(@RequestBody User user, @PathVariable int user_id) {
		String msg = service.updateData(user, user_id);
		return msg;
	}

	@GetMapping("/getSingleUserData")
	public User getSingleData(@RequestParam int user_id) {
		User user = service.getSingleData(user_id);
		return user;
	}

	@GetMapping("/getAllUserData")
	public List<User> getAllData() {
		List<User> list = service.getAllData();
		return list;
	}
}