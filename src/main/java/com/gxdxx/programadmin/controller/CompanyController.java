package com.gxdxx.programadmin.controller;

import com.gxdxx.programadmin.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/companies")
@Controller
public class CompanyController {

    private final CompanyService companyService;

}
