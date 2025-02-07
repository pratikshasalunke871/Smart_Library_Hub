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

import com.project.entity.LibraryCard;
import com.project.service.LibraryCardService;

@RestController
@RequestMapping("/labcard")
public class LibraryCardController {
	@Autowired
	LibraryCardService service;

	@PostMapping("/saveLibrayCardData")
	public String insertData(@RequestBody LibraryCard labcard) {
		String msg = service.insertData(labcard);
		return msg;
	}

	@DeleteMapping("/deleteLibraryCardData/{id}")
	public String deleteData(@PathVariable("id") int card_id) {
		String msg = service.deleteData(card_id);
		return msg;
	}

	@PutMapping("/updateLibraryCardData/{card_id}")
	public String updateData(@RequestBody LibraryCard labcard, @PathVariable int card_id) {
		String msg = service.updateData(labcard, card_id);
		return msg;
	}

	@GetMapping("/getLibraryCardSingleData")
	public LibraryCard getSingleData(@RequestParam int card_id) {
		LibraryCard labcard = service.getSingleData(card_id);
		return labcard;
	}

	@GetMapping("/getLibraryCardAllData")
	public List<LibraryCard> getAllData() {
		List<LibraryCard> list = service.getAllData();
		return list;
	}

}
