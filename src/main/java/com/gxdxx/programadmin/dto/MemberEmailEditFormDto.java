package com.gxdxx.programadmin.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberEmailEditFormDto {

    @NotEmpty
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String changeEmail;

}
