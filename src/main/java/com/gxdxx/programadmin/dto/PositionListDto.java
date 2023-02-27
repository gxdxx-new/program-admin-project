package com.gxdxx.programadmin.dto;

import com.gxdxx.programadmin.entity.Position;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PositionListDto {

    private Long id;

    private String positionName;

    private List<PositionMemberListDto> positionMemberListDtos;

    public static PositionListDto from(Position position) {
        return new PositionListDto(
                position.getId(),
                position.getPositionName(),
                new ArrayList<>()
        );
    }

}
