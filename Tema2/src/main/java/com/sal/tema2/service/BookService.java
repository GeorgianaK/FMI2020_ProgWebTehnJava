package com.sal.tema2.service;


import com.sal.tema2.model.Book;
import com.sal.tema2.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    //daca exista, se creste nr volumelor, daca nu, se adauga(pentru add book)
    public void addBook(Book book) {
        Optional<Book> findBook = bookRepository.findBookByISBN(book.getIsbn());
        if(findBook.isPresent()) {
            //se creste nr volumelor disponibile
            findBook.get().setNoOfVolumes(findBook.get().getNoOfVolumes() + 1);
        }
        else {
            //se adauga
            bookRepository.createBook(book);
        }
    }

    public List<Book> getBookByTitle(String title) throws Exception {
        if(bookRepository.findAllByTitle(title).size() > 0) {
            return bookRepository.findAllByTitle(title);
        }
        else {
            throw new Exception("Book not found!");
        }
    }

    public boolean borrowABook(Long isbn) {
        Optional<Book> findBook = bookRepository.findBookByISBN(isbn);
        if(findBook.isPresent()) {
            if(findBook.get().getNoOfVolumes() > 0 ) {
                findBook.get().setNoOfVolumesGiven(findBook.get().getNoOfVolumesGiven() + 1);
                findBook.get().setNoOfVolumes(findBook.get().getNoOfVolumes() - 1);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean returnABook(Long isbn) {
        Optional<Book> findBook = bookRepository.findBookByISBN(isbn);
        if(findBook.isPresent()) {
            if(findBook.get().getNoOfVolumesGiven() > 0) {
                findBook.get().setNoOfVolumes(findBook.get().getNoOfVolumes() + 1);
                findBook.get().setNoOfVolumesGiven(findBook.get().getNoOfVolumesGiven() - 1);
                return true;
            }
            return false;
        }
        return false;
    }


}
