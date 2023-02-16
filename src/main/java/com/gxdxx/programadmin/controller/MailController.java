package com.gxdxx.programadmin.controller;

import com.gxdxx.programadmin.dto.CompanyListDto;
import com.gxdxx.programadmin.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RequiredArgsConstructor
@RestController
public class MailController {

    private final MailService mailService;

    @PostMapping("/mail/mailConfirm")
    public String mailConfirm(@RequestBody String email) throws MessagingException, UnsupportedEncodingException {
        email = email.replace("\"", "");
        String authCode = mailService.sendEmail(email);
        return authCode;
    }

}
