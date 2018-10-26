package ru.chertenok.spring.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.chertenok.spring.hibernate.config.Config;
import ru.chertenok.spring.hibernate.entity.Permission;
import ru.chertenok.spring.hibernate.services.PermissionService;
import ru.chertenok.spring.hibernate.services.UserService;
import ru.chertenok.spring.hibernate.util.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ru.chertenok.spring.hibernate.config.Config.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private PermissionService permissionService;

    {
        // Добавляем права контроллера
        PERMISSION_MAP.put(PermissionName.userViewList, new Permission("USERS_LIST_VIEW", "USERS", "Просмотр списка пользователей"));
        // добавляем страницы контроллера
        PAGE_MAP.put(PagesName.userList, new PageInfo("/user/list", "Список пользователей", "user_list",
                PERMISSION_MAP.get(PermissionName.userViewList).getName()));
        PAGE_MAP.put(PagesName.userLogin, new PageInfo("/user/login", "Авторизация", "user_login3"));
        PAGE_MAP.put(PagesName.userAccessDenied, new PageInfo("/denied", "Доступ запрещен", "access-denied"));
        PAGE_MAP.put(PagesName.userLoginForm, new PageInfo("/user/loginForm", "Авторизация", "user_login3"));
        //формируем  пути от страницы до корня, нормальные + ошибки, если есть обработка ошибок, чтоб вернуться на страницу назад
        BREADCRUMB_MAP.put(PagesName.userList, new PageInfo[]{PAGE_MAP.get(PagesName.home),PAGE_MAP.get(PagesName.userList)});
        BREADCRUMB_MAP.put(PagesName.userLogin, new PageInfo[]{PAGE_MAP.get(PagesName.home),PAGE_MAP.get(PagesName.userLogin)});
        BREADCRUMB_MAP.put(PagesName.userAccessDenied, new PageInfo[]{PAGE_MAP.get(PagesName.home),PAGE_MAP.get(PagesName.userAccessDenied)});
        BREADCRUMB_MAP.put(PagesName.userLoginForm, new PageInfo[]{PAGE_MAP.get(PagesName.home),PAGE_MAP.get(PagesName.userLoginForm)});

    }


    @Autowired
    public UserController(PermissionService permissionService) {
        this.permissionService = permissionService;
        // проверяем наличие в бд и если нет, то заносим и даём права админу
        permissionService.registerPermission(PERMISSION_MAP.get(PermissionName.userViewList));
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/loginForm")
    public String login(Model model, HttpServletRequest request) {
        model.addAttribute("loginPageURL", PAGE_MAP.get(PagesName.userLogin));
        PageInfo.makeAllPlease(model,PagesName.userLoginForm);
        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();

    }

    @PostMapping("/login")
    public String login(
            Model model, HttpServletRequest request,
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "new_user", defaultValue = "false") boolean newUser
    ) {

        if (newUser) {
            userService.newUser(username, password);

        }
        model.addAttribute("new_user", newUser);
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "forward:/authenticateTheUser";

    }


    @RequestMapping("/accessDenied")
    public String showAccessDeniedPage(Model model) {
        PageInfo.makeAllPlease(model,PagesName.userAccessDenied);
        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
    }


    @RequestMapping("/list")
    public String userList(Model model) {
        if (PageInfo.makeAllPlease(model,PagesName.userList))
        {
            model.addAttribute("userList", userService.getUsers());
          }
        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
    }

}
