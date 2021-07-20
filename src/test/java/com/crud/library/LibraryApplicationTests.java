//package com.crud.library;
//
//import com.crud.library.domain.Book;
//import com.crud.library.domain.Copies;
//import com.crud.library.domain.Member;
//import com.crud.library.domain.Rented;
//import com.crud.library.repository.BookDao;
//import com.crud.library.repository.CopiesDao;
//import com.crud.library.repository.MemberDao;
//import com.crud.library.repository.RentedDao;
//import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.transaction.Transactional;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//@SpringBootTest
//@Transactional
//class LibraryApplicationTests {
//
//    @Autowired
//    private BookDao bookDao;
//    @Autowired
//    private CopiesDao copiesDao;
//    @Autowired
//    private MemberDao memberDao;
//    @Autowired
//    private RentedDao rentedDao;
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    void testBookDaoSave() {
//        //Given
//        Book book = new Book(1,"Zbrodnia i kara", "Dostojewski", 1962);
//
//        //When
//        bookDao.save(book);
//        int bookId = book.getId();
//        Optional<Book> readBook = bookDao.findById(bookId);
//
//        //Then
//        Assertions.assertTrue(readBook.isPresent());
//        Assertions.assertNotNull(readBook.get());
//    }
//
//    @Test
//    void testCopiesDaoSave() {
//        //Given
//        Book book = new Book(1,"Zbrodnia i kara", "Dostojewski", 1962);
//        Copies copy = new Copies(book,"Avaliable");
//        Copies copy1 = new Copies(book,"Avaliable");
//        Copies copy2 = new Copies(book,"Taken");
//        Copies copy3 = new Copies(book,"Avaliable");
//        Copies copy4 = new Copies(book,"Avaliable");
//        book.getCopiesList().add(copy);
//        book.getCopiesList().add(copy1);
//        book.getCopiesList().add(copy2);
//        book.getCopiesList().add(copy3);
//        book.getCopiesList().add(copy4);
//
//
//        //When
//        bookDao.save(book);
//        copiesDao.save(copy);
//        copiesDao.save(copy1);
//        copiesDao.save(copy2);
//        copiesDao.save(copy3);
//        copiesDao.save(copy4);
//
//        int bookId = book.getId();
//        Optional<Book> readBook = bookDao.findById(bookId);
//        List<Copies> copies = copiesDao.findCopiesByBook(book);
//
//        //Then
//        Assertions.assertTrue(readBook.isPresent());
//        Assertions.assertEquals(5,copies.size());
//        bookDao.deleteAll();
//    }
//
//    @Test
//    void testMemberDaoSave() {
//        //Given
//        Member member = new Member("Jan","Kowalski", LocalDate.of(2020,6,1));
//
//        //When
//        memberDao.save(member);
//        int memberId = member.getId();
//        Optional<Member> optionalMember = memberDao.findById(memberId);
//
//        //Then
//        Assertions.assertTrue(optionalMember.isPresent());
//        Assertions.assertNotNull(optionalMember.get());
//        memberDao.deleteAll();
//
//    }
//
//    @Test
//    void testRentedDaoSave() {
//        //Given
//        Member member = new Member("Jan","Kowalski", LocalDate.of(2020,6,1));
//        Book book = new Book(1,"Zbrodnia i kara", "Dostojewski", 1962);
//        Copies copy = new Copies(book,"Avaliable");
//        Copies copy1 = new Copies(book,"Avaliable");
//        Copies copy2 = new Copies(book,"Avaliable");
//        Copies copy3 = new Copies(book,"Avaliable");
//        Copies copy4 = new Copies(book,"Avaliable");
//        Rented rented = new Rented(copy, member, LocalDate.of(2021,7,17));
//        rented.setReturned(LocalDate.of(2021,7,25));
//       // member.getRentedBooks().add(rented);
//
//        //When
//        bookDao.save(book);
//        copiesDao.save(copy);
//        copiesDao.save(copy1);
//        copiesDao.save(copy2);
//        copiesDao.save(copy3);
//        copiesDao.save(copy4);
//        memberDao.save(member);
//        rentedDao.save(rented);
//
//        int rentedId = rented.getId();
//        Optional<Rented> getRented = rentedDao.findById(rentedId);
//
//        //Then
//        Assertions.assertTrue(getRented.isPresent());
//        Assertions.assertNotNull(getRented.get());
//        Assertions.assertEquals("Taken", copy.getStatus());
//        Assertions.assertEquals(25, getRented.get().getReturned().getDayOfMonth());
//
//    }
//
//    @Test
//    void delateAll() {
//        rentedDao.deleteAll();
//        memberDao.deleteAll();
//        copiesDao.deleteAll();
//        bookDao.deleteAll();
//    }
//}
