package com.gxdxx.programadmin.advice;

import com.gxdxx.programadmin.dto.MemberFormDto;
import com.gxdxx.programadmin.exception.CommentAjaxNotFoundException;
import com.gxdxx.programadmin.exception.MemberNameAlreadyExistsException;
import com.gxdxx.programadmin.exception.PostAjaxNotFoundException;
import com.gxdxx.programadmin.exception.PostNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(MemberNameAlreadyExistsException.class)
    public String memberNameAlreadyExistsException(MemberNameAlreadyExistsException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage() + "은 중복된 이메일 입니다.");
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "members/form";
    }

    @ExceptionHandler(PostNotFoundException.class)
    public String postNotFoundException(Model model) {
        model.addAttribute("errorMessage", "존재하지 않는 게시글입니다.");
        return "posts/index";
    }

    @ExceptionHandler(PostAjaxNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> postAjaxNotFoundException() {
        return new ResponseEntity<String>("존재하지 않는 게시글입니다.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CommentAjaxNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> commentAjaxNotFoundException() {
        return new ResponseEntity<String>("존재하지 않는 댓글입니다.", HttpStatus.NOT_FOUND);
    }

}
