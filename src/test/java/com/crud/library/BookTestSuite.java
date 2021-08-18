package com.crud.library;

import com.crud.library.domain.Book;
import com.crud.library.domain.Copies;
import com.crud.library.repository.BookDao;
import com.crud.library.repository.CopiesDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
@Transactional
public class BookTestSuite {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private CopiesDao copiesDao;

    @Test
    void testSaveBookAndReadBook() {
        //Given
        Book book = new Book("Title", "Author", 1895, new ArrayList<>());
        bookDao.save(book);
        //When
        Long bookId = book.getId();
        Book savedBook = bookDao.findById(bookId);
        //Then
        Assertions.assertEquals(1895, savedBook.getPublicationYear());
        //CleanUp
        bookDao.deleteById(bookId);
    }

    @Test
    void testUpdateBook() {
        //Given
        Book book = new Book("Title", "Author", 1895, new ArrayList<>());
        bookDao.save(book);
        //When
        Long bookId = book.getId();
        Book savedBook = bookDao.findById(bookId);
        savedBook.setAuthor("Mickiewicz");
        bookDao.save(savedBook);
        Book updatedBook = bookDao.findById(bookId);
        //Then
        Assertions.assertEquals(1895, updatedBook.getPublicationYear());
        Assertions.assertEquals("Mickiewicz", updatedBook.getAuthor());
        Assertions.assertEquals(updatedBook.getId(), savedBook.getId());
        //CleanUp
        bookDao.deleteById(bookId);
    }

    @Test
    void testBookCopiesRelation() {
        //Given
        Book book = new Book("Title", "Author", 1895, new ArrayList<>());
        Copies bookCopy = new Copies(book, "AVALIABLE");
        Copies bookCopy2 = new Copies(book, "TAKEN");
        Copies bookCopy3 = new Copies(book, "AVALIABLE");
        book.setCopiesList(Arrays.asList(bookCopy, bookCopy2, bookCopy3));
        bookDao.save(book);
        copiesDao.save(bookCopy);
        copiesDao.save(bookCopy2);
        copiesDao.save(bookCopy3);
        //When
        Long bookId = book.getId();
        Book savedBook = bookDao.findById(bookId);

        //Then
        Assertions.assertEquals(3, savedBook.getCopiesList().size());

        //CleaUp
        bookDao.deleteById(bookId);
    }
    @Test
    void testShouldDeleteCopiesWhileDeletingBook() {
        //Given
        Book book = new Book("Title", "Author", 1895, new ArrayList<>());
        Copies bookCopy = new Copies(book, "AVALIABLE");
        Copies bookCopy2 = new Copies(book, "TAKEN");
        Copies bookCopy3 = new Copies(book, "AVALIABLE");
        book.setCopiesList(Arrays.asList(bookCopy, bookCopy2, bookCopy3));
        bookDao.save(book);
        copiesDao.save(bookCopy);
        copiesDao.save(bookCopy2);
        copiesDao.save(bookCopy3);
        //When
        bookDao.deleteById(book.getId());

        //Then
        Assertions.assertFalse(copiesDao.existsById(bookCopy.getId()));
        Assertions.assertFalse(copiesDao.existsById(bookCopy2.getId()));
        Assertions.assertFalse(copiesDao.existsById(bookCopy3.getId()));
    }
}
