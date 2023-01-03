package com.gxdxx.programadmin.controller;

import com.gxdxx.programadmin.dto.CommentListDto;
import com.gxdxx.programadmin.dto.PostDetailDto;
import com.gxdxx.programadmin.dto.PostFormDto;
import com.gxdxx.programadmin.dto.PostSearchDto;
import com.gxdxx.programadmin.service.CommentService;
import com.gxdxx.programadmin.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/posts")
@Controller
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/new")
    public String postForm(Model model) {
        model.addAttribute("postFormDto", new PostFormDto());
        return "posts/form";
    }

    @PostMapping("/new")
    public String postNew(@Valid PostFormDto postFormDto, BindingResult bindingResult, Principal principal, Model model) {

        if (bindingResult.hasErrors()) {
            return "posts/form";
        }
        postService.savePost(principal.getName(), postFormDto);

        return "redirect:/posts";
    }

    @GetMapping("/{postId}/edit")
    public String postEditForm(@PathVariable("postId") Long postId, Principal principal, Model model) {

        if(!postService.validatePost(postId, principal.getName())) {
            return "redirect:/posts";
        }

        PostFormDto postFormDto = postService.getPostForm(postId);
        model.addAttribute("postFormDto", postFormDto);

        return "posts/form";
    }

    @PostMapping("/{postId}")
    public String postUpdate(@Valid PostFormDto postFormDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "posts/form";
        }

        postService.updatePost(postFormDto);

        return "redirect:/posts/{postId}";
    }

    @GetMapping(value = "/{postId}")
    public String postDetail(Model model, @PathVariable("postId") Long postId) {

        PostDetailDto postDetailDto = postService.getPostDetail(postId);
        List<CommentListDto> commentListDtos = commentService.getComments(postId);

        model.addAttribute("post", postDetailDto);
        model.addAttribute("comments", commentListDtos);
        return "posts/detail";
    }

    @DeleteMapping(value = "/posts/{postId}")  // 글 삭제
    public @ResponseBody ResponseEntity deletePost(
            @PathVariable("postId") Long postId, Principal principal) {

        if (!postService.validatePost(postId, principal.getName())) {
            return new ResponseEntity<String>("삭제 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        postService.deletePost(postId);
        return new ResponseEntity<Long>(postId, HttpStatus.OK);
    }

    @GetMapping
    public String posts(
            PostSearchDto postSearchDto,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            Model model) {

        model.addAttribute("posts", postService.searchPosts(postSearchDto.getSearchType(), postSearchDto.getSearchValue(), pageable));
        model.addAttribute("postSearchDto", postSearchDto);

        return "posts/index";

    }


}
