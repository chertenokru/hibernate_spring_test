package ru.chertenok.spring.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.chertenok.spring.hibernate.entity.Permission;
import ru.chertenok.spring.hibernate.services.DataGenerateService;
import ru.chertenok.spring.hibernate.services.PermissionService;
import ru.chertenok.spring.hibernate.util.PageInfo;

import static ru.chertenok.spring.hibernate.config.Config.*;

@Controller
public class MainController {
    private DataGenerateService dataGenerateService;
    private PermissionService permissionService;
    static {
        // Добавляем права контроллера
        PERMISSION_MAP.put(PermissionName.mainDataGenerate,
                new Permission("MAIN_DATA_GENERATE", "MAIN", "Генерация случайных данных"));
        // добавляем страницы контроллера
        PAGE_MAP.put(PagesName.mainShablon, new PageInfo("/", "${message}", "main_shablon"));
        PAGE_MAP.put(PagesName.dataGenerate, new PageInfo("/generate", "Home", "index",
                PERMISSION_MAP.get(PermissionName.mainDataGenerate).getName()));
    }


    @Autowired
    public void setDataGenerateService(DataGenerateService dataGenerateService) {
        this.dataGenerateService = dataGenerateService;
    }

    @Autowired
    public MainController(PermissionService permissionService) {
        this.permissionService = permissionService;
        // проверяем наличие в бд и если нет, то заносим и даём права админу
        permissionService.registerPermission(PERMISSION_MAP.get(PermissionName.mainDataGenerate));
        //формируем  пути от страницы до корня, нормальные + ошибки, если есть обработка ошибок, чтоб вернуться на страницу назад
        BREADCRUMB_MAP.put(PagesName.home, new PageInfo[]{PAGE_MAP.get(PagesName.home)});
        BREADCRUMB_MAP.put(PagesName.page404, new PageInfo[]{PAGE_MAP.get(PagesName.home),PAGE_MAP.get(PagesName.page404)});
        BREADCRUMB_MAP.put(PagesName.mainShablon, new PageInfo[]{PAGE_MAP.get(PagesName.home)});
        BREADCRUMB_MAP.put(PagesName.dataGenerate, new PageInfo[]{PAGE_MAP.get(PagesName.home),PAGE_MAP.get(PagesName.dataGenerate)});

    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("mainMessage", "");
        PageInfo.makeAllPlease(model,PagesName.home);
        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
    }

    @RequestMapping("/generate")
    public String generateData(Model model) {
        if (PageInfo.makeAllPlease(model,PagesName.dataGenerate))
        {
            dataGenerateService.generateData();
            model.addAttribute("mainMessage", "Данные сгенерированы");
        }
        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
    }
}


