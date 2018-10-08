package com.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/password")
public class PasswordController
{
    @RequestMapping("/repwd")
    public String repwd(){
        return "/WEB-INF/jsp/pwd.jsp";
    }
}
