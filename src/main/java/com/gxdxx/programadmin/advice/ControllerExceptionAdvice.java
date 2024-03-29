package com.gxdxx.programadmin.advice;

import com.gxdxx.programadmin.dto.CommentListDto;
import com.gxdxx.programadmin.dto.CompanyFormDto;
import com.gxdxx.programadmin.dto.MemberFormDto;
import com.gxdxx.programadmin.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.w3c.dom.events.EventException;

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
        return "index";
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

    @ExceptionHandler(RegistrationNumberAlreadyExistsException.class)
    public String registrationNumberAlreadyExistsException(Model model) {
        model.addAttribute("errorMessage", "이미 가입된 회사입니다.");
        model.addAttribute("companyFormDto", new CompanyFormDto());
        return "companies/form";
    }

    @ExceptionHandler(RegistrationNumberNotFoundException.class)
    public String registrationNumberNotFoundException(Model model) {
        model.addAttribute("errorMessage", "존재하지 않는 회사입니다.");
        return "index";
    }


    @ExceptionHandler(MemberNotFoundException.class)
    public String memberNotFoundException(Model model) {
        model.addAttribute("errorMessage", "존재하지 않는 회원입니다.");
        return "index";
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    public String passwordNotMatchException(Model model) {
        model.addAttribute("errorMessage", "저장된 비밀번호와 일치하지 않습니다.");
        return "index";
    }

    @ExceptionHandler(SuperAdminNotFoundException.class)
    public String superAdminNotFoundException(Model model) {
        model.addAttribute("errorMessage", "존재하지 않는 최고 관리자입니다.");
        return "index";
    }

    @ExceptionHandler(AdminNotFoundException.class)
    public String adminNotFoundException(Model model) {
        model.addAttribute("errorMessage", "존재하지 않는 관리자입니다.");
        return "index";
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public String companyNotFoundException(Model model) {
        model.addAttribute("errorMessage", "존재하지 않는 회사입니다.");
        return "index";
    }

    @ExceptionHandler(OrganizationAjaxNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> organizationAjaxNotFoundException() {
        return new ResponseEntity<String>("존재하지 않는 부서입니다.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrganizationNotFoundException.class)
    public String organizationNotFoundException(Model model) {
        model.addAttribute("errorMessage", "존재하지 않는 부서입니다.");
        return "index";
    }

    @ExceptionHandler(PositionNotFoundException.class)
    public String positionNotFoundException(Model model) {
        model.addAttribute("errorMessage", "존재하지 않는 직책입니다.");
        return "index";
    }

    @ExceptionHandler({EventException.class, RuntimeException.class})
    public String eventErrorHandler(Model model){
        return "error";
    }

}
