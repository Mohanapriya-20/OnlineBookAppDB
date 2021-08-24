package com.bookapp.dao;

import java.util.List;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public interface BookInter {
	void addBook(Book book);

	boolean deleteBook(int bookid) throws BookNotFoundException;

	int updateBook(int bookId, int price) throws BookNotFoundException;

	List<Book> getAllBooks();

	List<Book> getBookbyAuthor(String author)  throws AuthorNotFoundException;

	List<Book> getBookbycategory(String category)  throws CategoryNotFoundException;

}
