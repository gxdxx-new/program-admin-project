package com.gxdxx.programadmin.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class TelephoneNumber {

    public String firstTelNumber;

    public String middleTelNumber;

    public String lastTelNumber;

    private TelephoneNumber(String firstTelNumber, String middleTelNumber, String lastTelNumber) {
        this.firstTelNumber = firstTelNumber;
        this.middleTelNumber = middleTelNumber;
        this.lastTelNumber = lastTelNumber;
    }

    public static TelephoneNumber of(String firstTelNumber, String middleTelNumber, String lastTelNumber) {
        return new TelephoneNumber(firstTelNumber, middleTelNumber, lastTelNumber);
    }

}
