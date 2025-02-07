package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.UserDao;
import com.project.entity.User;

@Service
public class UserService {
	@Autowired
	UserDao dao;

	public String insertData(User user) {
		String msg = dao.insertData(user);
		return msg;
	}

	public String deleteData(int user_id) {
		String msg = dao.deleteData(user_id);
		return msg;
	}

	public String updateData(User user, int user_id) {
		String msg = dao.updatedata(user, user_id);
		return msg;
	}
	public User getSingleData(int user_id) {
		User user=dao.getSingleData(user_id);
		return user;
	}

	public List<User> getAllData() {
		List<User> list=dao.getAllData();
		return list;
	}
}

