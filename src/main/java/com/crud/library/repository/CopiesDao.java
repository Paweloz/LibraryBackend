package com.crud.library.repository;

import com.crud.library.domain.Book;
import com.crud.library.domain.Copies;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CopiesDao extends CrudRepository<Copies, Integer> {
    List<Copies> findAll();
    List<Copies> findCopiesByBook(Book book);
    Copies findById(Long id);
    void deleteById(Long id);
    Boolean existsById(Long id);
    @Query
    List<Copies> getAvaliable(@Param("AVALIABLE") String avaliable);
}
