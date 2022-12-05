package com.gxdxx.programadmin.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comment",
        indexes = {
        @Index(columnList = "createdBy"),
        @Index(columnList = "createdAt")
})
@Entity
public class Comment extends Auditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(optional = false)
    private Post post;

    @Setter
    @Column(nullable = false, length = 1000)
    private String content;

    private Comment(Post post, String content) {
        this.post = post;
        this.content = content;
    }

    public static Comment of(Post post, String content) {
        return new Comment(post, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment comment)) return false;
        return id != null && id.equals(comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
