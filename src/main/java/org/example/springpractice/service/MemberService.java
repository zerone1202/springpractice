package org.example.springpractice.service;

import lombok.RequiredArgsConstructor;
import org.example.springpractice.dto.MemberRequestDto;
import org.example.springpractice.dto.MemberResponseDto;
import org.example.springpractice.entity.Member;
import org.example.springpractice.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto createMember(MemberRequestDto memberRequestDto) {
        Member member = new Member(memberRequestDto.getName());
        Member savedMember = memberRepository.save(member);

        return new MemberResponseDto(
                savedMember.getId(),
                savedMember.getName()
        );
    }

    @Transactional(readOnly = true)
    public MemberResponseDto getMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("그런 id의 멤버는 없습니다.")
        );

        return new MemberResponseDto(
                member.getId(),
                member.getName()
        );
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> getMembers() {
        List<Member> members = memberRepository.findAll();

        List<MemberResponseDto> dtoList = new ArrayList<>();

        for (Member member : members) {
        MemberResponseDto memberResponseDto = new MemberResponseDto(
                member.getId(),
                member.getName()
        );
        dtoList.add(memberResponseDto);
        }
        return dtoList;
    }

    @Transactional
    public MemberResponseDto updateMember(Long id, MemberRequestDto memberRequestDto) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("그런 id의 멤버는 없습니다.")
        );
        member.updateName(memberRequestDto.getName());

        return new MemberResponseDto(member.getId(), member.getName());
    }

    @Transactional
    public void deleteMember(Long id) {
        memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("그런 id의 멤버는 없습니다.")
        );
        memberRepository.deleteById(id);
    }
}
