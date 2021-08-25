package com.bookapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;
import com.mysql.cj.protocol.Resultset;

public class BookImpl implements BookInter {
	Connection connection = null;
	PreparedStatement st = null;

	@Override
	public void addBook(Book book) {
		try {
			String sql = "insert into Book values(?,?,?,?,?)";
			connection = ModelDAO.openConnection();
			st = connection.prepareStatement(sql, ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
			st.setString(1, book.getTitle());
			st.setString(2, book.getAuthor());
			st.setString(3, book.getCategory());
			st.setInt(4, book.getBookid());
			st.setInt(5, book.getPrice());
			st.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {

				if (st != null) {
					st.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ModelDAO.closeConnection();
		}
	}

	@Override
	public boolean deleteBook(int bookid) {
		// TODO Auto-generated method stub
		try {
			String delsql = "delete from Book where bookid=?";
			connection = ModelDAO.openConnection();
			st = connection.prepareStatement(delsql, ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
			st.setInt(1, bookid);
			st.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {

				if (st != null) {
					st.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ModelDAO.closeConnection();
		}
		return false;
	}

	@Override
	public Book getBookById(int bookid) throws BookNotFoundException {
		// List<Book> BookbyId=new ArrayList<>();
		Book book = new Book();
		try {
			String idsql = "Select * from Book where bookid=?";
			connection = ModelDAO.openConnection();
			st = connection.prepareStatement(idsql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			st.setInt(1, bookid);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {

				book.setTitle(rs.getString(1));
				book.setAuthor(rs.getString(2));
				book.setCategory(rs.getString(3));
				book.setBookid(rs.getInt(4));
				book.setPrice(rs.getInt(5));
				// BookbyId.add(book);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {

				if (st != null) {
					st.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ModelDAO.closeConnection();
		}
		return book;
	}

	@Override
	public int updateBook(int bookId, int price) throws BookNotFoundException {
		int rs = 0;
		try {
			String updsql = "update Book set price=? where bookId=?";
			connection = ModelDAO.openConnection();
			st = connection.prepareStatement(updsql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			st.setInt(1, price);
			st.setInt(2, bookId);
			rs = st.executeUpdate();
			if (rs == 0) {
				throw new BookNotFoundException("not found");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {

				if (st != null) {
					st.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ModelDAO.closeConnection();
		}
		return rs;
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> getAllBooks = new ArrayList<>();
		try {

			String selsql = "select * from Book";
			connection = ModelDAO.openConnection();
			st = connection.prepareStatement(selsql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setTitle(rs.getString(1));
				book.setAuthor(rs.getString(2));
				book.setCategory(rs.getString(3));
				book.setBookid(rs.getInt(4));
				book.setPrice(rs.getInt(5));
				getAllBooks.add(book);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {

				if (st != null) {
					st.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ModelDAO.closeConnection();
		}
		return getAllBooks;
	}

	@Override
	public List<Book> getBookbyAuthor(String author) throws AuthorNotFoundException {
		List<Book> getBooksbyAuthor = new ArrayList<>();
		try {
			String selsql1 = "select * from Book where author=?";
			connection = ModelDAO.openConnection();
			st = connection.prepareStatement(selsql1, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			st.setString(1, author);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setTitle(rs.getString(1));
				book.setAuthor(rs.getString(2));
				book.setCategory(rs.getString(3));
				book.setBookid(rs.getInt(4));
				book.setPrice(rs.getInt(5));
				getBooksbyAuthor.add(book);

			}
			if (getBooksbyAuthor.isEmpty()) {
				throw new AuthorNotFoundException("Invalid Author name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {

				if (st != null) {
					st.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ModelDAO.closeConnection();
		}

		return getBooksbyAuthor;
	}

	@Override
	public List<Book> getBookbycategory(String category) throws CategoryNotFoundException {
		List<Book> getBooksbyCategory = new ArrayList<>();

		try {
			String selsql1 = "select * from Book where category=?";
			connection = ModelDAO.openConnection();
			st = connection.prepareStatement(selsql1, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			st.setString(1, category);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setTitle(rs.getString(1));
				book.setAuthor(rs.getString(2));
				book.setCategory(rs.getString(3));
				book.setBookid(rs.getInt(4));
				book.setPrice(rs.getInt(5));
				getBooksbyCategory.add(book);
			}
			if (getBooksbyCategory.isEmpty()) {
				throw new CategoryNotFoundException("Invalid Category name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {

				if (st != null) {
					st.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ModelDAO.closeConnection();
		}
		return getBooksbyCategory;
	}

}
