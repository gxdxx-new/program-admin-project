package com.gxdxx.programadmin.controller;

import com.gxdxx.programadmin.dto.CommentFormDto;
import com.gxdxx.programadmin.service.CommentService;
import com.gxdxx.programadmin.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class CommentController {

    private final PostService postService;
    private final CommentService commentService;

    @PostMapping(value = "/{postId}")
    public @ResponseBody
    ResponseEntity commentNew(@Valid @RequestBody CommentFormDto commentFormDto, BindingResult bindingResult,
                              @PathVariable("postId") Long postId, Principal principal) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<String>("댓글을 1000자 이내로 입력해주세요.", HttpStatus.FORBIDDEN);
        }

        commentService.saveComment(principal.getName(), postId, commentFormDto);

        return new ResponseEntity<Long>(postId, HttpStatus.OK);
    }

    @PatchMapping(value = "/{commentId}")
    public @ResponseBody ResponseEntity updateComment(@Valid @RequestBody CommentFormDto commentFormDto, BindingResult bindingResult,
                                                      @PathVariable("commentId") Long commentId, Principal principal) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<String>("댓글을 1000자 이내로 입력해주세요.", HttpStatus.FORBIDDEN);
        }

        if (!commentService.validateComment(commentId, principal.getName())) {
            return new ResponseEntity<String>("수정 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        commentService.updateComment(commentId, commentFormDto);

        return new ResponseEntity<Long>(commentId, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{commentId}")
    public @ResponseBody ResponseEntity deleteComment(@PathVariable("commentId") Long commentId, Principal principal) {

        if (!commentService.validateComment(commentId, principal.getName())) {
            return new ResponseEntity<String>("삭제 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        commentService.deleteComment(commentId);
        return new ResponseEntity<Long>(commentId, HttpStatus.OK);
    }

}
