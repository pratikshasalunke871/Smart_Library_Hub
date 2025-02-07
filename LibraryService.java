package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.LibraryDao;
import com.project.entity.Library;

@Service
public class LibraryService {
	@Autowired
	LibraryDao dao;

	public String insertData(Library lab) {
		String msg = dao.insertData(lab);
		return msg;
	}

	public String deleteData(int library_id) {
		String msg = dao.deleteData(library_id);
		return msg;
	}

	public String updateData(Library lab, int library_id) {
		String msg = dao.updateData(lab, library_id);
		return msg;
	}

	public Library getSingleData(int library_id) {
		Library lab = dao.getSingleData(library_id);
		return lab;
	}

	public List<Library> getAllData() {
		List<Library> list = dao.getAllData();

		return list;

	}
}