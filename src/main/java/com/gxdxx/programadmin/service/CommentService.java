package com.gxdxx.programadmin.service;

import com.gxdxx.programadmin.dto.CommentFormDto;
import com.gxdxx.programadmin.dto.CommentListDto;
import com.gxdxx.programadmin.entity.Comment;
import com.gxdxx.programadmin.entity.Member;
import com.gxdxx.programadmin.entity.Post;
import com.gxdxx.programadmin.exception.CommentAjaxNotFoundException;
import com.gxdxx.programadmin.exception.PostAjaxNotFoundException;
import com.gxdxx.programadmin.exception.PostNotFoundException;
import com.gxdxx.programadmin.repository.CommentRepository;
import com.gxdxx.programadmin.repository.MemberRepository;
import com.gxdxx.programadmin.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public List<CommentListDto> getComments(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        List<CommentListDto> commentListDtos = new ArrayList<>();
        for (Comment comment : post.getComments()) {
            commentListDtos.add(CommentListDto.builder()
                            .id(comment.getId())
                            .postId(postId)
                            .content(comment.getContent())
                            .createdBy(comment.getCreatedBy())
                            .createdAt(comment.getCreatedAt())
                    .build());
        }

        return commentListDtos;
    }

    public void saveComment(String userName, Long postId, CommentFormDto commentFormDto) {
        Member member = memberRepository.findByMemberName(userName);
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        Comment comment = Comment.of(member, post, commentFormDto.getContent());
        post.getComments().add(comment);
        commentRepository.save(comment);
    }

    public void updateComment(Long commentId, CommentFormDto commentFormDto) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentAjaxNotFoundException::new);
        comment.updateComment(commentFormDto.getContent());

    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentAjaxNotFoundException::new);
        Post post = postRepository.findById(comment.getPost().getId()).orElseThrow(PostAjaxNotFoundException::new);

        // 게시글에서도 삭제하고, 댓글에서도 삭제
        for (Comment findComment : post.getComments()) {
            if (findComment.getId() == comment.getId()) {
                post.getComments().remove(findComment);
                break;
            }
        }

        commentRepository.delete(comment);
    }

    public boolean validateComment(Long commentId, String memberName) {
        Member currentMember = memberRepository.findByMemberName(memberName);
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentAjaxNotFoundException::new);
        Member savedMember = comment.getMember();

        if (!StringUtils.equals(currentMember.getMemberName(), savedMember.getMemberName())) {
            return false;
        }

        return true;
    }

}
