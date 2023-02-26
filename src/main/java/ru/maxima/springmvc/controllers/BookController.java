package ru.maxima.springmvc.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.maxima.springmvc.dao.BookDAO;
import ru.maxima.springmvc.dao.PersonDAO;
import ru.maxima.springmvc.models.Book;
import ru.maxima.springmvc.models.Person;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/library")
public class BookController {

    private final BookDAO bookDAO;

    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String indexBook (Model model) {
        model.addAttribute("library", bookDAO.indexBook());
        return "books/indexBook";
    }

    @GetMapping("/{id}")
    public String showBook (@PathVariable ("id") int id, Model model) {
        addSearch(id, model);
        return "books/showBook";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book")Book book) {
        return "books/newBook";
    }

    @PostMapping
    public String createBook (@ModelAttribute("book") @Valid Book book,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/newBook";
        }
        bookDAO.saveBook(book);
        return "redirect:/library";
    }

    @GetMapping("/{id}/edit")
    public String editBook (Model model,@PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.showBook(id));
        return "books/editBook";
    }

    @PatchMapping("{id}")
    public  String updateBook (@ModelAttribute("book") @Valid Book book,
                               BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors()) {
            return "books/editBook";
        }
        bookDAO.updateBook(id, book);
        return "redirect:/library";
    }

    @DeleteMapping("{id}")
    public String deleteBook (@PathVariable("id") int id) {
        bookDAO.deleteBook(id);
        return "redirect:/library";
    }

    @PostMapping("/available")
    public String availableBook(@RequestParam Integer book_id, @RequestParam Integer person_id, Model model) {
        bookDAO.availableBook(book_id);
        return "redirect:/library/" + book_id;
    }

    @PostMapping("/choice")
    public String addUserBook(@RequestParam Integer id, @RequestParam Integer book_id, Model model){
        bookDAO.addUserBook(id, book_id);
        return "redirect:/library/" + book_id;
    }

    public void addSearch (int book_id, Model model) {
        Book book = bookDAO.showBook(book_id);
        Person person = bookDAO.searchBook(book.getPersonId());
        List<Person> people = personDAO.index();
        model.addAttribute("person", person);
        model.addAttribute("book", book);
        model.addAttribute("people",people);
    }

}


