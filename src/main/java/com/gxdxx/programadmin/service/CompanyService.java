package com.gxdxx.programadmin.service;

import com.gxdxx.programadmin.dto.*;
import com.gxdxx.programadmin.entity.*;
import com.gxdxx.programadmin.exception.MemberNameAlreadyExistsException;
import com.gxdxx.programadmin.exception.RegistrationNumberAlreadyExistsException;
import com.gxdxx.programadmin.exception.RegistrationNumberNotFoundException;
import com.gxdxx.programadmin.repository.CompanyRepository;
import com.gxdxx.programadmin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final MemberRepository memberRepository;

    public Long saveCompany(CompanyFormDto companyFormDto) {
        BusinessRegistrationNumber registrationNumber = BusinessRegistrationNumber.of(companyFormDto.getFirstNumber(),
                                                                                    companyFormDto.getMiddleNumber(),
                                                                                    companyFormDto.getLastNumber());
        TelephoneNumber telephoneNumber = TelephoneNumber.of(companyFormDto.getFirstTelNumber(),
                                                            companyFormDto.getMiddleTelNumber(),
                                                            companyFormDto.getLastTelNumber());
        Company company = Company.of(registrationNumber,
                                     companyFormDto.getChiefName(),
                                     companyFormDto.getCompanyName(),
                                     companyFormDto.getEmail(),
                                     telephoneNumber);
        validateDuplicateCompany(company);
        return companyRepository.save(company).getId();
    }

    private void validateDuplicateCompany(Company company) {
        Company findCompany = companyRepository.findByRegistrationNumber(company.getRegistrationNumber());
        if (findCompany != null) {
            throw new RegistrationNumberAlreadyExistsException();
        }
    }

    public Page<CompanyListDto> searchCompanies(CompanySearchType searchType, String searchValue, Pageable pageable) {

        if (searchValue == null || searchValue.isBlank()) {
            return companyRepository.findAll(pageable).map(CompanyListDto::from);
        }

        return switch (searchType) {
            case COMPANYNAME -> companyRepository.findByCompanyNameContains(searchValue, pageable).map(CompanyListDto::from);
            case CHIEFNAME -> companyRepository.findByChiefNameContains(searchValue, pageable).map(CompanyListDto::from);
        };

    }

}
