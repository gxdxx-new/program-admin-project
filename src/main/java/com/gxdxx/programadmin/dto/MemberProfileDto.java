package com.gxdxx.programadmin.dto;

import com.gxdxx.programadmin.entity.Company;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class MemberProfileDto {

    private String memberName;

    private String email;

    private String nickname;

    private String createdAt;

    private Company company;

    @Builder
    public MemberProfileDto(String memberName, String email, String nickname, LocalDateTime createdAt, Company company) {
        this.memberName = memberName;
        this.email = email;
        this.nickname = nickname;
        this.createdAt = createdAt.format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss"));
        this.company = company;
    }

}
