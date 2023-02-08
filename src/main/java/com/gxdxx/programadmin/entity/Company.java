package com.gxdxx.programadmin.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(columnList = "companyName"),
        @Index(columnList = "createdBy"),
        @Index(columnList = "createdAt")
})
@Entity
public class Company extends Auditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Embedded
    private BusinessRegistrationNumber registrationNumber;

    @Column(nullable = false, length = 10)
    private String chiefName;

    @Column(nullable = false, length = 20)
    private String companyName;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false)
    @Embedded
    private TelephoneNumber telNumber;

    @Embedded
    private List<AfterService> afterServices = new ArrayList<>();

    private Company(BusinessRegistrationNumber registrationNumber, String chiefName, String companyName, String email, TelephoneNumber telNumber) {
        this.registrationNumber = registrationNumber;
        this.chiefName = chiefName;
        this.companyName = companyName;
        this.email = email;
        this.telNumber = telNumber;
    }

    public static Company of(BusinessRegistrationNumber registrationNumber, String chiefName, String companyName, String email, TelephoneNumber telNumber) {
        return new Company(registrationNumber, chiefName, companyName, email, telNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company company)) return false;
        return id != null && id.equals(company.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
