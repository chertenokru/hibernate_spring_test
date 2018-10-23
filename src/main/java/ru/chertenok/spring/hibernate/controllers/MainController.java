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
        PAGE_MAP.put(PagesName.mainShablon, new PageInfo("/", "${message}", "main_shablon"));
    }


    @Autowired
    public void setDataGenerateService(DataGenerateService dataGenerateService) {
        this.dataGenerateService = dataGenerateService;
    }

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("mainMessage","");
        model.addAttribute("contentPage",PAGE_MAP.get(PagesName.home).getSHABLON());
        model.addAttribute(BREADCRUMB, new PageInfo[] { PAGE_MAP.get(PagesName.home)});
        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
    }

    @RequestMapping("/generate")
    public String generateData(Model model){
        dataGenerateService.generateData();
        model.addAttribute("mainMessage","Данные сгенерированы");
        model.addAttribute("contentPage",PAGE_MAP.get(PagesName.home).getSHABLON());
        model.addAttribute(BREADCRUMB, new PageInfo[] { PAGE_MAP.get(PagesName.home)});
        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
    }
}


