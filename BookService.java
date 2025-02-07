package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.BookDao;
import com.project.entity.Book;

@Service
public class BookService {
	@Autowired
	BookDao dao;

	public String insertData(Book book) {
		String msg = dao.insretData(book);
		return msg;
	}

	public String deleteData(int book_id) {
		String msg = dao.deleteData(book_id);
		return msg;
	}

	public String updateData(Book book, int book_id) {
		String msg = dao.updateData(book, book_id);
		return msg;
	}

	public Book getSingleData(int book_id) {
		Book book = dao.getSingleData(book_id);
		return book;
	}

	public List<Book> getAllData() {
		List<Book> list = dao.getAllData();
		return list;
	}
}
