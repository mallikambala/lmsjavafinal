package com.capgemini.lmsspringrest.stepdefinitions;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.lmsspringrest.dao.AdminDAO;
import com.capgemini.lmsspringrest.dao.StudentDAO;
import com.capgemini.lmsspringrest.dao.StudentDAOImplementation;
import com.capgemini.lmsspringrest.dao.UserDAO;
import com.capgemini.lmsspringrest.dto.BookDetails;
import com.capgemini.lmsspringrest.dto.BooksBorrowed;
import com.capgemini.lmsspringrest.dto.RequestDetails;
import com.capgemini.lmsspringrest.dto.User;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps {

	@Autowired
	private UserDAO userDao;

	@Autowired
	private AdminDAO adminDao;

	@Autowired
	private StudentDAO studentDao;

	User user;
	BookDetails bean;

	RequestDetails details;
	BooksBorrowed borrowed;

	@Given("^User is on registration page$")
	public void user_is_on_registration_page() throws Throwable {

		user = new User();
	}

	@When("^User enters \"([^\"])\",\"([^\"])\",\"([^\"])\",\"([^\"])\",(\\d+),\"([^\"]*)\"$")
	public void user_enters(String arg2, String arg3, String arg4, String arg5, int arg6, String arg7)
			throws Throwable {

		user.setFirstName(arg2);
		user.setLastName(arg3);
		user.setEmail(arg4);
		user.setPassword(arg5);
		user.setMobileNo(arg6);
		user.setRole(arg7);
	}

	@Then("^User should be \"([^\"]*)\"$")
	public void user_should_be(String arg1) throws Throwable {
		boolean result = userDao.registerUser(user);
		Assertions.assertTrue(result);
	}

	@Given("^User is on Login page$")
	public void user_is_on_Login_page() throws Throwable {
		user = new User();
	}

	@When("^User enters \"([^\"]*)\",\"([^\"]*)\"$")
	public void user_enters(String emailId, String password) throws Throwable {
		user.setEmail(emailId);
		user.setPassword(password);
	}

	@Then("^Admin should be logged in$")
	public void admin_should_be_logged_in() throws Throwable {
		User result = userDao.authUser("aravind@gmail.com", "aravind");
		Assertions.assertNotNull(result);
	}

	@Given("^Admin is on adding book page$")
	public void admin_is_on_adding_book_page() throws Throwable {
		bean = new BookDetails();
	}

	@When("^Admin enters \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",(\\d+),\"([^\"]*)\"$")
	public void admin_enters(String bookName, String authourName, String publisherName, int copies, String bookCategory)
			throws Throwable {
		bean.setBookName(bookName);
		bean.setAuthorName(authourName);
		bean.setPublisherName(publisherName);
		bean.setBookCategory(bookCategory);
		bean.setCopies(copies);

	}

	@Then("^Book \"([^\"]*)\"$")
	public void book(String arg1) throws Throwable {
		boolean result = adminDao.addBook(bean);
		Assertions.assertTrue(result);
	}

	@Given("^Admin is on removing book page$")
	public void admin_is_on_removing_book_page() throws Throwable {

		bean = new BookDetails();
	}

	@When("^Admin enters (\\d+)$")
	public void admin_enters(int arg1) throws Throwable {
		bean.setBookId(arg1);
	}

	@Then("^Book deleted successfully$")
	public void book_deleted_successfully() throws Throwable {
		boolean result = adminDao.removeBook(13);
		Assertions.assertFalse(result);
	}

	@Given("^Admin is on issuing book page$")
	public void admin_is_on_issuing_book_page() throws Throwable {

		details = new RequestDetails();

	}

	@When("^Admin enters (\\d+),(\\d+)$")
	public void admin_enters(int arg1, int arg2) throws Throwable {
		details.setUId(arg1);
		details.setBookId(arg2);

	}

	@Then("^Book issued successfully$")
	public void book_issued_successfully() throws Throwable {
		boolean result = adminDao.bookIssue(10402, 12);
		Assertions.assertTrue(result);
	}

	@Given("^Admin is on request book page$")
	public void admin_is_on_request_book_page() throws Throwable {
		bean = new BookDetails();
		user = new User();
		studentDao = new StudentDAOImplementation();
	}

	@When("^BookId  and UserId are given (\\d+), (\\d+)$")
	public void bookid_and_UserId_are_given(int arg1, int arg2) throws Throwable {
		bean.setBookId(arg1);
		user.setId(arg2);

	}

	@Then("^Book Requested Successfully$")
	public void book_Requested_Successfully() throws Throwable {
		boolean result = studentDao.request(12, 10402);
		Assertions.assertFalse(result);
	}

	@Given("^Admin is on returning book page$")
	public void admin_is_on_returning_book_page() throws Throwable {
		borrowed = new BooksBorrowed();
		studentDao = new StudentDAOImplementation();
	}

	@When("^Book  and User are given (\\d+), (\\d+)$")
	public void book_and_User_are_given(int arg1, int arg2) throws Throwable {
		borrowed.setBookId(arg1);
		borrowed.setUId(arg2);
	}

	@Then("^Book Returned Successfully$")
	public void book_Returned_Successfully() throws Throwable {
		boolean result = studentDao.returnBook(12, 10402);
		Assertions.assertFalse(result);
	}
}