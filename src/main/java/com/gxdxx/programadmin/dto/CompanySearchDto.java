package com.gxdxx.programadmin.dto;

import com.gxdxx.programadmin.entity.CompanySearchType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanySearchDto {

    private CompanySearchType searchType;

    private String searchValue;

}
