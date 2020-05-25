 package com.capgemini.lmsspringrest.dao;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.lmsspringrest.dto.BooksBorrowed;

public interface StudentDAO {

	
	boolean request(int bookId, int uId);

	boolean returnBook(int bookId, int uId);
	
	LinkedList<Integer> bookHistoryDetails(int uId);
	
	List<BooksBorrowed> borrowedBook(int uId);
	
}
