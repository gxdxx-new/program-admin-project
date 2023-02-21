package com.gxdxx.programadmin.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(columnList = "createdBy"),
        @Index(columnList = "createdAt")
})
@Entity
public class Organization extends Auditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String organizationName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id")
    private Company company;

    private Organization(String organizationName, Company company) {
        this.organizationName = organizationName;
        this.company = company;
    }

    public static Organization of(String organizationName, Company company) {
        return new Organization(organizationName, company);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization organization)) return false;
        return id != null && id.equals(organization.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
