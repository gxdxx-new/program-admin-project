package com.gxdxx.programadmin.dto;

import com.gxdxx.programadmin.entity.Company;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyLinkDto {

    private String firstNumber;

    private String middleNumber;

    private String lastNumber;

    private String companyName;

    private String email;

    @NotBlank
    private String mailNumberWhat;

    @NotBlank
    private String mailNumber;

    public static CompanyLinkDto from(Company company) {
        return new CompanyLinkDto(
                company.getRegistrationNumber().getFirstNumber(),
                company.getRegistrationNumber().getMiddleNumber(),
                company.getRegistrationNumber().getLastNumber(),
                company.getCompanyName(),
                company.getEmail(),
                "",
                ""
        );
    }

}
