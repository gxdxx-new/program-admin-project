package com.gxdxx.programadmin.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class MemberNicknameEditFormDto {

    @Length(min = 2, max = 100, message = "닉네임은 2자 이상, 100자 이하로 입력해주세요.")
    private String changeNickname;

}
