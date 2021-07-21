package com.crud.library.service;

import com.crud.library.domain.Member;
import com.crud.library.repository.MemberDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberDbService {
    private final MemberDao memberDao;

    public List<Member> getMembers() {
        return memberDao.findAll();
    }
    public List<Member> getMemberByLastName(final String lastname) {
        return memberDao.getMemberByLastName(lastname);
    }
    public Member saveMember(final Member member) {
        return memberDao.save(member);
    }
    public void deleteMemeber(final int memberId) {
        memberDao.deleteById(memberId);
    }
}
