package com.gxdxx.programadmin.controller;

import com.gxdxx.programadmin.dto.PostSearchDto;
import com.gxdxx.programadmin.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/posts")
@Controller
public class PostController {

    private final PostService postService;

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
