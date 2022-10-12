package com.myinstitute.generics;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
public class BookController extends GenericController<Book> {

    public BookController(BookRepository bookRepository) {
        super(bookRepository);
    }
}