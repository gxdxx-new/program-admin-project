package com.gxdxx.programadmin.repository;

import com.gxdxx.programadmin.entity.BusinessRegistrationNumber;
import com.gxdxx.programadmin.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByRegistrationNumber(BusinessRegistrationNumber registrationNumber);

}
