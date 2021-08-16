package com.crud.library.repository;

import com.crud.library.domain.Rented;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface RentedDao extends CrudRepository<Rented, Integer> {
    List<Rented> findAll();
    Optional<Rented> findById(Long id);
    void deleteById(Long id);
    List<Rented> findByMember_LastName(String lastname);
}
