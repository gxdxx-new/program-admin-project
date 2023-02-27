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
public class Position extends Auditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String positionName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    private Position(String positionName, Organization organization) {
        this.positionName = positionName;
        this.organization = organization;
    }

    public static Position of(String positionName, Organization organization) {
        return new Position(positionName, organization);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position position)) return false;
        return id != null && id.equals(position.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
