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

import com.project.entity.Library;
import com.project.service.LibraryService;

@RestController
@RequestMapping("/library")
public class LibraryController {
	@Autowired
	LibraryService service;

	@PostMapping("/saveLibraryData")
	public String insertData(@RequestBody Library lab) {
		String msg = service.insertData(lab);
		return msg;
	}

	@DeleteMapping("/deleteLibraryData/{id}")
	public String deleteData(@PathVariable("id") int library_id) {
		String msg = service.deleteData(library_id);
		return msg;
	}

	@PutMapping("/updateLibraryData/{library_id}")
	public String updateData(@RequestBody Library lab, @PathVariable int library_id) {
		String msg = service.updateData(lab, library_id);
		return msg;
	}

	@GetMapping("/getLibrarySingleData")
	public Library getSingleData(@RequestParam int library_id) {
		Library lab = service.getSingleData(library_id);
		return lab;
	}

	@GetMapping("/getLibraryAllData")
	public List<Library> getAllData() {
		List<Library> list = service.getAllData();
		return list;
	}

}
