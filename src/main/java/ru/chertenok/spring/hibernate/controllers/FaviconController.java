package ru.chertenok.spring.hibernate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
 class FaviconController {
    @RequestMapping("favicon.ico")
     String favicon() {
        return "forward:/resources/images/favicon.ico";
    }
}