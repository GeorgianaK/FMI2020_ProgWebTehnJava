package com.sal.demo;

import com.sal.demo.config.ProjectConfig;
import com.sal.demo.model.Book;
import com.sal.demo.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Scanner;

@SpringBootApplication
public class BibliotecaApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(BibliotecaApplication.class, args);

		System.out.println("Helooooo");

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

		ArrayList<Book> library = new ArrayList<Book>();
		BookService bookService = context.getBean(BookService.class);
		System.out.println("Cate carti adaugati? :");
		Scanner console = new Scanner(System.in);
		int noOfBooks = console.nextInt();
		for(int i=0; i<noOfBooks; i++) {
			bookService.addBook(library);
		}

		bookService.getAllBooks(library);
		bookService.borrowABook(library);
		bookService.borrowABook(library);

		bookService.returningABook(library);
		bookService.returningABook(library);
	}

}
