package com.gxdxx.programadmin.controller;

import com.gxdxx.programadmin.dto.MemberFormDto;
import com.gxdxx.programadmin.service.SuperAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/superadmins")
@Controller
public class SuperAdminController {

    private final SuperAdminService superAdminService;

    @GetMapping("/new")
    public String adminForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "superadmins/adminForm";
    }

    @PostMapping("/new")
    public String adminNew(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Principal principal) {

        if (bindingResult.hasErrors()) {
            return "superadmins/adminForm";
        }

        superAdminService.saveAdmin(principal.getName(), memberFormDto.getMemberName(), memberFormDto.getPassword(), memberFormDto.getEmail(), memberFormDto.getNickname());
        return "redirect:/";
    }

    @GetMapping("/admins")
    public String getAdminProfiles(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            Principal principal,
            Model model) {

        model.addAttribute("admins", superAdminService.searchAdmins(principal.getName() ,pageable));
        model.addAttribute("maxPage", 5);

        return "superadmins/adminList";
    }

    @GetMapping("/members")
    public String getMemberProfiles(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            Principal principal,
            Model model) {

        model.addAttribute("members", superAdminService.searchMembers(principal.getName() ,pageable));
        model.addAttribute("maxPage", 5);

        return "superadmins/memberList";
    }

    @GetMapping("/companies")
    public String getCompanies(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            Principal principal,
            Model model) {

        model.addAttribute("companies", superAdminService.searchCompanies(principal.getName() ,pageable));
        model.addAttribute("maxPage", 5);

        return "superadmins/companyList";
    }

}
