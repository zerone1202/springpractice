package org.example.springpractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springpractice.dto.MemberRequestDto;
import org.example.springpractice.dto.MemberResponseDto;
import org.example.springpractice.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public MemberResponseDto createMember(
            @RequestBody MemberRequestDto memberRequestDto
    ) {
        return memberService.createMember(memberRequestDto);
    }

    @GetMapping("/members")
    public List<MemberResponseDto> getMembers() {
        return memberService.getMembers();
    }

    @GetMapping("/members/{memberId}")
    public MemberResponseDto getMember(
            @PathVariable("memberId") Long memberId
    ) {
        return memberService.getMember(memberId);
    }

    @PutMapping("/members/{memberId}")
    public MemberResponseDto updateMember(
            @PathVariable Long memberId,
            @RequestBody MemberRequestDto memberRequestDto
    ) {
        return memberService.updateMember(memberId, memberRequestDto);
    }

    @DeleteMapping("/members/{memberId}")
    public void deleteMember(
            @PathVariable Long memberId
    ) {
        memberService.deleteMember(memberId);
    }
}
