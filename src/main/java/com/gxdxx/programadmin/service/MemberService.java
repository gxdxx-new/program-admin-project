package com.gxdxx.programadmin.service;

import com.gxdxx.programadmin.dto.MemberFormDto;
import com.gxdxx.programadmin.entity.Member;
import com.gxdxx.programadmin.entity.Role;
import com.gxdxx.programadmin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Long saveMember(MemberFormDto memberFormDto) {
        Member member = Member.of(memberFormDto.getMemberName(), memberFormDto.getPassword(), memberFormDto.getEmail(), memberFormDto.getNickname());
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByMemberName(member.getMemberName());
        if (findMember == null) {
            //예외처리 해야함
        }
    }

}
