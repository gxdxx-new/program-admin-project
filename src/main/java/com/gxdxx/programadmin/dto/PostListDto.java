package com.gxdxx.programadmin.dto;

import com.gxdxx.programadmin.entity.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    private String createdAt;

    public static PostListDto from(Post post) {
        return new PostListDto(
                post.getId(),
                post.getMember().getMemberName(),
                post.getTitle(),
                post.getContent(),
                post.getHashtag(),
                post.getCreatedBy(),
                post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss"))
        );
    }

}
