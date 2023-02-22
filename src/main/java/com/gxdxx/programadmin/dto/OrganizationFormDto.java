package com.gxdxx.programadmin.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class OrganizationFormDto {

    private Long id;

    @NotEmpty(message = "부서명은 필수 입력 값입니다.")
    @Length(min = 2, max = 20, message = "부서명은 2자 이상, 20자 이하로 입력해주세요.")
    private String organizationName;

}
