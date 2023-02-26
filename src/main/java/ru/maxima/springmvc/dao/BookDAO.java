package ru.maxima.springmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.maxima.springmvc.models.Book;
import ru.maxima.springmvc.models.Person;

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
        return jdbcTemplate.query("select * from book order by book_id", new BookMapper());
    }

    public Book showBook (int id){
        return jdbcTemplate.query("select * from book where book_id = ?", new Object[]{id},
                new BookMapper()).stream().findAny().orElse(null);
    }

    public void saveBook (Book book) {
        jdbcTemplate.update("insert into book (title, author, age) values (?, ?, ?)", book.getTitle(),
                book.getAuthor(),book.getAge());
    }

    public void updateBook (int id, Book updatedBook) {
        jdbcTemplate.update("update book set title = ?, author = ?, age = ? where book_id = ?" ,
                updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getAge(), id);
    }

    public void deleteBook (int id) {
        jdbcTemplate.update("delete from book where book_id = ?", id);
    }

    public void availableBook(Integer bookId) {
        jdbcTemplate.update("update  book set person_id = ? where book_id = ?", null, bookId);
    }

    public void addUserBook(int person_id, int bookId) {
        jdbcTemplate.update("update book set person_id = ? where book_id = ?", person_id, bookId);
    }

    public Person searchBook (int personId) {
        return jdbcTemplate.query("select * from person where person_id = ?", new Object[]{personId},
                new PersonMapper()).stream().findAny().orElse(null);
    }
}
