package com.gxdxx.programadmin.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentFormDto {

    private Long id;

    @NotEmpty(message = "댓글 내용은 필수 입력 값입니다.")
    @Length(max = 1000, message = "댓글 내용은 1000자 이하로 입력해주세요.")
    private String content;

    private String createdBy;

    private LocalDateTime createdAt;

    @Builder
    public CommentFormDto(Long id, String content, String createdBy, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

}
