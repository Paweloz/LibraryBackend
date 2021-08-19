package com.crud.library.service;

import com.crud.library.common.Validator;
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
    private final Validator validator;

    public List<Copies> getAllCopies() { return copiesDao.findAll();}
    public List<Copies> getCopiesByBook(Book book) {
        return copiesDao.findCopiesByBook(book);
    }
    public List<Copies> getAvaliable() {
        return copiesDao.getAvaliable("AVALIABLE");
    }
    public Copies saveCopy(final Copies copies) {
        validator.validateBookByCopy(copies);
        return copiesDao.save(copies);
    }
    public void deleteCopy(final Long copyId) {
        validator.validateCopy(copyId);
        copiesDao.deleteById(copyId);
    }

    public List<Copies> getCopiesAvaliableByBook( final Long bookId ) throws BookNotFoundException {
        validator.validateBookById(bookId);
        return copiesDao.findCopiesByBook(bookDao.findById(bookId));
    }



}
