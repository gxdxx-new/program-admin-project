package com.gxdxx.programadmin.service;

import com.gxdxx.programadmin.dto.PostFormDto;
import com.gxdxx.programadmin.dto.PostListDto;
import com.gxdxx.programadmin.entity.Member;
import com.gxdxx.programadmin.entity.Post;
import com.gxdxx.programadmin.entity.SearchType;
import com.gxdxx.programadmin.repository.MemberRepository;
import com.gxdxx.programadmin.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public Page<PostListDto> searchPosts(SearchType searchType, String searchValue, Pageable pageable) {

        if (searchValue == null || searchValue.isBlank()) {
            return postRepository.findAll(pageable).map(PostListDto::from);
        }

        return switch (searchType) {
            case TITLE -> postRepository.findByTitleContains(searchValue, pageable).map(PostListDto::from);
            case NICKNAME -> postRepository.findByMember_NicknameContains(searchValue, pageable).map(PostListDto::from);
            case HASHTAG -> postRepository.findByHashtag("#" + searchValue, pageable).map(PostListDto::from);
        };

    }

    public PostFormDto getPostForm(Long postId) {

        Post post = postRepository.findById(postId).orElseThrow();
        PostFormDto postFormDto = PostFormDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .hashtag(post.getHashtag())
                .build();

        return postFormDto;
    }

    public Long savePost(String memberId, PostFormDto postFormDto) {

        Member member = memberRepository.findByMemberId(memberId);

        Post post = Post.of(member, postFormDto.getTitle(), postFormDto.getContent(), postFormDto.getHashtag());
        postRepository.save(post);

        return post.getId();
    }

    public Long updatePost(PostFormDto postFormDto) {

        Post post = postRepository.findById(postFormDto.getId()).orElseThrow();
        post.updatePost(postFormDto.getTitle(), post.getTitle(), post.getHashtag());

        return post.getId();
    }

    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        postRepository.delete(post);
    }

    public boolean validatePost(Long postId, String memberId) {
        Member currentMember = memberRepository.findByMemberId(memberId);
        Post post = postRepository.findById(postId).orElseThrow();
        Member savedMember = post.getMember();

        if (!StringUtils.equals(currentMember.getMemberId(), savedMember.getMemberId())) {
            return false;
        }

        return true;
    }

}
