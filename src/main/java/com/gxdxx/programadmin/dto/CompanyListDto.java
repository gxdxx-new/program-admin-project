package com.gxdxx.programadmin.dto;

import com.gxdxx.programadmin.entity.BusinessRegistrationNumber;
import com.gxdxx.programadmin.entity.Company;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyListDto {

    private String firstNumber;

    private String middleNumber;

    private String lastNumber;

    private String companyName;

    private String chiefName;

    private String email;

    public static CompanyListDto from(Company company) {
        return new CompanyListDto(
                company.getRegistrationNumber().getFirstNumber(),
                company.getRegistrationNumber().getMiddleNumber(),
                company.getRegistrationNumber().getLastNumber(),
                company.getCompanyName(),
                company.getChiefName(),
                company.getEmail()
        );
    }

}
