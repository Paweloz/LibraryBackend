package com.crud.library.mapper;

import com.crud.library.domain.Member;
import com.crud.library.domain.dto.MemberDto;
import com.crud.library.domain.Rented;
import com.crud.library.repository.RentedDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberMapper {
    private final RentedDao rentedDao;

    public MemberDto mapMemberToMemberDto(Member member) {
        return new MemberDto(
                member.getId(),
                member.getName(),
                member.getLastName(),
                member.getAccountCreationDate(),
                member.getRentedBooks().stream()
                        .map(Rented::getId)
                        .collect(Collectors.toList())
        );
    }

    public Member mapMemberDtoToMember( MemberDto memberDto ) {
        return new Member(
                memberDto.getId(),
                memberDto.getName(),
                memberDto.getLastName(),
                memberDto.getAccountCreationDate(),
                memberDto.getRentedBooksId().stream()
                        .map(rentedDao::findById)
                        .collect(Collectors.toList())
        );
    }

    public List<MemberDto> mapToMemberDtoList( List<Member> memberList ) {
        return memberList.stream()
                .map(this::mapMemberToMemberDto)
                .collect(Collectors.toList());
    }
}
