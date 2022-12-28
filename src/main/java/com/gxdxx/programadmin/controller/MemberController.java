package com.gxdxx.programadmin.controller;

import com.gxdxx.programadmin.dto.MemberFormDto;
import com.gxdxx.programadmin.entity.Member;
import com.gxdxx.programadmin.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class MemberController {

    private MemberService memberService;

    @GetMapping(value = "/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "members/form";
    }

    @PostMapping(value = "/new")
    public String memberNew(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "members/form";
        }
        
        try {
            memberService.saveMember(memberFormDto);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "회원가입 중 에러가 발생했습니다.");
            return "members/form";
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "members/login";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMessage", "아이디 또는 비밀번호를 확인해주세요.");
        return "members/login";
    }

}
