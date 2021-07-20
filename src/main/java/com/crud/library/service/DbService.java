package com.crud.library.service;

import com.crud.library.domain.*;
import com.crud.library.repository.BookDao;
import com.crud.library.repository.CopiesDao;
import com.crud.library.repository.MemberDao;
import com.crud.library.repository.RentedDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbService {

    private final BookDao bookDao;
    private final CopiesDao copiesDao;
    private final MemberDao memberDao;
    private final RentedDao rentedDao;

    //Books
    public List<Book> getBooks() {
        return bookDao.findAll();
    }
    public Book saveBook(final Book book) {
        return bookDao.save(book);
    }

    //Members
    public List<Member> getMembers() {
        return memberDao.findAll();
    }
    public Member saveMember(final Member member) {
        return memberDao.save(member);
    }

    //Copies
    public List<Copies> getAvaliable() {
        return copiesDao.getAvaliable("AVALIABLE");
    }
    public Copies saveCopy(final Copies copies) {
        return copiesDao.save(copies);
    }

    //Rental
    public Optional<Rented> getRental(final int rentId) {
        return rentedDao.findById(rentId);
    }
    public Rented saveRental(final Rented rented) {
        rented.getCopy().setStatus("TAKEN");
        return rentedDao.save(rented);
    }
    public void deleteRental(final int rentId) {
        rentedDao.deleteById(rentId);
    }
}
