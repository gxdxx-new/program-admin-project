package com.gxdxx.programadmin.service;

import com.gxdxx.programadmin.dto.*;
import com.gxdxx.programadmin.entity.*;
import com.gxdxx.programadmin.exception.*;
import com.gxdxx.programadmin.repository.CompanyRepository;
import com.gxdxx.programadmin.repository.MemberRepository;
import com.gxdxx.programadmin.repository.OrganizationRepository;
import com.gxdxx.programadmin.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    private final PositionRepository positionRepository;

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

        List<OrganizationListDto> organizationListDtos;

        if (searchValue == null || searchValue.isBlank()) {
            organizationListDtos = organizationRepository.findAll().stream().map(OrganizationListDto::from).toList();
            return getPositions(organizationListDtos);
        }

        organizationListDtos = organizationRepository.findByOrganizationNameContains(searchValue).stream().map(OrganizationListDto::from).toList();
        return getPositions(organizationListDtos);
    }

    public List<OrganizationListDto> getPositions(List<OrganizationListDto> organizationListDtos) {
        for (OrganizationListDto organizationListDto : organizationListDtos) {
            List<PositionListDto> positionListDtos = positionRepository.findByOrganization_Id(organizationListDto.getId()).stream().map(PositionListDto::from).toList();
            positionListDtos = getMembers(positionListDtos);
            organizationListDto.setPositionListDtos(positionListDtos);
        }
        return organizationListDtos;
    }

    public List<PositionListDto> getMembers(List<PositionListDto> positionListDtos) {
        for (PositionListDto positionListDto : positionListDtos) {
            List<PositionMemberListDto> positionMemberListDtos = memberRepository.findByPosition_Id(positionListDto.getId()).stream().map(PositionMemberListDto::from).toList();
            positionListDto.setPositionMemberListDtos(positionMemberListDtos);
        }
        return positionListDtos;
    }

    public Long saveOrganization(Long companyId, String organizationName) {
        Company company = companyRepository.findById(companyId).orElseThrow(CompanyNotFoundException::new);
        Organization organization = Organization.of(organizationName, company);
        return organizationRepository.save(organization).getId();

    }

    public Long savePosition(Long companyId, Long organizationId, String positionName) {
        Organization organization = organizationRepository.findById(organizationId).orElseThrow(OrganizationAjaxNotFoundException::new);
        Position position = Position.of(positionName, organization);
        return positionRepository.save(position).getId();
    }

    public List<MemberListDto> manageMember(Long companyId) {
        List<Member> allMembers = memberRepository.findByCompany_Id(companyId);
        List<MemberListDto> newMembers = new ArrayList<>();
        for (Member member : allMembers) {
            if (member.getOrganization() == null && member.getPosition() == null) {
                newMembers.add(MemberListDto.from(member));
            }
        }
        return newMembers;
    }

    public void grantMember(Long companyId, Long organizationId, Long positionId, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
        Organization organization = organizationRepository.findById(organizationId).orElseThrow(OrganizationNotFoundException::new);
        member.applyOrganization(organization);
        Position position = positionRepository.findById(positionId).orElseThrow(PositionNotFoundException::new);
        member.applyPosition(position);
    }

}
