package ru.maxima.springmvc.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.maxima.springmvc.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();

        book.setId(resultSet.getInt("book_id"));
        book.setPersonId(resultSet.getInt("person_id"));
        book.setTitle(resultSet.getString("title"));
        book.setAuthor(resultSet.getString("author"));
        book.setAge(resultSet.getInt("age"));

        return book;

    }
}
