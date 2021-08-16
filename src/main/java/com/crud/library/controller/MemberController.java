package com.crud.library.controller;

import com.crud.library.domain.Member;
import com.crud.library.domain.MemberDto;
import com.crud.library.mapper.MemberMapper;
import com.crud.library.service.MemberDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "v1/library/member")
public class MemberController {
    private final MemberDbService memberDbService;
    private final MemberMapper memberMapper;

    @PostMapping(value = "createMember")
    public MemberDto createMember(@RequestBody MemberDto memberDto) {
        Member member = memberMapper.mapMemberDtoToMember(memberDto);
        Member savedMember = memberDbService.saveMember(member);
        return memberMapper.mapMemberToMemberDto(savedMember);

    }

    @PutMapping(value = "updateMember")
    public MemberDto updateMember(@RequestBody MemberDto memberDto) {
        Member member = memberMapper.mapMemberDtoToMember(memberDto);
        Member savedMember = memberDbService.saveMember(member);
        return memberMapper.mapMemberToMemberDto(savedMember);
    }

    @GetMapping(value = "getMembers")
    public List<MemberDto> getMembers() {
        List<Member> memberList = memberDbService.getMembers();
        return memberMapper.mapToMemberDtoList(memberList);
    }

    @GetMapping(value = "getMemberByLastname")
    public List<MemberDto> getMemberByLastname(@RequestParam String lastname) {
        List<Member> memberList = memberDbService.getMemberByLastName(lastname);
        return memberMapper.mapToMemberDtoList(memberList);
    }

    @DeleteMapping(value = "deleteMember")
    public void deleteMember(@RequestParam Long memberId) {
        memberDbService.deleteMemeber(memberId);
    }
}
