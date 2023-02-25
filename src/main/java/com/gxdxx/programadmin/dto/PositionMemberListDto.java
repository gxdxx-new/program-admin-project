package com.gxdxx.programadmin.dto;

import com.gxdxx.programadmin.entity.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PositionMemberListDto {

    private Long id;

    private String memberName;

    public static PositionMemberListDto from(Member member) {
        return new PositionMemberListDto(
                member.getId(),
                member.getMemberName()
        );
    }

}
