package com.gxdxx.programadmin.controller;

import com.gxdxx.programadmin.dto.*;
import com.gxdxx.programadmin.entity.Member;
import com.gxdxx.programadmin.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "members/form";
    }

    @PostMapping("/new")
    public String memberNew(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "members/form";
        }

        memberService.saveMember(memberFormDto.getMemberName(), memberFormDto.getPassword(), memberFormDto.getEmail(), memberFormDto.getNickname());
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

    @GetMapping("/profile/{memberName}")
    public String memberProfile(Model model, @PathVariable("memberName") String memberName) {

        MemberProfileDto memberProfileDto = memberService.getProfile(memberName);

        model.addAttribute("member", memberProfileDto);
        return "members/profile";
    }

    @GetMapping("/profile/password")
    public String memberPasswordEditForm(Model model) {
        model.addAttribute("memberPasswordEditFormDto", new MemberPasswordEditFormDto());
        return "members/editPassword";
    }

    @PostMapping("/profile/password")
    public String memberPasswordEdit(@Valid MemberPasswordEditFormDto memberPasswordEditFormDto, BindingResult bindingResult,
                                     Principal principal, Model model) {

        if (bindingResult.hasErrors()) {
            return "members/editPassword";
        }

        if (!StringUtils.equals(memberPasswordEditFormDto.getChangePassword(), memberPasswordEditFormDto.getChangePasswordCheck())) {
            model.addAttribute("errorMessage", "변경할 비밀번호를 다시 입력해주세요.");
            return "members/editPassword";
        }

        memberService.changePassword(principal.getName(), memberPasswordEditFormDto.getCurrentPassword(), memberPasswordEditFormDto.getChangePassword());

        return "redirect:/";
    }

    @GetMapping("/profile/email")
    public String memberEmailEditForm(Model model) {
        model.addAttribute("memberEmailEditFormDto", new MemberEmailEditFormDto());
        return "members/editEmail";
    }

    @PostMapping("/profile/email")
    public String memberEmailEdit(@Valid MemberEmailEditFormDto memberEmailEditFormDto, BindingResult bindingResult,
                                     Principal principal, Model model) {

        if (bindingResult.hasErrors()) {
            return "members/editEmail";
        }

        memberService.changeEmail(principal.getName(), memberEmailEditFormDto.getChangeEmail());

        return "redirect:/";
    }

    @GetMapping("/profile/nickname")
    public String memberNicknameEditForm(Model model) {
        model.addAttribute("memberNicknameEditFormDto", new MemberNicknameEditFormDto());
        return "members/editNickname";
    }

    @PostMapping("/profile/nickname")
    public String memberNicknameEdit(@Valid MemberNicknameEditFormDto memberNicknameEditFormDto, BindingResult bindingResult,
                                  Principal principal, Model model) {

        if (bindingResult.hasErrors()) {
            return "members/editNickname";
        }

        memberService.changeNickname(principal.getName(), memberNicknameEditFormDto.getChangeNickname());

        return "redirect:/";
    }

}
