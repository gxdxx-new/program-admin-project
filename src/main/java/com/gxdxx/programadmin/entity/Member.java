package com.gxdxx.programadmin.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(columnList = "memberId"),
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
    private String memberId;

    @Setter
    @Column(nullable = false)
    private String password;

    @Setter
    @Column(length = 100)
    private String email;

    @Setter
    @Column(length = 100)
    private String nickname;

    private Member(String memberId, String password, String email, String nickname) {
        this.memberId = memberId;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }

    public static Member of(String memberId, String password, String email, String nickname) {
        return new Member(memberId, password, email, nickname);
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
