package com.crud.library.controller;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookDto;
import com.crud.library.mapper.BookMapper;
import com.crud.library.service.BooksDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "v1/library/books")
public class BookController {

    private final BooksDbService booksDbService;
    private final BookMapper bookMapper;

    @PostMapping(value = "createTitle")
    public void createTitle(@RequestBody BookDto bookDto) {
        Book book = bookMapper.mapBookDtoToBook(bookDto);
        booksDbService.saveBook(book);
    }

    @GetMapping(value = "getBooks")
    public List<BookDto> getBooks() {
        List<Book> books = booksDbService.getBooks();
        return bookMapper.mapToBookDtoList(books);
    }
}
