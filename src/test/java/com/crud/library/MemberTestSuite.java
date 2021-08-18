package com.crud.library;

import com.crud.library.domain.*;
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
public class MemberTestSuite {
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private CopiesDao copiesDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private RentedDao rentedDao;

    @Test
    void testSaveMemberAndReadMember() {
        //Given
        Member member = new Member("Janusz", "Nowak",
                LocalDate.of(2020,10,15));
        memberDao.save(member);
        //When
        Long memberId = member.getId();
        Member savedMember = memberDao.findById(memberId);
        //Then
        Assertions.assertEquals("Nowak", savedMember.getLastName());
        //CleanUp
        memberDao.deleteById(memberId);
    }

    @Test
    void testUpdateMember() {
        //Given
        Member member = new Member("Janusz", "Nowak",
                LocalDate.of(2020,10,15));
        memberDao.save(member);
        //When
        Long memberId = member.getId();
        Member savedMember = memberDao.findById(memberId);
        savedMember.setName("Piotr");
        memberDao.save(savedMember);
        Member updatedMember = memberDao.findById(memberId);
        //Then
        Assertions.assertEquals("Piotr", updatedMember.getName());
        Assertions.assertEquals("Nowak", updatedMember.getLastName());
        Assertions.assertEquals(updatedMember.getId(), savedMember.getId());
        //CleanUp
        memberDao.deleteById(memberId);
    }

    @Test
    void testMemberRentedRelation() {
        //Given
        Book book = new Book("Title", "Author", 1895, new ArrayList<>());
        Copies bookCopy = new Copies(book, "AVALIABLE");
        Copies bookCopy2 = new Copies(book, "TAKEN");
        Copies bookCopy3 = new Copies(book, "AVALIABLE");
        Member member = new Member("Janusz", "Nowak",
                LocalDate.of(2020,10,15));
        Rented rented = new Rented(bookCopy, member,
                LocalDate.of(2021,8,15));
        Rented rented2 = new Rented(bookCopy2, member,
                LocalDate.of(2019,6,10));
        book.setCopiesList(Arrays.asList(bookCopy, bookCopy2, bookCopy3));
        member.setRentedBooks(Arrays.asList(rented, rented2));

        memberDao.save(member);
        bookDao.save(book);
        copiesDao.save(bookCopy);
        copiesDao.save(bookCopy2);
        copiesDao.save(bookCopy3);
        rentedDao.save(rented);
        rentedDao.save(rented2);

        //When
        Long memberId = member.getId();
        Member savedMember = memberDao.findById(memberId);

        //Then
        Assertions.assertEquals(2, savedMember.getRentedBooks().size());

        //CleaUp
        rentedDao.deleteById(rented.getId());
        rentedDao.deleteById(rented2.getId());
        bookDao.deleteById(book.getId());
        memberDao.deleteById(memberId);
    }
}
