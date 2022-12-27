package com.gxdxx.programadmin.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberFormDto {

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 8, max = 20, message = "아이디는 8자 이상, 20자 이하로 입력해주세요.")
    private String memberName;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
    private String password;

    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @Length(min = 2, max = 100, message = "닉네임은 2자 이상, 100자 이하로 입력해주세요.")
    private String nickname;

}
