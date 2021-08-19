package com.crud.library.service;

import com.crud.library.domain.Book;
import com.crud.library.domain.Copies;
import com.crud.library.exception.BookNotFoundException;
import com.crud.library.exception.CopyNotFoundException;
import com.crud.library.repository.BookDao;
import com.crud.library.repository.CopiesDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CopiesDbService {
    private final CopiesDao copiesDao;
    private final BookDao bookDao;

    public List<Copies> getAllCopies() { return copiesDao.findAll();}
    public List<Copies> getCopiesByBook(Book book) {
        return copiesDao.findCopiesByBook(book);
    }
    public List<Copies> getAvaliable() {
        return copiesDao.getAvaliable("AVALIABLE");
    }
    public Copies saveCopy(final Copies copies) {
        validateBook(copies);
        return copiesDao.save(copies);
    }
    public void deleteCopy(final Long copyId) {
        validateCopy(copyId);
        copiesDao.deleteById(copyId);
    }

    public List<Copies> getCopiesAvaliableByBook( final Long bookId ) throws BookNotFoundException {
        validateBook(bookId);
        return copiesDao.findCopiesByBook(bookDao.findById(bookId));
    }


    private void validateBook(final Copies copies) throws BookNotFoundException {
        Long bookId = copies.getBook().getId();
        if(!bookDao.existsById(bookId) ||
                !bookDao.findById(bookId).equals(copies.getBook())){
            throw new BookNotFoundException("There is no book with ID :"+bookId+
                    " or books detalis are different");
        }
    }
    private void validateBook(final Long bookId) throws BookNotFoundException {
        if(!bookDao.existsById(bookId)){
            throw new BookNotFoundException("There is no book with ID :"+bookId+
                    " or books detalis are different");
        }
    }

    private void validateCopy( final Long copyId ) throws CopyNotFoundException {
        if (!copiesDao.existsById(copyId)) {
            throw new CopyNotFoundException("There is no copy with ID : "+copyId);
        }
    }
}
