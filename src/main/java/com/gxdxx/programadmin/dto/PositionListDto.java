package com.gxdxx.programadmin.dto;

import com.gxdxx.programadmin.entity.Position;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PositionListDto {

    private String positionName;

    public static PositionListDto from(Position position) {
        return new PositionListDto(
                position.getPositionName()
        );
    }

}
