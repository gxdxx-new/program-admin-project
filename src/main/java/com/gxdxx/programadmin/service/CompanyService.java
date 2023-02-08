package com.gxdxx.programadmin.service;

import com.gxdxx.programadmin.dto.CompanyFormDto;
import com.gxdxx.programadmin.dto.PostFormDto;
import com.gxdxx.programadmin.entity.*;
import com.gxdxx.programadmin.exception.MemberNameAlreadyExistsException;
import com.gxdxx.programadmin.exception.RegistrationNumberAlreadyExistsException;
import com.gxdxx.programadmin.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

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

}
