package com.gxdxx.programadmin.controller;

import com.gxdxx.programadmin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/admins")
@Controller
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public String getAdminProfiles(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            Principal principal,
            Model model) {

        model.addAttribute("admins", adminService.searchAdmins(principal.getName() ,pageable));
        model.addAttribute("maxPage", 5);

        return "admins/adminList";
    }

    @GetMapping("/members")
    public String getMemberProfiles(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            Principal principal,
            Model model) {

        model.addAttribute("members", adminService.searchMembers(principal.getName() ,pageable));
        model.addAttribute("maxPage", 5);

        return "admins/memberList";
    }

    @GetMapping("/companies")
    public String getCompanies(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            Principal principal,
            Model model) {

        model.addAttribute("companies", adminService.searchCompanies(principal.getName() ,pageable));
        model.addAttribute("maxPage", 5);

        return "admins/companyList";
    }

}
