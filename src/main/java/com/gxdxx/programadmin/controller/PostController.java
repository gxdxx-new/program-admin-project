package com.gxdxx.programadmin.controller;

import com.gxdxx.programadmin.dto.PostFormDto;
import com.gxdxx.programadmin.dto.PostSearchDto;
import com.gxdxx.programadmin.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/posts")
@Controller
public class PostController {

    private final PostService postService;

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
