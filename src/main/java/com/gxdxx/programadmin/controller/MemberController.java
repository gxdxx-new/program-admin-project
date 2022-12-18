package com.gxdxx.programadmin.controller;

import com.gxdxx.programadmin.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class MemberController {

    private MemberService memberService;

}
