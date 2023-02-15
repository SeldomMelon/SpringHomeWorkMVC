package ru.maxima.springmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.maxima.springmvc.models.Book;
import ru.maxima.springmvc.models.Person;

import java.sql.*;
import java.util.*;

@Component
public class PersonDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("select * from person order by person_id", new PersonMapper());
    }

    public Person show(int id){
        return jdbcTemplate.query("select * from person where person_id = ?",
                new Object[]{id},
                new PersonMapper()).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into person (name, year) values (?, ?)",
                person.getName(), person.getYear());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("update person set name = ? , year = ? where person_id = ?",
                updatedPerson.getName(), updatedPerson.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from person where person_id = ?", id);
    }

    public List<Book> userBook (int id) {
        return jdbcTemplate.query("select * from book where person_id = ? order by book_id",
                new Object[]{id}, new BookMapper());
    }

}
