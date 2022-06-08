package com.tuplv.api.web;

import com.tuplv.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/email")
public class EmailAPI {
    @Autowired
    IEmailService emailService;

    @PostMapping(value = "/send", produces = "text/plain;charset=UTF-8")
    public String sendEmail(@RequestParam(name = "fullName") String fullName,
                            @RequestParam(name = "email") String email) {

        try {
            emailService.sendEmail(email, fullName);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "error";
    }
}
