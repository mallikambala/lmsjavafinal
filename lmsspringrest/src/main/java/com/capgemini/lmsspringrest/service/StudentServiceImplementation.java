package com.capgemini.lmsspringrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.lmsspringrest.dao.StudentDAO;
import com.capgemini.lmsspringrest.dto.BooksBorrowed;

@Service
public class StudentServiceImplementation implements StudentService {

	@Autowired
	private StudentDAO dao;

	@Override
	public boolean request(int bookId, int id) {
		return dao.request(bookId, id);
	}

	@Override
	public boolean returnBook(int bookId, int id) {
		return dao.returnBook(bookId, id);
	}

	@Override
	public List<BooksBorrowed> getBorrowedBooks(int uId) {
		return dao.getBorrowedBooks(uId);
	}

}
