package com.sal.demo.service;

import com.sal.demo.aspects.BorrowedErrorLogger;
import com.sal.demo.aspects.ReturnedErrorLogger;
import com.sal.demo.model.Book;
import com.sal.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void addBook(ArrayList<Book> library) {
        Scanner console = new Scanner(System.in);
        Book newBook = new Book();

        System.out.println("Adaugati titlul cartii:" );
        newBook.setTitle(console.nextLine());

        System.out.println("Adaugati ISBN-ul: ");
        newBook.setIsbn(console.nextLong());
        console.nextLine();
        System.out.println("Adaugati autorii separati prin ',' : ");
        newBook.setAuthors(console.nextLine());

        System.out.println("Adaugati genul cartii: ");
        newBook.setGenre(console.nextLine());

        System.out.println("Adaugati numarul de pagini: ");
        newBook.setNoOfPages(console.nextInt());

        console.nextLine();
        System.out.println("Adaugati anul publicarii: ");
        newBook.setYear(console.nextInt());

        console.nextLine();
        System.out.println("Adaugati editura: ");
        newBook.setPublishingHouse(console.nextLine());

        System.out.println("Adaugati numarul de volume disponibile: ");
        newBook.setNoOfVolumes(console.nextInt());
        newBook.setNoOfVolumesGiven(0);

        //System.out.println(newBook.getTitle() + " " + newBook.getIsbn() + " " + newBook.getAuthors()+" " +newBook.getGenre()+" " +newBook.getNoOfPages() + " " + newBook.getYear()+ " " +newBook.getPublishingHouse() + " " +newBook.getNoOfVolumes());

        bookRepository.addBook(library, newBook);
    }

    public void getAllBooks(ArrayList<Book> library) {
        for(int i=0; i< library.size(); i++) {
            bookRepository.viewBook(library.get(i));
        }
    }

    @BorrowedErrorLogger
    public void borrowABook(ArrayList<Book> library) throws Exception{
        Scanner console = new Scanner(System.in);

        System.out.println("Cautati dupa titlu");
        System.out.println("Introduceti titlul cartii: ");
        String title = console.nextLine();

        ArrayList<Book> booksFound = bookRepository.findBookByTitle(library, title);
        if(booksFound.size() == 0) {
            System.out.println("Rezultate returnate: 0");
            return;
        }
        else {
            System.out.println("Rezultate returnate: " + booksFound.size());
            for(int i=0; i<booksFound.size(); i++) {
                System.out.println(i+1 + ".   " + booksFound.get(i).getTitle() + "       " + booksFound.get(i).getAuthors());
            }
            System.out.println("INTRODUCETI NUMARUL CARTII DORITE: ");
            int bookId = console.nextInt();
            if((bookId-1 >= 0) && (bookId-1 < booksFound.size())) {
                Long bookISBN = booksFound.get(bookId-1).getIsbn();
                Optional<Book> toBeBorrowedBook = bookRepository.findBookByISBN(library,bookISBN);
                if(toBeBorrowedBook.isPresent()) {
                    if(toBeBorrowedBook.get().getNoOfVolumes() > 0) {
                        toBeBorrowedBook.get().setNoOfVolumesGiven(toBeBorrowedBook.get().getNoOfVolumesGiven() + 1);
                        toBeBorrowedBook.get().setNoOfVolumes(toBeBorrowedBook.get().getNoOfVolumes() - 1);

                        System.out.println("OPERATIUNE EFECTUATA CU SUCCES!");
                    }
                    else {
                        throw new Exception("Not enough volumes!");
                    }
                }
                else {
                    throw new Exception("Book not found!");
                }

            }
            else {
                throw new Exception("Invalid input!");
            }
        }
    }

    @ReturnedErrorLogger
    public void returningABook(ArrayList<Book> library) throws Exception{
        Scanner console = new Scanner(System.in);
        System.out.println("Introduceti titlul: ");
        String title = console.nextLine();

        ArrayList<Book> booksFound = bookRepository.findBookByTitle(library, title);
        if(booksFound.size() == 0) {
            throw new Exception("Book not found!");
        }
        for(int i=0; i<booksFound.size(); i++) {
            System.out.println(i+1 + ".   " + booksFound.get(i).getTitle() + "       " + booksFound.get(i).getAuthors());
        }
        System.out.println("INTRODUCETI NUMARUL CARTII: ");
        int bookId = console.nextInt();
        if((bookId-1 >= 0) && (bookId-1 < booksFound.size())) {
            Long bookISBN = booksFound.get(bookId-1).getIsbn();
            Optional<Book> borrowedBook = bookRepository.findBookByISBN(library,bookISBN);
            if(borrowedBook.isPresent()) {
                if(borrowedBook.get().getNoOfVolumesGiven() > 0) {
                    borrowedBook.get().setNoOfVolumesGiven(borrowedBook.get().getNoOfVolumesGiven() - 1);
                    borrowedBook.get().setNoOfVolumes(borrowedBook.get().getNoOfVolumes() + 1);

                    System.out.println("OPERATIUNE EFECTUATA CU SUCCES!");
                }
                else {
                    throw new Exception("Not borrowed from here!");
                }
            }
            else {
                throw new Exception("Book not found!");
            }

        }
        else {
            throw new Exception("Invalid input!");
        }
    }

}
