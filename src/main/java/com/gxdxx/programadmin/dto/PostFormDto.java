package com.gxdxx.programadmin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class PostFormDto {

    private Long id;

    @NotEmpty(message = "글제목은 필수 입력 값입니다.")
    @Length(min = 2, max = 50, message = "글제목은 2자 이상, 50자 이하로 입력해주세요.")
    private String title;

    @NotEmpty(message = "글내용은 필수 입력 값입니다.")
    @Length(min = 2, max = 500, message = "글내용은 2자 이상, 500자 이하로 입력해주세요.")
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
