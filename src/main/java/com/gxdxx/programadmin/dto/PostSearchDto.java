package com.gxdxx.programadmin.dto;

import com.gxdxx.programadmin.entity.PostSearchType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSearchDto {

    PostSearchType searchType;

    String searchValue;

}
