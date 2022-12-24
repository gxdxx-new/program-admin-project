package com.gxdxx.programadmin.service;

import com.gxdxx.programadmin.entity.Member;
import com.gxdxx.programadmin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Long saveMember(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByMemberId(member.getMemberId());
    }

}
