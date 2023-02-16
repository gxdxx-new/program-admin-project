package com.gxdxx.programadmin.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class BusinessRegistrationNumber {

    private String firstNumber;

    private String middleNumber;

    private String lastNumber;

    private BusinessRegistrationNumber(String firstNumber, String middleNumber, String lastNumber) {
        this.firstNumber = firstNumber;
        this.middleNumber = middleNumber;
        this.lastNumber = lastNumber;
    }

    public static BusinessRegistrationNumber of(String firstNumber, String middleNumber, String lastNumber) {
        return new BusinessRegistrationNumber(firstNumber, middleNumber, lastNumber);
    }

}
