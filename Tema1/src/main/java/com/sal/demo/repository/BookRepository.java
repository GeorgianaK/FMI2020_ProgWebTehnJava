package com.sal.demo.repository;

import com.sal.demo.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class BookRepository {

    public void addBook(ArrayList<Book> library, Book book) {
        library.add(book);
    }

    public void viewBook(Book book) {
        System.out.println(book.getIsbn() + "    " + book.getTitle());
        System.out.println("Autori: " + book.getAuthors());
        System.out.println("Detalii: gen: " + book.getGenre() + "/ anul: " + book.getYear() + "/ publicatie: " + book.getPublishingHouse() + "/ pagini: " + book.getNoOfPages());
        System.out.println("Numar de volume disponibile: " + book.getNoOfVolumes() + "|| Numar de volume imprumutate: " + book.getNoOfVolumesGiven());
        System.out.println("-----------");
    }

    public ArrayList<Book> findBookByTitle(ArrayList<Book> library, String title) {
        ArrayList<Book> resultBook = new ArrayList<>();

        for(int i=0; i< library.size(); i++) {
            if(library.get(i).getTitle().toLowerCase().contains(title.toLowerCase())) {
                resultBook.add(library.get(i));
            }
        }
        return resultBook;
    }

    public Optional<Book> findBookByISBN(ArrayList<Book> library, Long isbn) {
        Book found = new Book();
        for(int i=0; i<library.size(); i++) {
            if(library.get(i).getIsbn().equals(isbn)) {
                found = library.get(i);
            }
        }
        return Optional.of(found);
    }

}
