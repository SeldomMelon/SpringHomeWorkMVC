package ru.maxima.springmvc.models;

public class Book {

    private int id;
    private int personId;

    private String title;

    private String author;

    private int age;

    public Book() {
    }

    public Book(int id, int personId, String title, String author, int age) {
        this.id = id;
        this.personId = personId;
        this.title = title;
        this.author = author;
        this.age = age;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
