package com.gxdxx.programadmin.service;

import com.gxdxx.programadmin.dto.AdminListDto;
import com.gxdxx.programadmin.dto.MemberListDto;
import com.gxdxx.programadmin.entity.Member;
import com.gxdxx.programadmin.entity.Role;
import com.gxdxx.programadmin.exception.MemberNameAlreadyExistsException;
import com.gxdxx.programadmin.exception.SuperAdminNotFoundException;
import com.gxdxx.programadmin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class SuperAdminService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Long saveAdmin(String superAdminName, String memberName, String password, String email, String nickname) {
        Member superAdminMember = memberRepository.findByMemberName(superAdminName);
        if (superAdminMember.getRole() != Role.SUPERADMIN) {
            throw new SuperAdminNotFoundException();
        }
        Member member = Member.of(memberName, passwordEncoder.encode(password), email, nickname, Role.ADMIN);
        validateDuplicateMember(member);
        return memberRepository.save(member).getId();
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByMemberName(member.getMemberName());
        if (findMember != null) {
            throw new MemberNameAlreadyExistsException(findMember.getMemberName());
        }
    }

    public Page<AdminListDto> searchAdmins(String superAdminName, Pageable pageable) {
        Member superAdminMember = memberRepository.findByMemberName(superAdminName);
        if (superAdminMember.getRole() != Role.SUPERADMIN) {
            throw new SuperAdminNotFoundException();
        }

        return memberRepository.findByRole(Role.ADMIN, pageable).map(AdminListDto::from);
    }

    public Page<MemberListDto> searchMembers(String superAdminName, Pageable pageable) {
        Member superAdminMember = memberRepository.findByMemberName(superAdminName);
        if (superAdminMember.getRole() != Role.SUPERADMIN) {
            throw new SuperAdminNotFoundException();
        }

        return memberRepository.findByRole(Role.USER, pageable).map(MemberListDto::from);
    }

}
