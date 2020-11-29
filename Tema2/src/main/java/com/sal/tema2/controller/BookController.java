package com.sal.tema2.controller;


import com.sal.tema2.model.Book;
import com.sal.tema2.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class BookController {

    //A trebuit sa modific proiectul initial deoarece a fost gandit pentru a interactiona prin terminal cu utilizatorul
    //Astfel, functiile se comporta in acelasi mod, dar sunt scrise intr-o forma prietenoasa pentru implementarea endpoint-urilor

    private final BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @PostMapping("add")
    public Book addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return book;
    }

    @GetMapping("{title}")
    public ResponseEntity<List<Book>> getByTitle(@PathVariable("title") String title) {
        try{
                return ResponseEntity.ok(bookService.getBookByTitle(title));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    //Pentru a imprumuta, se va afisa inainte lista cu toate cartile si se va copia isbn-ul care va fi folosit in request-ul de imprumutare/returnare
    @PostMapping("borrow/{isbn}")
    public ResponseEntity borrowBook(@PathVariable("isbn") Long isbn) {
        if(bookService.borrowABook(isbn)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("return/{isbn}")
    public ResponseEntity returnBook(@PathVariable("isbn") Long isbn) {
        if(bookService.returnABook(isbn)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
