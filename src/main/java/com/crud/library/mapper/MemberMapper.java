package com.crud.library.mapper;

import com.crud.library.domain.Member;
import com.crud.library.domain.MemberDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberMapper {

    public MemberDto mapMemberToMemberDto(Member member) {
        return new MemberDto(
                member.getId(),
                member.getName(),
                member.getLastName(),
                member.getAccountCreationDate()
        );
    }

    public Member mapMemberDtoToMember( MemberDto memberDto ) {
        return new Member(
                memberDto.getId(),
                memberDto.getName(),
                memberDto.getLastName(),
                memberDto.getAccountCreationDate()
        );
    }

    public List<MemberDto> mapToMemberDtoList( List<Member> memberList ) {
        return memberList.stream()
                .map(this::mapMemberToMemberDto)
                .collect(Collectors.toList());
    }
}
