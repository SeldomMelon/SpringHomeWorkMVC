package ru.maxima.springmvc.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.maxima.springmvc.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper  implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();

        person.setId(resultSet.getInt("person_id"));
        person.setName(resultSet.getString("name"));
        person.setYear(resultSet.getInt("year"));

        return person;
    }
}
