package com.gxdxx.programadmin.service;

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

}
