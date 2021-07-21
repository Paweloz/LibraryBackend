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
@RequestMapping(path = "v1/member")
public class MemberController {
    private final MemberDbService memberDbService;
    private final MemberMapper memberMapper;

    @PostMapping(value = "createMember")
    public void createMember(@RequestBody MemberDto memberDto) {
        Member member = memberMapper.mapMemberDtoToMember(memberDto);
        memberDbService.saveMember(member);
    }

    @GetMapping(value = "getMembers")
    public List<MemberDto> getMembers() {
        List<Member> memberList = memberDbService.getMembers();
        return memberMapper.mapToMemberDtoList(memberList);
    }
}
