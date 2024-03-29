package com.gxdxx.programadmin.controller;

import com.gxdxx.programadmin.dto.*;
import com.gxdxx.programadmin.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;
import java.security.Principal;

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

    @GetMapping
    public String companies(CompanySearchDto companySearchDto,
                            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                            Model model) {

        model.addAttribute("companies", companyService.searchCompanies(companySearchDto.getSearchType(), companySearchDto.getSearchValue(), pageable));
        model.addAttribute("companySearchDto", companySearchDto);
        model.addAttribute("maxPage", 5);

        return "members/company";
    }

    @GetMapping("/link/{firstNumber}/{middleNumber}/{lastNumber}")
    public String companyLinkForm(@PathVariable("firstNumber") String firstNumber,
                              @PathVariable("middleNumber") String middleNumber,
                              @PathVariable("lastNumber") String lastNumber,
                              Model model) {
        model.addAttribute("company", companyService.getLinkCompany(firstNumber, middleNumber, lastNumber));
        return "members/link";
    }

    @PostMapping("/employeeCheck")
    public String companyLink(@Valid MailConfirmDto mailConfirmDto, BindingResult bindingResult,
                              Principal principal, Model model) {

        if (bindingResult.hasErrors()) {
            return "members/profile/" + principal.getName();
        }

        if (!StringUtils.equals(mailConfirmDto.getMailNumberWhat(), mailConfirmDto.getMailNumber())) {
            model.addAttribute("errorMessage", "인증번호가 틀렸습니다.");
            return "members/profile/" + principal.getName();
        }

        companyService.validateVerificationCode(mailConfirmDto.getFirstNumber(), mailConfirmDto.getMiddleNumber(), mailConfirmDto.getLastNumber(),principal.getName());
        return "redirect:/";
    }

}
