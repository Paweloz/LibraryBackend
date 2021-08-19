package com.crud.library.common;

import com.crud.library.domain.Copies;
import com.crud.library.exception.BookNotFoundException;
import com.crud.library.exception.CopyNotFoundException;
import com.crud.library.exception.MemberNotFoundException;
import com.crud.library.repository.BookDao;
import com.crud.library.repository.CopiesDao;
import com.crud.library.repository.MemberDao;
import com.crud.library.repository.RentedDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class Validator {
    private final BookDao bookDao;
    private final CopiesDao copiesDao;
    private final MemberDao memberDao;
    private final RentedDao rentedDao;

    public void valdiateMemberById(final Long memberId) throws MemberNotFoundException {
        if(!memberDao.existsById(memberId)) {
            throw new MemberNotFoundException("There is no member with ID :" + memberId);
        }
    }

    public void valdiateMemberByLastName(final String lastname) throws MemberNotFoundException {
        if(memberDao.getMemberByLastName(lastname).isEmpty()) {
            throw new MemberNotFoundException("There is no member with lastname :" + lastname);
        }
    }

    public void validateBookByCopy(final Copies copies) throws BookNotFoundException {
        Long bookId = copies.getBook().getId();
        if(!bookDao.existsById(bookId) ||
                !bookDao.findById(bookId).equals(copies.getBook())){
            throw new BookNotFoundException("There is no book with ID :" + bookId +
                    " or books detalis are different");
        }
    }
    public void validateBookById(final Long bookId) throws BookNotFoundException {
        if(!bookDao.existsById(bookId)){
            throw new BookNotFoundException("There is no book with ID :" + bookId +
                    " or books detalis are different");
        }
    }

    public void validateBookByAuthorOrTitle( final String bookDetalis ) throws BookNotFoundException {
        if( bookDao.getBookByAuthor(bookDetalis).isEmpty() &&
                bookDao.getBookByTitle(bookDetalis).isEmpty()) {
            throw new BookNotFoundException("There is no book with author/title : " +
                    bookDetalis);
        }
    }

    public void validateCopy( final Long copyId ) throws CopyNotFoundException {
        if (!copiesDao.existsById(copyId)) {
            throw new CopyNotFoundException("There is no copy with ID : " + copyId);
        }
    }
}
