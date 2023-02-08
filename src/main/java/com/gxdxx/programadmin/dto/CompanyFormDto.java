package com.gxdxx.programadmin.dto;

import com.gxdxx.programadmin.entity.BusinessRegistrationNumber;
import com.gxdxx.programadmin.entity.TelephoneNumber;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CompanyFormDto {

    @Length(min = 3, max = 3, message = "지역코드 3자를 입력해주세요.")
    private String firstNumber;
    @Length(min = 2, max = 2, message = "개인/법인 성격코드 2자를 입력해주세요.")
    private String middleNumber;
    @Length(min = 5, max = 5, message = "일련번호와 검증번호 5자를 입력해주세요.")
    private String lastNumber;

    @NotEmpty(message = "대표명은 필수 입력 값입니다.")
    @Length(min = 2, max = 10, message = "대표명은 2자 이상, 10자 이하로 입력해주세요.")
    private String chiefName;

    @NotEmpty(message = "회사명은 필수 입력 값입니다.")
    @Length(min = 2, max = 50, message = "회사명은 2자 이상, 50자 이하로 입력해주세요.")
    private String companyName;

    @Email(message = "이메일 형식으로 입력해주세요.")
    @NotBlank
    private String email;

    @Length(min = 2, max = 3, message = "전화번호를 확인해주세요.")
    private String firstTelNumber;
    @Length(min = 3, max = 4, message = "전화번호를 확인해주세요.")
    private String middleTelNumber;
    @Length(min = 4, max = 4, message = "전화번호를 확인해주세요.")
    private String lastTelNumber;

}
