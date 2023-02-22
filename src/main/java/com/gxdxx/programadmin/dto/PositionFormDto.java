package com.gxdxx.programadmin.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PositionFormDto {

    @NotEmpty(message = "직책명은 필수 입력 값입니다.")
    @Length(max = 20, message = "직책명은 20자 이내로 입력해주세요.")
    private String positionName;

}
