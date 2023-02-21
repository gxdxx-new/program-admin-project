package com.gxdxx.programadmin.service;

import com.gxdxx.programadmin.dto.AdminListDto;
import com.gxdxx.programadmin.dto.CompanyDetailDto;
import com.gxdxx.programadmin.dto.CompanyListDto;
import com.gxdxx.programadmin.dto.MemberListDto;
import com.gxdxx.programadmin.entity.Company;
import com.gxdxx.programadmin.entity.Member;
import com.gxdxx.programadmin.entity.Role;
import com.gxdxx.programadmin.exception.AdminNotFoundException;
import com.gxdxx.programadmin.exception.CompanyNotFoundException;
import com.gxdxx.programadmin.repository.CompanyRepository;
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
    private final CompanyRepository companyRepository;

    public Page<AdminListDto> searchAdmins(String adminName, Pageable pageable) {
        Member adminMember = memberRepository.findByMemberName(adminName);
        if (adminMember.getRole() != Role.ADMIN) {
            throw new AdminNotFoundException();
        }

        return memberRepository.findByRole(Role.ADMIN, pageable).map(AdminListDto::from);
    }

    public Page<MemberListDto> searchMembers(String adminName, Pageable pageable) {
        Member adminMember = memberRepository.findByMemberName(adminName);
        if (adminMember.getRole() != Role.ADMIN) {
            throw new AdminNotFoundException();
        }

        return memberRepository.findByRole(Role.USER, pageable).map(MemberListDto::from);
    }

    public Page<CompanyListDto> searchCompanies(String adminName, Pageable pageable) {
        Member adminMember = memberRepository.findByMemberName(adminName);
        if (adminMember.getRole() != Role.ADMIN) {
            throw new AdminNotFoundException();
        }

        return companyRepository.findAll(pageable).map(CompanyListDto::from);
    }

    public CompanyDetailDto getCompanyDetail(Long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(CompanyNotFoundException::new);
        return CompanyDetailDto.from(company);
    }

}
