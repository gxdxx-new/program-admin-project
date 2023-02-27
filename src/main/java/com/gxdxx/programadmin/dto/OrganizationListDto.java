package com.gxdxx.programadmin.dto;

import com.gxdxx.programadmin.entity.Organization;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OrganizationListDto {

    private Long id;

    private String organizationName;

    private List<PositionListDto> positionListDtos;

    public static OrganizationListDto from(Organization organization) {
        return new OrganizationListDto(
                organization.getId(),
                organization.getOrganizationName(),
                new ArrayList<>()
        );
    }

}
