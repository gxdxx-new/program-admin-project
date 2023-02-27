package com.gxdxx.programadmin.dto;

import com.gxdxx.programadmin.entity.Company;
import com.gxdxx.programadmin.entity.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberListDto {

    private Long id;

    private String memberName;

    private String email;

    private String nickname;

    private String createdAt;

    private Company company;

    public static MemberListDto from(Member member) {
        return new MemberListDto(
                member.getId(),
                member.getMemberName(),
                member.getEmail(),
                member.getNickname(),
                member.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss")),
                member.getCompany()
        );
    }

}
