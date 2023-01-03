package com.gxdxx.programadmin.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostDetailDto {

    private Long id;

    private String title;

    private String content;

    private String hashtag;

    private String createdBy;

    private LocalDateTime createdAt;

    @Builder
    public PostDetailDto(Long id, String title, String content, String hashtag, String createdBy, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }


}
