package com.gxdxx.programadmin.dto;

import com.gxdxx.programadmin.entity.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PostListDto {

    private Long id;

    private String memberName;

    private String title;

    private String content;

    private String hashtag;

    private String createdBy;

    private LocalDateTime createdAt;

    public static PostListDto of(Long id, String memberName, String title, String content, String hashtag, String createdBy, LocalDateTime createdAt) {
        return new PostListDto(id, memberName, title, content, hashtag, createdBy, createdAt);
    }

    public static PostListDto from(Post post) {
        return new PostListDto(post.getId(), post.getMember().getMemberName(), post.getTitle(), post.getContent(), post.getHashtag(), post.getCreatedBy(), post.getCreatedAt());
    }

}
