package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.LibraryCardDao;
import com.project.entity.LibraryCard;

@Service
public class LibraryCardService {
	@Autowired
	LibraryCardDao dao;

	public String insertData(LibraryCard labcard) {
		String msg = dao.insertData(labcard);
		return msg;
	}

	public String deleteData(int card_id) {
		String msg = dao.deleteData(card_id);
		return msg;
	}

	public String updateData(LibraryCard labcard, int card_id) {
		String msg = dao.updateData(labcard, card_id);
		return msg;
	}

	public LibraryCard getSingleData(int card_id) {
		LibraryCard labcard = dao.getSingleData(card_id);
		return labcard;
	}

	public List<LibraryCard> getAllData() {
		List<LibraryCard> list = dao.getAllData();

		return list;
	}
}
