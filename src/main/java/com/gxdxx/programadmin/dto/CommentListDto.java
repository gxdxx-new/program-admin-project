package com.gxdxx.programadmin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentListDto {

    private Long id;

    private Long postId;

    private String content;

    private String createdBy;

    private LocalDateTime createdAt;

    @Builder
    public CommentListDto(Long id, Long postId, String content, String createdBy, LocalDateTime createdAt) {
        this.id = id;
        this.postId = postId;
        this.content = content;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

}
