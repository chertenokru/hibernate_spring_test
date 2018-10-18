package ru.chertenok.spring.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.chertenok.spring.hibernate.services.DataGenerateService;
import ru.chertenok.spring.hibernate.util.PageInfo;

import static ru.chertenok.spring.hibernate.util.Config.*;

@Controller
public class MainController {
    private DataGenerateService dataGenerateService;

    {
        PAGE_MAP.put(PagesName.home, new PageInfo("/", "Home", "index"));
        PAGE_MAP.put(PagesName.page404, new PageInfo("/", "${message}", "page404"));
    }


    @Autowired
    public String setDataGenerateService(DataGenerateService dataGenerateService) {
        this.dataGenerateService = dataGenerateService;
        return PAGE_MAP.get(PagesName.home).getSHABLON();
    }

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("mainMessage","");
        model.addAttribute(BREADCRUMB, new PageInfo[] { PAGE_MAP.get(PagesName.home)});
        return PAGE_MAP.get(PagesName.home).getSHABLON();
    }

    @RequestMapping("/generate")
    public String generateData(Model model){
        dataGenerateService.generateData();
        model.addAttribute("mainMessage","Данные сгенерированы");
        model.addAttribute(BREADCRUMB, new PageInfo[] { PAGE_MAP.get(PagesName.home)});
        return PAGE_MAP.get(PagesName.home).getSHABLON();
    }
}
