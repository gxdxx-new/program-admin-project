package com.gxdxx.programadmin.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
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
public class Member extends Auditing implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String memberName;

    @Column(nullable = false)
    private String password;

    @Column(length = 100)
    private String email;

    @Column(length = 100)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private Position position;

    private Member(String memberName, String password, String email, String nickname, Role role) {
        this.memberName = memberName;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.role = role;
    }

    public static Member of(String memberName, String password, String email, String nickname, Role role) {
        return new Member(memberName, password, email, nickname, role);
    }

    public void changePassword(String changePassword) {
        this.password = changePassword;
    }

    public void changeEmail(String changeEmail) {
        this.email = changeEmail;
    }

    public void changeNickname(String changeNickname) {
        this.nickname = changeNickname;
    }

    public void applyCompany(Company company) {
        this.company = company;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getValue()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return memberName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
