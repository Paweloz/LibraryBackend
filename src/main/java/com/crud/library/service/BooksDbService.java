package com.crud.library.service;

import com.crud.library.common.Validator;
import com.crud.library.domain.Book;
import com.crud.library.repository.BookDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class BooksDbService {
    private final BookDao bookDao;
    private final Validator validator;

    public List<Book> getBookByAuthor(String author) {
        validator.validateBookByAuthorOrTitle(author);
        return bookDao.getBookByAuthor(author);
    }
    public List<Book> getBookByTitle(String title) {
        validator.validateBookByAuthorOrTitle(title);
        return bookDao.getBookByTitle(title);
    }
    public List<Book> getBooks() {
        return bookDao.findAll();
    }
    public Book saveBook(final Book book) {
        return bookDao.save(book);
    }
    public Book updateBook(final Book book) {
        validator.validateBookById(book.getId());
        return bookDao.save(book);
    }
    public void deleteBook(final Long bookId) {
        validator.validateBookById(bookId);
        bookDao.deleteById(bookId);
    }
}
