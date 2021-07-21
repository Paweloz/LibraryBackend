package com.crud.library.repository;

import com.crud.library.domain.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface MemberDao extends CrudRepository<Member, Integer> {
    List<Member> findAll();
    List<Member> getMemberByLastName(String lastname);
}
