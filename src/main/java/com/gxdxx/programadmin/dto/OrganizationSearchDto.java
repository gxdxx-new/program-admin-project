package com.gxdxx.programadmin.dto;

import com.gxdxx.programadmin.entity.OrganizationSearchType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationSearchDto {

    private OrganizationSearchType searchType;

    private String searchValue;

}
