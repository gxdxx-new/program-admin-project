package com.gxdxx.programadmin.dto;

import com.gxdxx.programadmin.entity.SearchType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSearchDto {

    SearchType searchType;

    String searchValue;

}
