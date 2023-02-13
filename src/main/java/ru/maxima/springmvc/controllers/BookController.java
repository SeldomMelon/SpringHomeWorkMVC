package ru.maxima.springmvc.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.maxima.springmvc.dao.BookDAO;
import ru.maxima.springmvc.models.Book;

import javax.validation.Valid;

@Controller
@RequestMapping("/library")
public class BookController {

    private final BookDAO bookDAO;

    @Autowired
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String indexBook (Model model) {
        model.addAttribute("library", bookDAO.indexBook());
        return "books/indexBook";
    }

    @GetMapping("/{id}")
    public String showBook (@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDAO.showBook(id));
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
}


