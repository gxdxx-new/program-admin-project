package com.gxdxx.programadmin.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentListDto {

    private Long id;

    private Long postId;

    private String content;

    private String createdBy;

    private String createdAt;

    @Builder
    public CommentListDto(Long id, Long postId, String content, String createdBy, LocalDateTime createdAt) {
        this.id = id;
        this.postId = postId;
        this.content = content;
        this.createdBy = createdBy;
        this.createdAt = createdAt.format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss"));
    }

}
