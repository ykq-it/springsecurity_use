package com.ykq.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 功能描述：
 *
 * @author: ykq
 * @date: 2020/10/25 13:07
 */
@Controller
public class BaseController {

    @GetMapping("/login.html")
    public String loginPage() {
        return "/login.html";
    }

    @GetMapping("/home.html")
    public String homePage() {
        return "/home.html";
    }

    @GetMapping("/")
    public String basePage() {
        return "/home.html";
    }

    @GetMapping("/error.html")
    public String errorPage() {
        return "/error.html";
    }
}
