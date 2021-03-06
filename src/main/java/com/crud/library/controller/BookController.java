package com.crud.library.controller;

import com.crud.library.domain.Book;
import com.crud.library.domain.dto.BookDto;
import com.crud.library.exception.BookNotFoundException;
import com.crud.library.mapper.BookMapper;
import com.crud.library.service.BooksDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "v1/library/books")
public class BookController {

    private final BooksDbService booksDbService;
    private final BookMapper bookMapper;

    @PostMapping(value = "createTitle")
    public BookDto createTitle(@RequestBody BookDto bookDto) {
        Book book = bookMapper.mapBookDtoToBook(bookDto);
        return bookMapper.mapBookToBookDto(booksDbService.saveBook(book)) ;
    }

    @PutMapping(value = "updateBook")
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        Book book = bookMapper.mapBookDtoToBook(bookDto);
        Book savedBook;
        try {
            savedBook = booksDbService.updateBook(book);
        }catch ( BookNotFoundException bnf ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, bnf.getMessage());
        }
        return bookMapper.mapBookToBookDto(savedBook);
    }

    @GetMapping(value = "getBooks")
    public List<BookDto> getBooks() {
        List<Book> books = booksDbService.getBooks();
        return bookMapper.mapToBookDtoList(books);
    }

    @GetMapping(value = "getBookByAuthor")
    public List<BookDto> getBookByAuthor(@RequestParam String author) {
        List<Book> booksByAuthor;
        try {
            booksByAuthor = booksDbService.getBookByAuthor(author);
        }catch ( BookNotFoundException bnf ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, bnf.getMessage());
        }
        return bookMapper.mapToBookDtoList(booksByAuthor);
    }

    @GetMapping(value = "getBookByTitle")
    public List<BookDto> getBookByTitle(@RequestParam String title) {
        List<Book> booksByTitle;
        try {
            booksByTitle = booksDbService.getBookByTitle(title);
        }catch ( BookNotFoundException bnf ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, bnf.getMessage());
        }
        return bookMapper.mapToBookDtoList(booksByTitle);
    }

    @DeleteMapping(value = "deleteBookById")
    public void deleteBookById(@RequestParam Long bookId) {
       try {
           booksDbService.deleteBook(bookId);
       } catch ( BookNotFoundException bnf ) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, bnf.getMessage());
       }
    }
}
