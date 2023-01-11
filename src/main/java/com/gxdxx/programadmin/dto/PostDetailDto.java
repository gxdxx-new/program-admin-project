package com.gxdxx.programadmin.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostDetailDto {

    private Long id;

    private String title;

    private String content;

    private String hashtag;

    private String createdBy;

    private String createdAt;

    @Builder
    public PostDetailDto(Long id, String title, String content, String hashtag, String createdBy, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
        this.createdBy = createdBy;
        this.createdAt = createdAt.format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss"));
    }


}
