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
public class CopiesTestSuite {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private CopiesDao copiesDao;

    @Test
    void testSaveCopyAndReadCopy() {
        //Given
        Book book = new Book("Title", "Author", 1895, new ArrayList<>());
        Copies bookCopy = new Copies(book, "AVALIABLE");
        bookDao.save(book);
        copiesDao.save(bookCopy);
        //When
        Long copyId = bookCopy.getId();
        Copies saveCopy = copiesDao.findById(copyId);
        //Then
        Assertions.assertEquals("AVALIABLE", saveCopy.getStatus());
        //CleanUp
        copiesDao.deleteById(copyId);
    }

    @Test
    void testUpdateCopy() {
        //Given
        Book book = new Book("Title", "Author", 1895, new ArrayList<>());
        Copies bookCopy = new Copies(book, "AVALIABLE");
        bookDao.save(book);
        copiesDao.save(bookCopy);
        //When
        Long copyId = bookCopy.getId();
        Copies savedCopy = copiesDao.findById(copyId);
        savedCopy.setStatus("TAKEN");
        copiesDao.save(savedCopy);
        Copies updatedCopy = copiesDao.findById(copyId);
        //Then
        Assertions.assertEquals("TAKEN", updatedCopy.getStatus());
        Assertions.assertEquals( book, updatedCopy.getBook());
        Assertions.assertEquals(updatedCopy.getId(), updatedCopy.getId());
        //CleanUp
        copiesDao.deleteById(copyId);
        bookDao.deleteById(book.getId());
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
        copiesDao.deleteById(bookCopy.getId());
        copiesDao.deleteById(bookCopy2.getId());
        copiesDao.deleteById(bookCopy3.getId());

    }
    @Test
    void testBookShouldStayWhileDelatingCopy() {
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
        copiesDao.deleteById(bookCopy.getId());

        //Then
        Assertions.assertTrue(bookDao.existsById(book.getId()));
        Assertions.assertTrue(copiesDao.existsById(bookCopy2.getId()));
        Assertions.assertTrue(copiesDao.existsById(bookCopy3.getId()));
        Assertions.assertFalse(copiesDao.existsById(bookCopy.getId()));

        //CleanUP
        copiesDao.deleteById(bookCopy2.getId());
        copiesDao.deleteById(bookCopy3.getId());

    }
}
