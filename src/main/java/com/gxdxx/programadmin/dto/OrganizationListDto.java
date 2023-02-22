package com.gxdxx.programadmin.dto;

import com.gxdxx.programadmin.entity.Organization;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OrganizationListDto {

    private Long id;

    private String organizationName;

    public static OrganizationListDto from(Organization organization) {
        return new OrganizationListDto(
                organization.getId(),
                organization.getOrganizationName()
        );
    }

}
