package com.capgemini.lmsspringrest.dao;

import java.util.List;

import com.capgemini.lmsspringrest.dto.BookDetails;
import com.capgemini.lmsspringrest.dto.BookIssue;
import com.capgemini.lmsspringrest.dto.RequestDetails;
import com.capgemini.lmsspringrest.dto.User;

public interface AdminDAO {

	boolean addBook(BookDetails bookInfo);

	boolean removeBook(int bookId);	

	boolean updateBook(BookDetails bookInfo);

	boolean bookIssue(int uId, int bookId);

	List<RequestDetails> showRequests();

	List<BookIssue> showIssuedBooks();

	List<User> showUsers();
}
