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

import com.project.entity.College;
import com.project.service.CollegeService;

@RestController
@RequestMapping("/college")
public class CollegeController {
	@Autowired
	CollegeService service;

	@PostMapping("/saveCollegeData")
	public String insertData(@RequestBody College college) {
		String msg = service.insertData(college);
		return msg;
	}

	@DeleteMapping("/deleteCollegeData/{id}")
	public String deleteData(@PathVariable("id") int college_id) {
		String msg = service.deleteData(college_id);
		return msg;
	}

	@PutMapping("/updateCollegeData/{college_id}")
	public String updateData(@RequestBody College college, @PathVariable int college_id) {
		String msg = service.updateData(college, college_id);
		return msg;
	}

	@GetMapping("/getCollegeSingleData")
	public College getSingleData(@RequestParam int college_id) {
		College college = service.getSingleData(college_id);
		return college;
	}

	@GetMapping("/getCollegeAllData")
	public List<College> getAllData() {
		List<College> list = service.getAllData();
		return list;
	}

}
