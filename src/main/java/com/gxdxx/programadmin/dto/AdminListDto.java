package com.gxdxx.programadmin.dto;

import com.gxdxx.programadmin.entity.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminListDto {

    private String adminName;

    private String email;

    private String nickname;

    private String createdAt;

    public static AdminListDto from(Member admin) {
        return new AdminListDto(
                admin.getMemberName(),
                admin.getEmail(),
                admin.getNickname(),
                admin.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss"))
        );
    }

}
