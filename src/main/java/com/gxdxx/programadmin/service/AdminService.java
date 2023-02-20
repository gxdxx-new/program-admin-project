package com.gxdxx.programadmin.service;

import com.gxdxx.programadmin.dto.AdminListDto;
import com.gxdxx.programadmin.entity.Member;
import com.gxdxx.programadmin.entity.Role;
import com.gxdxx.programadmin.exception.AdminNotFoundException;
import com.gxdxx.programadmin.exception.SuperAdminNotFoundException;
import com.gxdxx.programadmin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class AdminService {

    private final MemberRepository memberRepository;

    public Page<AdminListDto> searchAdmins(String adminName, Pageable pageable) {
        Member adminMember = memberRepository.findByMemberName(adminName);
        if (adminMember.getRole() != Role.ADMIN) {
            throw new AdminNotFoundException();
        }

        return memberRepository.findByRole(Role.ADMIN, pageable).map(AdminListDto::from);
    }

}
