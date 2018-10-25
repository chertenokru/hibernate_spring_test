package ru.chertenok.spring.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.chertenok.spring.hibernate.services.UserService;
import ru.chertenok.spring.hibernate.util.PageInfo;

import javax.servlet.http.HttpServletRequest;

import static ru.chertenok.spring.hibernate.config.Config.*;
import static ru.chertenok.spring.hibernate.config.Config.PAGE_MAP;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    {
        PAGE_MAP.put(PagesName.userList, new PageInfo("/user/list", "Список пользователей", "user_list"));
        PAGE_MAP.put(PagesName.userLogin, new PageInfo("/user/login", "Авторизация", "user_login"));
        PAGE_MAP.put(PagesName.userAccessDenied, new PageInfo("/denied", "Доступ запрещен", "access-denied"));

    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/loginForm")
    public String login(Model model, HttpServletRequest request){
        model.addAttribute("loginPageURL",PAGE_MAP.get(PagesName.userLogin));
        model.addAttribute("contentPage", PAGE_MAP.get(PagesName.userLogin).getSHABLON());
        model.addAttribute(BREADCRUMB, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.userLogin)});
        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();

    }

    @PostMapping("/login")
    public String login(
            Model model, HttpServletRequest request,
                        @RequestParam(name = "username") String username,
                        @RequestParam(name = "password")  String password,
                        @RequestParam(name = "new_user", defaultValue = "false") boolean newUser
                        ){

        if (newUser) {
            userService.newUser(username,password,userService.getDefaultRole());

        }
        return "forward:/authenticateTheUser";

        }


    @RequestMapping("/accessDenied")
    public String showAccessDeniedPage(Model model) {

        model.addAttribute("contentPage", PAGE_MAP.get(PagesName.userAccessDenied).getSHABLON());
        model.addAttribute(BREADCRUMB, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.userAccessDenied)});
        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
    }


    @RequestMapping("/list")
    public String  userList(Model model){

        model.addAttribute("userList",userService.getUsers());

        model.addAttribute("contentPage", PAGE_MAP.get(PagesName.userList).getSHABLON());
        model.addAttribute(BREADCRUMB, new PageInfo[]{PAGE_MAP.get(PagesName.home), PAGE_MAP.get(PagesName.userList)});
        return PAGE_MAP.get(PagesName.mainShablon).getSHABLON();
    }

}
