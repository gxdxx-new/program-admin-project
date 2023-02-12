package com.gxdxx.programadmin.service;

import com.gxdxx.programadmin.dto.*;
import com.gxdxx.programadmin.entity.Member;
import com.gxdxx.programadmin.entity.Post;
import com.gxdxx.programadmin.entity.Role;
import com.gxdxx.programadmin.exception.MemberNameAlreadyExistsException;
import com.gxdxx.programadmin.exception.MemberNotFoundException;
import com.gxdxx.programadmin.exception.PasswordNotMatchException;
import com.gxdxx.programadmin.exception.PostNotFoundException;
import com.gxdxx.programadmin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Long saveMember(String memberName, String password, String email, String nickname) {
        Member member = Member.of(memberName, passwordEncoder.encode(password), email, nickname);
        validateDuplicateMember(member);
        return memberRepository.save(member).getId();
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByMemberName(member.getMemberName());
        if (findMember != null) {
            throw new MemberNameAlreadyExistsException(findMember.getMemberName());
        }
    }

    public MemberProfileDto getProfile(String memberName) {
        Member member = memberRepository.findByMemberName(memberName);
        if (member == null) {
            throw new MemberNotFoundException();
        }
        MemberProfileDto memberProfileDto = MemberProfileDto.builder()
                    .memberName(member.getMemberName())
                    .email(member.getEmail())
                    .nickname(member.getNickname())
                    .createdAt(member.getCreatedAt())
                    .company(member.getCompany())
                    .build();

        return memberProfileDto;
    }

    public MemberEditFormDto getMemberForm(String memberName) {
        Member member = memberRepository.findByMemberName(memberName);
        if (member == null) {
            throw new MemberNotFoundException();
        }
        MemberEditFormDto memberEditFormDto = MemberEditFormDto.builder()
                .memberName(member.getMemberName())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .company(member.getCompany())
                .build();

        return memberEditFormDto;
    }

    public void changePassword(String memberName, String currentPassword, String changePassword) {
        Member member = memberRepository.findByMemberName(memberName);
        if (member == null) {
            throw new MemberNotFoundException();
        }

        validatePassword(currentPassword, member.getPassword());
        member.changePassword(passwordEncoder.encode(changePassword));
    }

    private void validatePassword(String checkPassword, String savedPassword) {
        if (!passwordEncoder.matches(checkPassword, savedPassword)) {
            throw new PasswordNotMatchException();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberName(username);
        if (member == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        }
        return new User(member.getMemberName(), member.getPassword(), authorities);
    }

}
