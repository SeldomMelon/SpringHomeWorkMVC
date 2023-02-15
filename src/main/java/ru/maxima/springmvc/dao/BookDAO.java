package ru.maxima.springmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.maxima.springmvc.models.Book;

import java.util.List;
import java.util.*;


@Component
public class BookDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Book> indexBook() {
        return jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
    }


    public Book showBook (int id){
        return jdbcTemplate.query("select * from book where book_id = ?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }


    public void saveBook (Book book) {
        jdbcTemplate.update("insert into book (title, author, age) values (?, ?, ?)", book.getTitle(),
                book.getAuthor(),book.getAge());
    }


    public void  updateBook (int id, Book updatedBook) {
        jdbcTemplate.update("update book set title = ?, author = ?, age = ? where book_id = ?" ,
                updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getAge(), id);
    }


    public void  deleteBook (int id) {
        jdbcTemplate.update("delete from book where book_id = ?", id);
    }



}
