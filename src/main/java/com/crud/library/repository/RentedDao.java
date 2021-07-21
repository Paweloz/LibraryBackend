package com.crud.library.repository;

import com.crud.library.domain.Rented;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RentedDao extends CrudRepository<Rented, Integer> {
    List<Rented> findAll();
    List<Rented> findByMember_LastName(String lastname);
}
