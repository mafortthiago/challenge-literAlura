package com.mafort.book.catalog.main;

import com.mafort.book.catalog.model.*;
import com.mafort.book.catalog.repository.AuthorRepository;
import com.mafort.book.catalog.repository.BookRepository;
import com.mafort.book.catalog.service.ApiRequest;
import com.mafort.book.catalog.service.ConvertData;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    private final Scanner scanner = ScannerSingleton.getScanner(System.in);

    public Main(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public void menu() {
        int opcao;
        do {
            this.writeMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    SearchBookByTitle();
                    break;
                case 2:
                    listAuthors();
                    break;
                case 3:
                    listBooks();
                    break;
                case 4:
                    authorsInTheYear();
                    break;
                case 5:
                    booksByLanguage();
                    break;
            }
        } while (opcao != 0);
    }

    private void SearchBookByTitle() {
        var consumeApi = new ApiRequest();
        ConvertData convertData = new ConvertData();

        System.out.println("What is the title of the book?");
        var title = scanner.nextLine();

        try {
            var json = consumeApi.request("https://gutendex.com/books/?search=" + title.replace(" ", "+"));
            var apiResponse = convertData.getDate(json, ApiResponse.class);

            Optional<BookRecord> apiBook = apiResponse.getResults().stream().findFirst();
            if (apiBook.isPresent()) {
                Book book = new Book(apiBook.get());
                authorRepository.save(book.getAuthor());
                bookRepository.save(book);
            } else {
                System.out.println("No book found for title: " + title);
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("| ! | Error in the request.");
        }

    }

    private void listAuthors() {
        System.out.println("**** List of registered authors: ****");
        List<Author> authors = this.authorRepository.findAll();
        authors.forEach(System.out::println);
    }

    private void listBooks() {
        System.out.println("**** List of registered books: ****");
        List<Book> books = this.bookRepository.findAll();
        books.forEach(System.out::println);
    }

    private void authorsInTheYear() {
        System.out.println("**** List of authors by year: ****");
        System.out.println("What year do you want to research? ");
        int year = this.scanner.nextInt();
        this.scanner.nextLine();
        List<Author> authors = this.authorRepository.findAuthorByYear(year);
        if (!authors.isEmpty()) {
            authors.forEach(System.out::println);
        } else {
            System.out.println("There is no registered author who lived in that year.");
        }
    }

    private void booksByLanguage() {
        System.out.println("**** List of books by language: ****");
        System.out.println("What's language? (1) Português ou (2) English? ");
        int option = this.scanner.nextInt();
        this.scanner.nextLine();
        String language;
        if (option == 1) {
            language = "pt";
        } else if (option == 2) {
            language = "en";
        } else {
            throw new RuntimeException("Invalid option");
        }
        List<Book> books = this.bookRepository.findByLanguage(language);
        if (!books.isEmpty()) {
            books.forEach(System.out::println);
        } else {
            System.out.println("There are no books registered in this language.");
        }
    }

    public void writeMenu() {
        System.out.println("""
                ---------------- Book catalog ----------------
                                
                 - 1 -> Search book by title
                 - 2 -> List registered authors
                 - 3 -> List registered books
                 - 4 -> List authors alive in a certain year
                 - 5 -> List books in a certain language
                 - 0 -> Exit
                                
                 Escolha uma opção:
                ----------------------------------------------
                """);
    }
}
