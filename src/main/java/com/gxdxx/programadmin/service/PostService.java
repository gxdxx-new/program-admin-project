package com.gxdxx.programadmin.service;

import com.gxdxx.programadmin.dto.PostFormDto;
import com.gxdxx.programadmin.dto.PostListDto;
import com.gxdxx.programadmin.entity.Post;
import com.gxdxx.programadmin.entity.SearchType;
import com.gxdxx.programadmin.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class PostService {

    private final PostRepository postRepository;

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

}
