package com.crud.library.repository;

import com.crud.library.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BookDao extends CrudRepository<Book, Integer> {
    List<Book> findAll();
    Book findById(Long id);
    List<Book> getBookByAuthor(String author);
    List<Book> getBookByTitle(String title);
    Boolean existsById(Long id);
    void deleteById(Long id);
}
