package com.sal.tema2.repository;


import com.sal.tema2.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class BookRepository {

    private List<Book> books;

    public List<Book> findAll() {
        return books;
    }

    public void createBook(Book book) {
        books.add(book);
    }

    public Optional<Book> findBookByISBN(Long isbn) {
        for(Book book: books) {
            if(book.getIsbn().equals(isbn)) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    public List<Book> findAllByTitle(String title) {
        List<Book> booksWithTitle =  new ArrayList<>();
        for(Book book: books) {
            if(book.getTitle().contains(title)) {
                booksWithTitle.add(book);
            }
        }
        return booksWithTitle;
    }

}
