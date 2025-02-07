package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.CollegeDao;
import com.project.entity.College;

@Service
public class CollegeService {
	@Autowired
	CollegeDao dao;

	public String insertData(College college) {
		String msg = dao.insertData(college);
		return msg;
	}

	public String deleteData(int college_id) {
		String msg = dao.deleteData(college_id);
		return msg;
	}

	public String updateData(College college, int college_id) {
		String msg = dao.updateData(college, college_id);
		return msg;
	}

	public College getSingleData(int college_id) {
		College college = dao.getSingleData(college_id);
		return college;
	}

	public List<College> getAllData() {
		List<College> list = dao.getAllData();

		return list;
	}
}
