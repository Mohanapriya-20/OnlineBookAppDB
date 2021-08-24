package com.bookapp.main;

import com.bookapp.bean.Book;
import com.bookapp.dao.BookImpl;
import com.bookapp.dao.BookInter;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

import java.util.Scanner;
public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Select your Choice");
		System.out.println("1.Add Books Details \n2.Delete Book Details\n3.Update Book Details\n4.Book Details\n5.Author Name\n6.Category Name");
		BookInter obj=new BookImpl();
		int n=sc.nextInt();
		switch(n)
		{
		case 1:
			System.out.println("Enter Title");
			String title=sc.next();
			System.out.println("Enter Author Name");
			String author=sc.next();
			System.out.println("Enter Category");
			String category=sc.next();
			System.out.println("Enter Book Id");
			int bookid=sc.nextInt();
			System.out.println("Enter Price");
			int price=sc.nextInt();
			Book book= new Book();
			book.setTitle(title);
			book.setAuthor(author);
			book.setCategory(category);
			book.setBookid(bookid);
			book.setPrice(price);
			obj.addBook(book);
		case 2:
			try {
				System.out.println("Enter the Book Id to Delete");
				int id=sc.nextInt();
				boolean val=obj.deleteBook(id);
				if(val== false)
					System.out.println("Deleted..");
			} catch (BookNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			System.out.println("Enter Book ID and Price to Update");
			int id=sc.nextInt();
			int price1=sc.nextInt();
			try {
				obj.updateBook(id, price1);
			} catch (BookNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 4:
			System.out.println(obj.getAllBooks());
			break;
		
		case 5:
			System.out.println("Enter Author Name");
			String author1=sc.next();
			try {
				System.out.println(obj.getBookbyAuthor(author1));
			} catch (AuthorNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case 6:
			System.out.println("Enter Category Name");
			String category1=sc.next();
			try {
				System.out.println(obj.getBookbycategory(category1));
			} catch (CategoryNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}

			
		
		
	}

}
