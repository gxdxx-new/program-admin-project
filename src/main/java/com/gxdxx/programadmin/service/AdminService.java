package com.gxdxx.programadmin.service;

import com.gxdxx.programadmin.dto.*;
import com.gxdxx.programadmin.entity.*;
import com.gxdxx.programadmin.exception.AdminNotFoundException;
import com.gxdxx.programadmin.exception.CompanyNotFoundException;
import com.gxdxx.programadmin.repository.CompanyRepository;
import com.gxdxx.programadmin.repository.MemberRepository;
import com.gxdxx.programadmin.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class AdminService {

    private final MemberRepository memberRepository;
    private final CompanyRepository companyRepository;
    private final OrganizationRepository organizationRepository;

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

    public List<OrganizationListDto> getOrganization(OrganizationSearchType searchType, String searchValue) {

        if (searchValue == null || searchValue.isBlank()) {
            return organizationRepository.findAll().stream().map(OrganizationListDto::from).toList();
        }

        return organizationRepository.findByOrganizationNameContains(searchValue).stream().map(OrganizationListDto::from).toList();
    }

    public Long saveOrganization(Long companyId, String organizationName) {
        Company company = companyRepository.findById(companyId).orElseThrow(CompanyNotFoundException::new);
        Organization organization = Organization.of(organizationName, company);
        return organizationRepository.save(organization).getId();

    }
}
