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

import com.project.entity.Book;
import com.project.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	BookService service;

	@PostMapping("/saveBookData")
	public String insretData(@RequestBody Book book) {
		String msg = service.insertData(book);
		return msg;
	}

	@DeleteMapping("/deleteBookData/{id}")
	public String deleteData(@PathVariable("id") int book_id) {
		String msg = service.deleteData(book_id);
		return msg;
	}

	@PutMapping("/updateBookData/{book_id}")
	public String updateData(@RequestBody Book book, @PathVariable int book_id) {
		String msg = service.updateData(book, book_id);
		return msg;
	}

	@GetMapping("/getBookSingleData")
	public Book getSingleData(@RequestParam int book_id) {
		Book book = service.getSingleData(book_id);
		return book;
	}

	@GetMapping("/getBookAllData")
	public List<Book> getAllData() {
		List<Book> list = service.getAllData();
		return list;
	}

}
