package com.crud.library.service;

import com.crud.library.domain.Book;
import com.crud.library.repository.BookDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BooksDbService {
    private final BookDao bookDao;

    public List<Book> getBookByAuthor(String author) {
        return bookDao.getBookByAuthor(author);
    }
    public List<Book> getBookByTitle(String title) {
        return bookDao.getBookByTitle(title);
    }
    public List<Book> getBooks() {
        return bookDao.findAll();
    }
    public Book saveBook(final Book book) {
        return bookDao.save(book);
    }
    public void deleteBook(final int bookId) {
        bookDao.deleteById(bookId);
    }
}
