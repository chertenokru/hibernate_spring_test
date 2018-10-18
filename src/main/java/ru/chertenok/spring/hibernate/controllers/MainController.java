package ru.chertenok.spring.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.chertenok.spring.hibernate.services.DataGenerateService;

@Controller
public class MainController {
    private DataGenerateService dataGenerateService;


    @Autowired
    public String setDataGenerateService(DataGenerateService dataGenerateService) {
        this.dataGenerateService = dataGenerateService;
        return "index";
    }

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("mainMessage","");
        model.addAttribute("breadcrumb", new String[][]{{"Home","/"}});

        return "index";
    }

    @RequestMapping("/generate")
    public String generateData(Model model){
        dataGenerateService.generateData();
        model.addAttribute("mainMessage","Данные сгенерированы");
        model.addAttribute("breadcrumb", new String[][]{{"Home","/"}});
        return "index";
    }
}
