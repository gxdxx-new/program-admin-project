package com.gxdxx.programadmin.service;

import com.gxdxx.programadmin.dto.MemberFormDto;
import com.gxdxx.programadmin.entity.Member;
import com.gxdxx.programadmin.exception.MemberNameAlreadyExistsException;
import com.gxdxx.programadmin.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원가입_성공")
    public void saveMemberSuccess() {
        //given
        MemberFormDto memberFormDto = createMemberFormDto();
        Member member = createMember(memberFormDto);

        Long fakeMemberId = 1L;
        ReflectionTestUtils.setField(member, "id", fakeMemberId);
        ReflectionTestUtils.setField(memberService, "passwordEncoder", passwordEncoder);

        //mocking
        given(passwordEncoder.encode(anyString())).willReturn("encodedPassword");
        ReflectionTestUtils.setField(member, "password", passwordEncoder.encode(member.getPassword()));
        given(memberRepository.save(any())).willReturn(member);
        given(memberRepository.findById(fakeMemberId)).willReturn(Optional.ofNullable(member));

        //when
        Long newMemberId = memberService.saveMember(memberFormDto.getMemberName(), memberFormDto.getPassword(), memberFormDto.getEmail(), memberFormDto.getNickname());

        //then
        Member findMember = memberRepository.findById(newMemberId).get();
        assertEquals(member.getId(), findMember.getId());
        assertEquals(member.getMemberName(), findMember.getMemberName());
        assertEquals(member.getEmail(), findMember.getEmail());
        assertEquals(member.getNickname(), findMember.getNickname());
        assertEquals(member.getPassword(), findMember.getPassword());
    }

    @Test
    @DisplayName("회원가입_실패")
    public void saveMemberFail() {
        //given
        MemberFormDto memberFormDto = createMemberFormDto();
        Member member = createMember(memberFormDto);
        Member alreadyExistsMember = createMember(memberFormDto);

        ReflectionTestUtils.setField(memberService, "passwordEncoder", passwordEncoder);

        //mocking
        given(passwordEncoder.encode(anyString())).willReturn("encodedPassword");
        ReflectionTestUtils.setField(member, "password", passwordEncoder.encode(member.getPassword()));
        given(memberRepository.findByMemberName(member.getMemberName())).willReturn(alreadyExistsMember);

        //then
        Assertions.assertThrows(MemberNameAlreadyExistsException.class, () -> {
            memberService.saveMember(memberFormDto.getMemberName(), memberFormDto.getPassword(), memberFormDto.getEmail(), memberFormDto.getNickname());
        });
    }


    private Member createMember(MemberFormDto memberFormDto) {
        return Member.of(memberFormDto.getMemberName(), memberFormDto.getPassword(), memberFormDto.getEmail(), memberFormDto.getNickname());
    }

    private MemberFormDto createMemberFormDto() {
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setMemberName("nkd0310");
        memberFormDto.setPassword("123123123");
        memberFormDto.setEmail("nkd0310@naver.com");
        memberFormDto.setNickname("dondon");
        return memberFormDto;
    }

}