package com.gxdxx.programadmin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class PostFormDto {

    private Long id;

    @Max(50)
    @NotNull(message = "글제목은 필수 입력 값입니다.")
    private String title;

    @Max(500)
    @NotNull(message = "글내용은 필수 입력 값입니다.")
    private String content;

    private String hashtag;

    @Builder
    public PostFormDto(Long id, String title, String content, String hashtag) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

}
