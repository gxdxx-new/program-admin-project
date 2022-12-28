package com.gxdxx.programadmin.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(columnList = "memberName"),
        @Index(columnList = "email"),
        @Index(columnList = "createdBy"),
        @Index(columnList = "createdAt")
})
@Entity
public class Member extends Auditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, length = 20)
    private String memberName;

    @Setter
    @Column(nullable = false)
    private String password;

    @Setter
    @Column(length = 100)
    private String email;

    @Setter
    @Column(length = 100)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Member(String memberName, String password, String email, String nickname) {
        this.memberName = memberName;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.role = Role.USER;
    }

    public static Member of(String memberName, String password, String email, String nickname) {
        return new Member(memberName, password, email, nickname);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member member)) return false;
        return id != null && id.equals(member.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
