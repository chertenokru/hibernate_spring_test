package ru.chertenok.spring.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.chertenok.spring.hibernate.services.DataGenerateService;

@Controller
public class MainController {
    private DataGenerateService dataGenerateService;

    @Autowired
    public void setDataGenerateService(DataGenerateService dataGenerateService) {
        this.dataGenerateService = dataGenerateService;
    }

    @RequestMapping("/")
    public String index(){
     return "index";
    }

    @RequestMapping("/generate")
    public String generateData(){
        dataGenerateService.generateData();
        return "index";
    }
}
