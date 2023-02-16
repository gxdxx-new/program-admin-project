package com.gxdxx.programadmin.dto;

import com.gxdxx.programadmin.entity.BusinessRegistrationNumber;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MailConfirmDto {

    private String firstNumber;

    private String middleNumber;

    private String lastNumber;

    @NotBlank
    private String mailNumberWhat;

    @NotBlank
    private String mailNumber;

}
