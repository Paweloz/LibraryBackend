package com.crud.library;

import com.crud.library.domain.Book;
import com.crud.library.domain.Copies;
import com.crud.library.domain.Member;
import com.crud.library.domain.Rented;
import com.crud.library.repository.BookDao;
import com.crud.library.repository.CopiesDao;
import com.crud.library.repository.MemberDao;
import com.crud.library.repository.RentedDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
@Transactional
public class RentedTestSuite {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private CopiesDao copiesDao;
    @Autowired
    private RentedDao rentedDao;
    @Autowired
    private MemberDao memberDao;

    @Test
    void testSaveRentedAndReadRented() {
        //Given
        Book book = new Book("Title", "Author", 1895, new ArrayList<>());
        Copies bookCopy = new Copies(book, "AVALIABLE");
        Copies bookCopy2 = new Copies(book, "TAKEN");
        Copies bookCopy3 = new Copies(book, "AVALIABLE");
        Member member = new Member("Janusz", "Nowak",
                LocalDate.of(2020,10,15));
        Rented rented = new Rented(bookCopy, member,
                LocalDate.of(2021,8,15));
        book.setCopiesList(Arrays.asList(bookCopy, bookCopy2, bookCopy3));
        member.getRentedBooks().add(rented);
        memberDao.save(member);
        bookDao.save(book);
        copiesDao.save(bookCopy);
        copiesDao.save(bookCopy2);
        copiesDao.save(bookCopy3);
        rentedDao.save(rented);

        //When
        Long rentedId = rented.getId();
        Rented savedRented = rentedDao.findById(rentedId);
        //Then
        Assertions.assertEquals(bookCopy, savedRented.getCopy());
        //CleanUp
        rentedDao.deleteById(rentedId);
        bookDao.deleteById(book.getId());
        memberDao.deleteById(member.getId());
    }

    @Test
    void testUpdateRental() {
        //Given
        Book book = new Book("Title", "Author", 1895, new ArrayList<>());
        Copies bookCopy = new Copies(book, "AVALIABLE");
        Copies bookCopy2 = new Copies(book, "TAKEN");
        Copies bookCopy3 = new Copies(book, "AVALIABLE");
        Member member = new Member("Janusz", "Nowak",
                LocalDate.of(2020,10,15));
        Rented rented = new Rented(bookCopy, member,
                LocalDate.of(2021,8,15));
        book.setCopiesList(Arrays.asList(bookCopy, bookCopy2, bookCopy3));
        member.getRentedBooks().add(rented);
        memberDao.save(member);
        bookDao.save(book);
        copiesDao.save(bookCopy);
        copiesDao.save(bookCopy2);
        copiesDao.save(bookCopy3);
        rentedDao.save(rented);

        //When
        Long rentedId = rented.getId();
        Rented savedRented = rentedDao.findById(rentedId);
        savedRented.setReturned(LocalDate.of(2021,8,18));
        rentedDao.save(savedRented);
        Rented updateRented = rentedDao.findById(rentedId);
        //Then

        Assertions.assertEquals(bookCopy, updateRented.getCopy());
        Assertions.assertEquals(LocalDate.of(2021,8,18), updateRented.getReturned());
        Assertions.assertEquals(updateRented.getId(), savedRented.getId());
        //CleanUp
        rentedDao.deleteById(rentedId);
        bookDao.deleteById(book.getId());
        memberDao.deleteById(member.getId());
    }
}
