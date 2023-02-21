package com.gxdxx.programadmin.dto;

import com.gxdxx.programadmin.entity.Company;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyDetailDto {

    private Long id;

    private String firstNumber;

    private String middleNumber;

    private String lastNumber;

    private String companyName;

    private String chiefName;

    private String email;

    private String createdAt;

    public static CompanyDetailDto from(Company company) {
        return new CompanyDetailDto(
                company.getId(),
                company.getRegistrationNumber().getFirstNumber(),
                company.getRegistrationNumber().getMiddleNumber(),
                company.getRegistrationNumber().getLastNumber(),
                company.getCompanyName(),
                company.getChiefName(),
                company.getEmail(),
                company.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss"))
        );
    }

}
