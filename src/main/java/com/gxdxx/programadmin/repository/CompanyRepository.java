package com.gxdxx.programadmin.repository;

import com.gxdxx.programadmin.entity.BusinessRegistrationNumber;
import com.gxdxx.programadmin.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByRegistrationNumber(BusinessRegistrationNumber registrationNumber);

    Page<Company> findByCompanyNameContains(String companyName, Pageable pageable);

    Page<Company> findByChiefNameContains(String chiefName, Pageable pageable);

}
