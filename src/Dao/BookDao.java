package Dao;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;


import Bean.BookBean;
import dbconnection.DBConnection;

public class BookDao {

	private Connection getConnection() throws SQLException {
		return DBConnection.getConnection();
	}

	public boolean addBook(BookBean book) {
		String query = "INSERT INTO books (title, author, genre, year_of_publication) VALUES (?, ?, ?, ?)";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.setString(3, book.getGenre());
			stmt.setInt(4, book.getYearOfPublication());
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}



	public boolean updateBook(BookBean book) {
		String query = "UPDATE books SET title = ?, author = ?, genre = ?, year_of_publication = ?,  WHERE id = ?";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.setString(3, book.getGenre());
			stmt.setInt(4, book.getYearOfPublication());
			stmt.setInt(6, book.getId());
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	
	public boolean deleteBook(int id) {
		String query = "DELETE FROM books WHERE id = ?";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, id);
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
    public List<BookBean> getAllBooks() throws SQLException {
        List<BookBean> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BookBean book = new BookBean();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setGenre(rs.getString("genre"));
                book.setYearOfPublication(rs.getInt("year_of_publication"));
                books.add(book);
            }
        }
        return books;
    }
}
	

