package com.gxdxx.programadmin.controller;

import com.gxdxx.programadmin.dto.CompanyFormDto;
import com.gxdxx.programadmin.dto.MemberFormDto;
import com.gxdxx.programadmin.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/companies")
@Controller
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/new")
    public String companyForm(Model model) {
        model.addAttribute("companyFormDto", new CompanyFormDto());
        return "companies/form";
    }

    @PostMapping("/new")
    public String companyNew(@Valid CompanyFormDto companyFormDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "companies/form";
        }

        companyService.saveCompany(companyFormDto);
        return "redirect:/";
    }

}
